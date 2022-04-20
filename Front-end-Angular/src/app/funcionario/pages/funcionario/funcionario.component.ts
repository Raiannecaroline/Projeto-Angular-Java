import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-funcionario',
  templateUrl: './funcionario.component.html',
  styleUrls: ['./funcionario.component.css']
})
export class FuncionarioComponent implements OnInit {
  
  idFuncionario!: number | null;
  funcionario!: Funcionario;

  constructor(
    private route: ActivatedRoute,
    private funHttpService:FuncionarioHttpService
  ) { }

  ngOnInit(): void {
    //Propiredade receba o parâmetro retornado
    this.idFuncionario = parseInt(this.route.snapshot.paramMap.get('idFuncionario') || '');

    this.funHttpService.getFuncionarioById(this.idFuncionario).subscribe(
      (f) => {
        this.funcionario = f
      }
    )
  }

}
