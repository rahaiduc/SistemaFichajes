// mostrar-fichajes.component.ts

import { Component} from '@angular/core';
import {FichajeService} from "./fichaje.service";

@Component({
  selector: 'app-mostrar-fichajes',
  template: '<button (click)="mostrarFichajes()">Mostrar Fichajes</button>'
})
export class MostrarFichajesComponent {
  fichajes: any[] = [];

  constructor(private fichajeService: FichajeService) { }

  mostrarFichajes() {

  }
}
