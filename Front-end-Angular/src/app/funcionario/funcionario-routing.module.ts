import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { IsNumberGuard } from './guards/is-number.guard';

const routes: Routes = [
  {
    path: 'novo-funcionario',
    component: NovoFuncionarioComponent,
  },
  {
    path: '',
    pathMatch: 'full',
    component: ListarFuncionarioComponent
  },
  {
    path: ':idFuncionario',
    component: FuncionarioComponent,
    canActivate: [
      IsNumberGuard
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
