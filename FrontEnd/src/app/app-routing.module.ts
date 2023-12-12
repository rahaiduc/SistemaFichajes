//app-routing.modules.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FichajesComponent} from "./fichajes/fichajes.component";
import {LoginComponent} from "./login/login.component";
import {MostrarFichajesComponent} from "./fichajes/mostrar-fichajes.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'fichajes', component: FichajesComponent },
  { path: 'mostrarfichajes', component: MostrarFichajesComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
