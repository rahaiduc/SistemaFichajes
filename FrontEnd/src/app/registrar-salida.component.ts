// registrar-salida.component.ts
import {Component, OnInit} from '@angular/core';
import {FichajeService} from "./fichaje.service";
import {FichajeData} from "./fichaje.model";
@Component({
  selector: 'app-registrar-salida',
  template: '<button (click)="registrarSalida()">Registrar Salida</button>' +
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
export class RegistrarSalidaComponent implements OnInit{
  fichaje: FichajeData= {} as FichajeData;
  errorMensaje: string = '';
  constructor(private fichajeService: FichajeService) { }

  ngOnInit(): void {}
  registrarSalida() {
    this.errorMensaje = '';
    this.fichajeService.registrarSalida().subscribe(
      (data: FichajeData) => {
        this.fichaje = data;
      },
      error => {
        this.errorMensaje = 'Hay que registrar primero la entrada';
        console.error('Error al obtener el fichaje', error);
      }
    );
  }
}
