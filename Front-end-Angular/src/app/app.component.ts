import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthenticationService } from './auth/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Front-end-Angular';

  logged$!: boolean

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ){}

  ngOnInit() {
    this.authService.logged()
    .subscribe(
      (logged) => {
        this.logged$ = logged
      }
    )
  }

  logout(){
    this.authService.logout()
    this.router.navigateByUrl('/auth/login')
  }
}
