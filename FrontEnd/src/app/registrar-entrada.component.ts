// registrar-entrada.component.ts
import {Component, OnInit} from '@angular/core';
import {FichajeService} from "./fichaje.service";
import {FichajeData} from "./fichaje.model";
@Component({
  selector: 'app-registrar-entrada',
  template: '<button (click)="registrarEntrada()">Registrar Entrada</button>'+
    '<div *ngIf="errorMensaje" class="error-message">\n' +
    '    {{ errorMensaje }}\n' +
    '</div>',
  styles: [
    `
      .error-message {
        color: red;
        margin-top: 10px;
      }
    `,
  ],
})
export class RegistrarEntradaComponent implements OnInit{
  fichaje: FichajeData= {} as FichajeData;
  errorMensaje: string = '';
  constructor(private fichajeService: FichajeService) { }

  ngOnInit(): void {}
  registrarEntrada() {
    this.errorMensaje = '';
    this.fichajeService.registrarEntrada().subscribe(
      (data: FichajeData) => {
        this.fichaje = data;
      },
      error => {
        this.errorMensaje = 'Ya se ha registrado una entrada. Registra la salida del ultimo';
        console.error('Error al obtener el fichaje', error);
      }
    );
  }
}
