// mostrar-fichajes.component.ts

import {Component, OnInit} from '@angular/core';
import {FichajeService} from "../fichaje.service";
import {FichajeData} from "../fichaje.model";

@Component({
  selector: 'app-mostrar-fichajes',
  templateUrl: './MostrarFichajes.html',
})
export class MostrarFichajesComponent implements OnInit{
  fichajes: FichajeData[] = [];

  constructor(private fichajeService: FichajeService) { }

  ngOnInit(): void {
    this.mostrarFichajes();
  }

  mostrarFichajes() {
    this.fichajeService.obtenerFichajes().subscribe(
      (data: FichajeData[]) => {
        this.fichajes = data;
      },
      error => {
        console.error('Error al obtener los fichajes', error);
      }
    );
  }
}
