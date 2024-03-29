import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FuncionarioRoutingModule } from './funcionario-routing.module';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';
import { MaterialModule } from '../material/material.module';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { IsNumberGuard } from './guards/is-number.guard';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { EditFuncionarioComponent } from './pages/edit-funcionario/edit-funcionario.component';
import { ConfirmExitGuard } from './guards/confirm-exit.guard';
import { ConfirmExitDialogComponent } from './components/confirm-exit-dialog/confirm-exit-dialog.component';
import { CanEnterGuard } from './guards/can-enter.guard';

@NgModule({
  declarations: [
    NovoFuncionarioComponent,
    ListarFuncionarioComponent,
    FuncionarioComponent,
    DeleteDialogComponent,
    EditFuncionarioComponent,
    ConfirmExitDialogComponent
  ],
  imports: [
    CommonModule,
    FuncionarioRoutingModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    IsNumberGuard,
    ConfirmExitGuard,
    CanEnterGuard
  ]
})
export class FuncionarioModule { }
