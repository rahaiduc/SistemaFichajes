package com.sistemafichajes.controller;



import com.sistemafichajes.Configuracion;
import com.sistemafichajes.application.impl.FichajeServiceImpl;
import com.sistemafichajes.application.impl.ServiceBloques;
import com.sistemafichajes.application.impl.ServiceNodo;
import com.sistemafichajes.application.impl.ServiceTransacciones;
import com.sistemafichajes.controller.dto.inputs.FichajeInputDto;
import com.sistemafichajes.controller.dto.outputs.FichajeOutputDto;
import com.sistemafichajes.domain.Fichaje;
import com.sistemafichajes.domain.Mappers.FichajeMapper;
import com.sistemafichajes.domain.blockchain.Bloque;
import com.sistemafichajes.domain.blockchain.Transaccion;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController()
@RequestMapping("transaccion")
public class RestControllerTransacciones {
    Logger logger= LoggerFactory.getLogger(RestControllerTransacciones.class);
    private final ServiceTransacciones servicioTransacciones;
    private final ServiceBloques serviceBloques;

    private final ServiceNodo servicioNodo;

    private final FichajeServiceImpl fichajeService;

    @Autowired
    public RestControllerTransacciones(ServiceTransacciones servicioTransacciones,ServiceBloques serviceBloques, ServiceNodo servicioNodo,FichajeServiceImpl fichajeService) {
        this.servicioTransacciones = servicioTransacciones;
        this.servicioNodo = servicioNodo;
        this.serviceBloques=serviceBloques;
        this.fichajeService=fichajeService;
    }

    /**
     * Obtener el pool de transacciones pendientes de ser incluidas en un bloque
     * @return JSON pool de transacciones
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<Transaccion> getPoolTransacciones() {
        logger.info("PETICION POOL DE TRANSACCIONES\n");
        logger.info("Tamaño del pool: " + servicioTransacciones.getPoolTransacciones().size());
        return servicioTransacciones.getPoolTransacciones();
    }


    /**
     * Añadir una transaccion al pool
     * @param transaccion1 Transaccion a ser añaadida
     * @param propagar si la transacción debe ser propagada a otros nodos en la red
     * @param response código 202 si la transacción es añadida al pool, 406 en otro caso
     */
    @PostMapping("/{idEmpleado}")
    void añadirTransaccion(@RequestBody(required = false) Transaccion transaccion1, @RequestParam(required = false) Boolean propagar,@PathVariable String idEmpleado, HttpServletResponse response) {
        logger.info("NUEVA TRANSACCION RECIBIDA:");
        FichajeOutputDto fichajeOutputDto=fichajeService.getFichajeEntrada(idEmpleado);
        Fichaje fichaje=FichajeMapper.INSTANCE.fichajeInputToFichaje(new FichajeInputDto(fichajeOutputDto.getId_fichaje(),fichajeOutputDto.getId_empleado(),fichajeOutputDto.getTimeEntry().getTime(),fichajeOutputDto.getTimeExit().getTime()));
        Transaccion transaccion= transaccion1!=null ? transaccion1 : new Transaccion(new byte[256], fichaje,new byte[256]);
        transaccion.setEsCoinbase(true);
        System.out.println(transaccion);
        System.out.println("\n");
        try {
        	//comprobar si la transacción es válida
        	servicioTransacciones.añadirTransaccion(transaccion);
            List<Transaccion> poolTransacciones=servicioTransacciones.getPoolTransacciones();
            if(poolTransacciones.size()==Configuracion.getInstancia().getMaxNumeroTransaccionesEnBloque()){
                logger.info("Se ha alcanzado el numero maximo de transacciones");
                Bloque ultimoBloque = serviceBloques.getCadenaDeBloques().getUltimoBloque();
                byte[] hashUltimoBloque =  ultimoBloque!= null
                        ? ultimoBloque.getHash()
                        : null;
                for(Transaccion t:poolTransacciones){
                    this.serviceBloques.getCadenaDeBloques().getFichajes().liquidarTransaccion(t);
                    logger.info(t.toString());
                }
                Bloque bloque = new Bloque(hashUltimoBloque, poolTransacciones, 10);
                serviceBloques.añadirBloque(bloque);
                servicioNodo.emitirPeticionPostNodosVecinos("bloque", bloque);
            }
        	
            logger.info("Transaccion añadida al pool.\n");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);

            if (propagar != null && propagar) {
                servicioNodo.emitirPeticionPostNodosVecinos("transaccion", transaccion);
                logger.info("Transaccion propagada.\n");
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        	
        }
    }

}