import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent implements OnInit {
  isLogin = false;
  roles: string[];
  authority: string;

  constructor(private tokenService: TokenService, private router: Router) { } 
  
  title = 'AngSpringProd';
  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogin = true;
      this.roles = [];
      this.roles = this.tokenService.getAuthorities();
      this.roles.every(rol => {
        if (rol === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  } 

  logOut(): void {
    this.tokenService.logOut();
    this.isLogin = false;
    this.authority = '';
    this.router.navigate(['home']);
  }
  
}
