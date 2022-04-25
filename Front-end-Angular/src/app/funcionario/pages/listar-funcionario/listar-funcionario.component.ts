import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from '../../components/delete-dialog/delete-dialog.component';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-listar-funcionario',
  templateUrl: './listar-funcionario.component.html',
  styleUrls: ['./listar-funcionario.component.css']
})
export class ListarFuncionarioComponent implements OnInit {

  funcionarios: Funcionario[] = []

  columns: string [] = ['idFuncionario', 'nome', 'email', 'actions']

  constructor(
    private funHttpService: FuncionarioHttpService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.funHttpService.getFuncionarios().subscribe(
      (funcionarios) => {
       this.funcionarios = funcionarios
      }
    )
  }

  confirmaDelete(){
    const dialogRef = this.dialog.open(DeleteDialogComponent)

    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result)
      }
    )
  }
}
