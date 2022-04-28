import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-novo-funcionario',
  templateUrl: './novo-funcionario.component.html',
  styleUrls: ['./novo-funcionario.component.css']
})
export class NovoFuncionarioComponent implements OnInit {

  @ViewChild('fileInput')
  fileInput!: ElementRef

  funcionario: FormGroup = this.fb.group({
    nome: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    foto: ['']
  })

  foto!: File

  constructor(
    private fb: FormBuilder,
    private funHttpService: FuncionarioHttpService,
    private snackbar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  selectInput(): void{
    this.fileInput.nativeElement.click();
  }

  submit(): void{
    const funcionario = this.funcionario.value
    funcionario.foto = null

    this.funHttpService
    .createFuncionario(funcionario)
    .subscribe(
      (fun) =>{

        if(this.foto != undefined){
          const formData: FormData = new FormData()

          formData.append('foto', new Blob([this.foto], { type: this.foto.type}))

          const filename = `funcionario-${fun.idFuncionario}.${this.foto.type.split('/')[1]}`

          this.funHttpService.addFoto(fun.idFuncionario || 0, formData, filename)
          .subscribe(
            () => {
              this.showMeSnackBarSucess()
            },
            (error: HttpErrorResponse) =>{
              this.showMeSnackBarError(error)
            }
          )
        } else{
          this.showMeSnackBarSucess()
        }

      },
      (error: HttpErrorResponse) =>{
        this.showMeSnackBarError(error)
      }
    )
  }

  fileChange(event: any) {
    this.foto = event.target.files[0]
    console.log(this.foto)
    console.log(this.foto.type.split('/')[1])
  }

  showMeSnackBarSucess(){
    this.snackbar.open('Funcionário salvo!', 'OK', {
      duration: 3000,
      horizontalPosition: 'left',
      verticalPosition: 'top'
    })
    this.router.navigateByUrl('/funcionario')
  }

  showMeSnackBarError(error: HttpErrorResponse){
    this.snackbar.open(`O arquivo não conseguiu ser salvo (Error ${error.status})`, 'OK', {
      duration: 3000,
      horizontalPosition: 'left',
      verticalPosition: 'top'
    })
  }

}
