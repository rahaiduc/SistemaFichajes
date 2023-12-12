// app.routes.ts


import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FichajesComponent } from './fichajes/fichajes.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'fichajes', component: FichajesComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  // Otros caminos necesarios
];


