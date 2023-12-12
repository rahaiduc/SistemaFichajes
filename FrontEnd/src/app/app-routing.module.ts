//app-routing.modules.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FichajesComponent} from "./fichajes/fichajes.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'fichajes', component: FichajesComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
