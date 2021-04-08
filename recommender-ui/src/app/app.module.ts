import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {JwtModule} from '@auth0/angular-jwt';
import {AuthService} from './services/auth.service';
import {RecommenderService} from './services/recommender.service';
import {AuthGuard} from './guards/auth.guard';

export function tokenGetter(): string | null {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter,
        allowedDomains: ['localhost:8080'],
        disallowedRoutes: ['localhost:8080/api/auth']
      }
    })
  ],
  providers: [
    AuthService,
    RecommenderService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
