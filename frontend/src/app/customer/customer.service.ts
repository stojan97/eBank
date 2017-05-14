import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Customer } from './customer';

@Injectable()
export class CustomerService {

  private customerUrl = '/api/customer';

  constructor(private http: Http) {

  }

  getCustomer(): Observable<Customer> {
    let headers = new Headers();
    headers.append('X-Requested-With', 'XMLHttpRequest');
    return this.http.get(this.customerUrl, {headers}).map(res => res.json());
  }

  // private extractData(res: Response) {
  //   let body = res.json();
  //   return body.data || { };
  // }

  // getCustomer() {
  //   let headers = new Headers();
  //   headers.append('X-Requested-With', 'XMLHttpRequest');
  //   return this.http.get('/api/customer', {headers}).map(res => res.json());
  // }

}
