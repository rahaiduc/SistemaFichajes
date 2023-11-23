// fichajes.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RegistrarEntradaComponent } from './registrar-entrada.component';
import { RegistrarSalidaComponent } from './registrar-salida.component';
import { MostrarFichajesComponent } from './mostrar-fichajes.component';

@NgModule({
  declarations: [
    RegistrarEntradaComponent,
    RegistrarSalidaComponent,
    MostrarFichajesComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [
    RegistrarEntradaComponent,
    RegistrarSalidaComponent,
    MostrarFichajesComponent
  ]
})
export class FichajesModule { }
