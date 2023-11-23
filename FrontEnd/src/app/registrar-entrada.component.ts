// registrar-entrada.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-registrar-entrada',
  template: '<button (click)="registrarEntrada()">Registrar Entrada</button>',
})
export class RegistrarEntradaComponent {
  registrarEntrada() {
    console.log('Entrada registrada');
  }
}
