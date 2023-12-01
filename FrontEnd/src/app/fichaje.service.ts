// fichaje.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FichajeService {
  private baseUrl = 'http://localhost:9080'; // Reemplaza con la URL de tu backend

  constructor(private http: HttpClient) { }

  obtenerFichajes(): Observable<any> {
    return this.http.get(`${this.baseUrl}/empleado/mostrarfichajes/1`);
  }
  registrarEntrada():Observable<any> {
    return this.http.get(`${this.baseUrl}/empleado/addfichaje/1`);
  }

  registrarSalida():Observable<any> {
    return this.http.post(`${this.baseUrl}/transaccion/1`,{});
  }
}
