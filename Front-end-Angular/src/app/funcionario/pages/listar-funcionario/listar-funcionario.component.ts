import { Component, OnInit } from '@angular/core';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-listar-funcionario',
  templateUrl: './listar-funcionario.component.html',
  styleUrls: ['./listar-funcionario.component.css']
})
export class ListarFuncionarioComponent implements OnInit {

  funcionarios = [

    {
      id: 1,
      nome: 'Raiane',
      email: 'nome@gmail.com',
      foto: 'aaaaaaa'
    },
    {
      id: 2,
      nome: 'Lucas',
      email: 'Lucas@gmail.com',
      foto: 'aaaaaaa'
    },
    {
      id: 3,
      nome: 'Rafa',
      email: 'Rafa@gmail.com',
      foto: 'aaaaaaa'
    }

  ]

  columns: string[] = ['id', 'nome', 'email']

  constructor(
    private funHttpService: FuncionarioHttpService
  ) { }

  ngOnInit(): void {
    this.funHttpService.getFuncionarios().subscribe(
      (funcionarios) => {
        console.log(funcionarios)
      }
    )
  }

}
