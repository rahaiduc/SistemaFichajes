// registrar-salida.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-registrar-salida',
  template: '<button (click)="registrarSalida()">Registrar Salida</button>',
})
export class RegistrarSalidaComponent {
  registrarSalida() {
    console.log('Salida registrada');
  }
}
