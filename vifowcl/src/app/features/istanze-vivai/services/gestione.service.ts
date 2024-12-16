/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from 'src/app/shared/services/config.service';

@Injectable({
  providedIn: 'root'
})
export class GestioneService {
  constructor(private httpClient: HttpClient, private config: ConfigService) { }

  searchDomanda(page: number,limit: number,searchValues:any){
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/domanda/search?page=${page}&limit=${limit}`
    + `&cognome=${searchValues.cognome}&ragioneSociale=${searchValues.ragioneSociale}`
    + `&codFiscalePiva=${searchValues.codFiscalePiva}`
    + `&provincia=${searchValues.provincia}&comune=${searchValues.comune}`);
  }




}
