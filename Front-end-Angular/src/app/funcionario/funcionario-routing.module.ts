import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { IsNumberGuard } from './guards/is-number.guard';
import { EditFuncionarioComponent } from './pages/edit-funcionario/edit-funcionario.component';
import { ConfirmExitGuard } from './guards/confirm-exit.guard';
import { CanEnterGuard } from './guards/can-enter.guard';

const routes: Routes = [
  {
    path: 'novo-funcionario',
    component: NovoFuncionarioComponent,
    canDeactivate: [
      ConfirmExitGuard
    ],
    canActivate: [
      CanEnterGuard
    ]
  },
  {
    path: '',
    pathMatch: 'full',
    component: ListarFuncionarioComponent,
    canActivate: [
      CanEnterGuard
    ]
  },
  {
    path: ':idFuncionario',
    component: FuncionarioComponent,
    canActivate: [
      IsNumberGuard,
      CanEnterGuard
    ]
  },
  {
    path: 'edit/:idFuncionario',
    component: EditFuncionarioComponent,
    canDeactivate: [
      ConfirmExitGuard
    ],
    canActivate: [
      CanEnterGuard
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class FuncionarioRoutingModule { }
