import {Component} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  public username!: string;
  public password!: string;
  public error!: string;

  constructor(private auth: AuthService, private router: Router) {
  }

  public submit(): void {
    if (!this.username || !this.password) {
      this.error = '';
      return;
    }

    this.auth.login(this.username, this.password)
      .pipe(first())
      .subscribe(
        result => this.router.navigate(['dashboard']),
        err => this.error = 'Incorrect username or password'
      );
  }
}
