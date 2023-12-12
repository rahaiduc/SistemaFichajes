// registrar-salida.component.ts
import {Component, OnInit} from '@angular/core';
import {FichajeService} from "../fichaje.service";
import {FichajeData} from "../fichaje.model";
@Component({
  selector: 'app-registrar-salida',
  templateUrl: './botonSalida.html',
  styleUrls: ['./Boton.css']
})
export class RegistrarSalidaComponent implements OnInit{
  fichaje: FichajeData= {} as FichajeData;
  errorMensaje: string = '';
  showModal = false;
  modalTitle = 'Éxito';
  modalMessage = 'Se ha añadido el fichaje de salida';
  constructor(private fichajeService: FichajeService) { }

  ngOnInit(): void {}
  registrarSalida() {
    this.errorMensaje = '';
    this.fichajeService.registrarSalida().subscribe(
      (data: FichajeData) => {
        this.fichaje = data;
        this.showModal=true;
      },
      error => {
        this.errorMensaje = 'Hay que registrar primero la entrada';
        console.error('Error al obtener el fichaje', error);
      }
    );
  }
  closeModal() {
    // Cierra el modal
    this.showModal = false;
  }
}
