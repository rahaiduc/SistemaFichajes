// app.module.ts

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { RegistrarEntradaComponent } from './registrar-entrada.component';
import { RegistrarSalidaComponent } from './registrar-salida.component';
import { MostrarFichajesComponent } from './mostrar-fichajes.component';
import { LoginComponent } from './login/login.component';
import { FichajesComponent } from './fichajes/fichajes.component';

import { AppRoutingModule } from './app-routing.module';
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  declarations: [
    AppComponent,
    RegistrarEntradaComponent,
    RegistrarSalidaComponent,
    MostrarFichajesComponent,
    LoginComponent,
    FichajesComponent
  ],
  imports: [
    BrowserModule,// Asegúrate de tener BrowserModule importado
    CommonModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent], // Asegúrate de que AppComponent sea el componente principal
  exports: [
    RegistrarEntradaComponent,
    RegistrarSalidaComponent,
    MostrarFichajesComponent
  ]
})
export class AppModule { }
