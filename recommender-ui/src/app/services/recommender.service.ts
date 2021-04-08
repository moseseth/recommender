import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs';

const service: string = environment.baseUrl;

export interface Customer {
  age: number;
  income: number;
  student: boolean;
}

export interface Product {
  type: string;
}

@Injectable({
  providedIn: 'root'
})
export class RecommenderService {

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  getProduct(customer: Customer): Observable<Array<Product>> {
    const options: object = {
      responseType: 'json',
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`)
        .set('Content-Type', 'application/json'),
      params: customer
    };

    return this.http.get<Array<Product>>(`${service}api/products`, options);
  }

}
