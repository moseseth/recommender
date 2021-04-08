import {Component} from '@angular/core';
import {AuthService} from './services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  constructor(public auth: AuthService, private router: Router) { }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['login']);
  }
}
