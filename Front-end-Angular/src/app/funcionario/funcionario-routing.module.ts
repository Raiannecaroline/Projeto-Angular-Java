import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { IsNumberGuard } from './guards/is-number.guard';
import { EditFuncionarioComponent } from './pages/edit-funcionario/edit-funcionario.component';

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
  },
  {
    path: 'edit/:idFuncionario',
    component: EditFuncionarioComponent
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
