// registrar-entrada.component.ts
import {Component, OnInit} from '@angular/core';
import {FichajeService} from "../fichaje.service";
import {FichajeData} from "../fichaje.model";
@Component({
  selector: 'app-registrar-entrada',
  templateUrl: './botonEntrada.html',
  styleUrls: ['./Boton.css']
})
export class RegistrarEntradaComponent implements OnInit{
  fichaje: FichajeData= {} as FichajeData;
  errorMensaje: string = '';
  exitoMensaje: string = '';
  showModal = false;
  modalTitle = 'Éxito';
  modalMessage = 'Se ha añadido el fichaje de entrada';

  constructor(private fichajeService: FichajeService) { }

  ngOnInit(): void {}
  registrarEntrada() {
    this.exitoMensaje = '';
    this.errorMensaje = '';
    this.fichajeService.registrarEntrada().subscribe(
      (data: FichajeData) => {
        this.fichaje = data;
        this.exitoMensaje = '¡Se ha añadido el fichaje de entrada!';
        this.errorMensaje = '';
      },
      error => {
        this.errorMensaje = 'Ya se ha registrado una entrada. Registra la salida del ultimo';
        this.exitoMensaje = '';
        console.error('Error al obtener el fichaje', error);
      }
    );
  }
  closeModal() {
    // Cierra el modal
    this.showModal = false;
  }
}
