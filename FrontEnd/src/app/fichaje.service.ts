// fichaje.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FichajeService {
  private baseUrl = 'http://localhost:9080'; // Reemplaza con la URL de tu backend
  empleado: any;
  constructor(private http: HttpClient) {
    const usuarioString = sessionStorage.getItem('usuario');
    if (usuarioString) {
      this.empleado = JSON.parse(usuarioString);
    }
  }

  obtenerFichajes(): Observable<any> {
    return this.http.get(`${this.baseUrl}/empleado/mostrarfichajes/`+this.empleado.id_empleado);
  }
  registrarEntrada():Observable<any> {
    return this.http.get(`${this.baseUrl}/empleado/addfichaje/`+this.empleado.id_empleado);
  }

  registrarSalida():Observable<any> {
    return this.http.post(`${this.baseUrl}/transaccion/`+this.empleado.id_empleado,{});
  }
}
