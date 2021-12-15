import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.interface';
import { environment } from '../../environments/environment';
import {Message} from "../model/message.interface";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private server = environment.server;

  constructor(private http: HttpClient) {
  }

  getUser(name: string): Promise<User> {
    return this.http.post<User>(`${this.server}/users/new`, {name}).toPromise();
  }

  getAllUsers(): Promise<User[]> {
    return this.http.get<User[]>(`${this.server}/users`).toPromise();
  }
}
