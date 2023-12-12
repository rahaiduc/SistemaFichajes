// auth.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9080/empleado/login';

  constructor(private http: HttpClient) {}

  login(usuario: string, password: string): Observable<any> {
    return this.http.post(this.apiUrl, { usuario, password });
  }
}
