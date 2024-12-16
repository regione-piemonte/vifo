/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User} from '../models/user';
import { ConfigUtente } from '../models/user-info';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  baseUrl: string = "";

  constructor(private http: HttpClient, private config: ConfigService) {}

  getUser() {
    return this.http.get<any>(`${this.config.getBERootUrl(
      false
    )}/utenti`);
  }

  getUserData(userId: number): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/soggetti/${userId}`
    );
  }

  saveUser(user: User): Observable<User> {
    if (user.idSoggetto === 0 || user.idSoggetto === null) {
      return this.http.post<User>(
        `${this.config.getBERootUrl(false)}/utenti`,
        user
      );
    } else {
      return this.http.put<User>(
        `${this.config.getBERootUrl(false)}/utenti`,
        user
      );
    }
  }

  getConfigationByName(configName: string): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }
  
  getHomeData() : Observable<ConfigUtente> {
    return this.http.get<ConfigUtente>(this.config.getBERootUrl(false) + `/geoPlPfi/home`);
   }

  getUtenti(): Observable<User> {
    return this.http.get<User>(this.config.getBERootUrl(false) + `/utenti`);
  } 

  isUtenteAlreadyUpdate(): Observable<User> {
    return this.http.get<User>(this.config.getBERootUrl(false) + `/utenti/isUpdate`);
  } 

  logout(): Observable<User> {
    return this.http.get(this.config.getBERootUrl(false) + `/utenti/logout`);
  } 
}
