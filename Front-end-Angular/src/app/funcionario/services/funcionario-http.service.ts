import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class FuncionarioHttpService {

  private readonly baseUrl = 'http://localhost:8080/servicos/funcionario'

  constructor(
    private http: HttpClient
  ) { }
}
