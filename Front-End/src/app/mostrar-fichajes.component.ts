// mostrar-fichajes.component.ts
import { Component, OnInit } from '@angular/core';
import { FichajeService } from './fichaje.service';

@Component({
  selector: 'app-mostrar-fichajes',
  template: '<button (click)="mostrarFichajes()">Mostrar Fichajes</button>\n' +
    '    <table *ngIf="fichajes && fichajes.length > 0">\n' +
    '      <thead>\n' +
    '        <tr>\n' +
    '          <th>ID</th>\n' +
    '          <th>Empleado ID</th>\n' +
    '          <th>Fecha Entrada</th>\n' +
    '          <th>Fecha Salida</th>\n' +
    '        </tr>\n' +
    '      </thead>\n' +
    '      <tbody>\n' +
    '        <tr *ngFor="let fichaje of fichajes">\n' +
    '          <td>{{ fichaje.id_fichaje }}</td>\n' +
    '          <td>{{ fichaje.empleado_id }}</td>\n' +
    '          <td>{{ fichaje.fechaEntrada | date:\'short\' }}</td>\n' +
    '          <td>{{ fichaje.fechaSalida | date:\'short\' }}</td>\n' +
    '        </tr>\n' +
    '      </tbody>\n' +
    '    </table>',
  styles: []
})
export class MostrarFichajesComponent implements OnInit{
  fichajes: any[] = [];

  constructor(private fichajeService: FichajeService) { }

  ngOnInit(): void {}
  mostrarFichajes() {
    this.fichajeService.obtenerFichajes().subscribe(
      (data: any[]) => {
        this.fichajes = data;
      },
      error => {
        console.error('Error al obtener los fichajes', error);
      }
    );
  }
}
