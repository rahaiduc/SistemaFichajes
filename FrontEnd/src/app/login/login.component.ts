// login.component.ts

import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  usuario: string = '';
  password: string = '';
  errorMensaje: string = '';
  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.usuario, this.password).subscribe(
      (response: any) => {
        // Guardar la información en la sesión
        sessionStorage.setItem('usuario', JSON.stringify(response));
        // Puedes almacenar más información según tus necesidades

        // Navegar a la página principal
        this.router.navigate(['/fichajes']);
      },
      (error) => {
        this.errorMensaje = 'Credenciales Incorrectas';
      }
    );
  }
}
