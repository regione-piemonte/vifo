/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';
import { from, Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import * as fileSaver from "file-saver";
import { InstanceDomandaTable, Content } from 'src/app/shared/models/view-instance';
import { ConfigService } from 'src/app/shared/services/config.service';
import { ProvinciaModel } from 'src/app/shared/models/provincia.model';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { ConfigUtenteModel, SoggettoModel } from 'src/app/shared/models/soggetto.model';
import { DocumentoAllegato } from 'src/app/shared/models/plain-sesto-sezione.model';
import { AssegnazioneModel } from 'src/app/shared/models/assegnazione.model';
import { DomandaModel } from 'src/app/shared/models/domanda.model';
import { DdtModel } from 'src/app/shared/models/ddt.model';
import { SpecieRichiestaFull } from 'src/app/shared/models/specie-richiesta-full.model';
import { PagamentoModel } from 'src/app/shared/models/pagamento.model';
import { ConfigPagamentiModel } from 'src/app/shared/models/configPagamenti.model';
@Injectable({
  providedIn: 'root'
})
export class VivaiService {

  typeFiltering = {
    Comune: ['denominazione']
  };

  constructor(private httpClient: HttpClient, private config: ConfigService) {}

  getLoggedSoggetto() : Observable<SoggettoModel>{
    return this.httpClient.get<SoggettoModel>(this.config.getBERootUrl(false) + `/soggetti/logged/user`);
  }

  getSoggettiById(idSoggetti: number) : Observable<SoggettoModel>{
    return this.httpClient.get<SoggettoModel>(this.config.getBERootUrl(false) + `/soggetti/${idSoggetti}`);
  }
  getConfigById(idConfig: number) : Observable<ConfigUtenteModel>{
    return this.httpClient.get<ConfigUtenteModel>(this.config.getBERootUrl(false) + `/soggetti/config/${idConfig}`);
  }
  getSoggettoByCf(codice: string) : Observable<SoggettoModel>{
    return this.httpClient.get<SoggettoModel>(this.config.getBERootUrl(false) + `/soggetti/fisica?codiceFiscale=${codice}`);
  }

  getMyAziende(codice: string, isEntePubblico: boolean) : Observable<any>{
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
      + `/soggetti/myGiuridica?codiceFiscale=${codice}&isEntePubblico=${isEntePubblico}`);
  }

  getAziendaByCfAndType(codice: string, isEntePubblico:boolean) : Observable<any>{
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/soggetti/giuridica?codiceFiscale=${codice}`+
    `&isEntePubblico=${isEntePubblico}`);
  }

  saveSoggetto(soggetto:SoggettoModel) : Observable<any>{
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/soggetti`,soggetto);
  }

  saveSpecie(formData:FormData) : Observable<any>{//file: FormData
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/specieArboree`,formData);
  }

  updateSpecie(formData:FormData) : Observable<any>{
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/specieArboree`,formData);
  }

  getConfigationByName(configName: string): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }

  getConfigationPagamenti(): Observable<any> {
    return this.httpClient.get<ConfigPagamentiModel>(
      `${this.config.getBERootUrl(false)}/configuration/pagamenti`
    );
  }
 
  getHomeData() {
   return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/idfvivai/home`);
  }
  
  createSoggetti(soggetto: SoggettoModel) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/idfvivai`, soggetto, {observe: 'response'});
  }

  getNumDomandeBySoggetto(idSoggetto){
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/domanda/count/${idSoggetto}`);
  }

  loadDomanda(idDomanda:number){
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/domanda/${idDomanda}`);
  }

  saveDomanda(domanda: any) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/domanda`, domanda);
  }

  getInfoDomanda(idDomanda:number){
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/domanda/info/${idDomanda}`);
  }

  saveAssegnazione(assegnazione: any, idDomanda:number) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/assegnazione/save/${idDomanda}`, assegnazione);
  }

  saveDdt(listDdt: any, idDomanda:number) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/assegnazione/ddt/save/${idDomanda}`, listDdt);
  }

  updateDdt(listDdt: any, numeroDdt:number, annoDdt:number) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/assegnazione/ddt/update/${numeroDdt}/${annoDdt}`, listDdt);
  }

  annullaDdt(numeroDdt:number, annoDdt:number){
    return this.httpClient.delete<any>(`${this.config.getBERootUrl(false)}/assegnazione/ddt/annulla/${numeroDdt}/${annoDdt}`);
  }


  saveRitiroPiantine(assegnazione: any, idDomanda:number) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/assegnazione/ritiro/${idDomanda}`, assegnazione);
  }

  inviaDomanda(domanda: any) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/invia`, domanda);
  }

  confermaAssegnazione(idDomanda:number) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/${idDomanda}/accettazione`, idDomanda);
  }

  confermaVariazioni(domanda: any) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/variazione`, domanda);
  }

  updateDomanda(domanda: any) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda`, domanda);
  }

  updateDomandaToAssegnata(domanda: DomandaModel){
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/assegna`, domanda);
  }

  updateDomandaPreassegnata(domanda: DomandaModel){
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/preassegna`, domanda);
  }

  updateDomandaToClose(idDomanda:number) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/close`, {'idDomanda':idDomanda});
  }

  updateDomandaToRifiutata(idDomanda:number, motivoRifiuto:string) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) 
      + `/domanda/rifiutata`, {'idDomanda':idDomanda,'note':motivoRifiuto});
  }

  rejectAssegnazione(idDomanda:number) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/domanda/assegna/reject`, {'idDomanda':idDomanda});
  }

  deleteDomanda(idDomanda: number) {
    return this.httpClient.delete<any>(this.config.getBERootUrl(false) + `/domanda/${idDomanda}`);
  }

  getMyDomandeInArchivio(paging:any) {
    let sort = this.getSort(paging);
      return this.httpClient.get<Content<InstanceDomandaTable[]>>(
        this.config.getBERootUrl(false) + `/domanda/pagined/mine?page=${paging.page}&limit=${paging.limit}${sort}`);
  }

  uploadFileDomanda(file: FormData, idDomanda: number, tipo: number): Observable<any> {
    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/documento/upload/domanda?idDomanda=${idDomanda}&tipo=${tipo}`, file);
  }

  uploadFileImgSpecie(file: FormData, idSpecie: number, fotoType: number): Observable<DocumentoAllegato> {
    return this.httpClient.post<DocumentoAllegato>(`${this.config.getBERootUrl(false)}/documento/upload/imgSpecie?idSpecie=${idSpecie}&fotoType=${fotoType}`, file);
  }

  deleteDocumentoAllegato(idAllegato: number){
    return this.httpClient.delete(`${this.config.getBERootUrl(false)}/documento/delete/${idAllegato}`);
  }

  loadAllegatiByIdDomanda(idDomanda:number){
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/documento/domanda/${idDomanda}`);
  }

  downloadAllegato(idAllegato: number) {
    return this.httpClient.get(`${this.config.getBERootUrl(false)}/documento/download/allegato/${idAllegato}`, {observe: 'response', responseType: 'blob'});
  }

  getBaseUrlImgSpecie(){
    return this.config.getBERootUrl(false);
  }

  searchDomanda(paging:any,searchValues:any){
    let sort = this.getSort(paging);
    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/domanda/search?page=${paging.page}&limit=${paging.limit}${sort}`, searchValues);
  }

  searchDdt(paging:any, searchValues:any){
    let sort = this.getSort(paging);
    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/assegnazione/ddt/search?page=${paging.page}&limit=${paging.limit}${sort}`, searchValues);
  }

  private getSort(paging:any){
    let sortOrder = paging.sortOrder == -1 ? '-':'+';
    return paging.sortField?'&sort='+ sortOrder + paging.sortField:'';
  }

  getAssegnazioniByIdDomanda(idDomanda:number){
    return this.httpClient.get<AssegnazioneModel[]>(this.config.getBERootUrl(false) + `/assegnazione/${idDomanda}`);
  }

  getAssegnazioniNRByIdDomanda(idDomanda:number){
    return this.httpClient.get<AssegnazioneModel[]>(this.config.getBERootUrl(false) + `/assegnazione/nonRichiesta/${idDomanda}`);
  }

  getAllAssegnazioniByIdDomanda(idDomanda:number){
    return this.httpClient.get<AssegnazioneModel[]>(this.config.getBERootUrl(false) + `/assegnazione/riepilogo/${idDomanda}`);
  }

  getInfoDdt(numeroDdt:number, annoDdt:number){
    return this.httpClient.get<AssegnazioneModel[]>(this.config.getBERootUrl(false) + `/assegnazione/ddt/info/${numeroDdt}/${annoDdt}`);
  }

  getDdtDetails(numeroDdt:number, annoDdt:number){
    return this.httpClient.get<AssegnazioneModel[]>(this.config.getBERootUrl(false) + `/assegnazione/ddt/details/${numeroDdt}/${annoDdt}`);
  }

  getAvailableDdtByIdDomanda(idDomanda:number){
    return this.httpClient.get<DdtModel[]>(this.config.getBERootUrl(false) + `/assegnazione/ddt/available/${idDomanda}`);
  }

  getListDdtByIdDomanda(idDomanda:number){
    return this.httpClient.get<DdtModel[]>(this.config.getBERootUrl(false) + `/assegnazione/ddt/list/${idDomanda}`);
  }

  getPartiteBySpecieAndVivaio(idSpecie:number,idVivaio:number){
    return this.httpClient.get<AssegnazioneModel[]>(this.config.getBERootUrl(false) 
        + `/assegnazione/partite/specie?idSpecie=${idSpecie}&idVivaio=${idVivaio}`);
  }

  insertPagamentoPiantine(pagamento:PagamentoModel){
    return this.httpClient.post<any>(this.config.getBERootUrl(false) 
        + `/domanda/pagamento/piantine/add`, pagamento);
  }

  updatePagamentoPiantine(pagamento:PagamentoModel){
    return this.httpClient.put(this.config.getBERootUrl(false) 
        + `/domanda/pagamento/piantine/update`, pagamento);
  }

  annullaPagamentoPiantine(domanda:DomandaModel){
    return this.httpClient.put(this.config.getBERootUrl(false) 
        + `/domanda/pagamento/piantine/annulla`, {idDomanda:domanda.idDomanda});
  }

  downloadAvvisoPagamento(idDomanda: number) {
    return this.httpClient.get(this.config.getBERootUrl(false)
    + `/documento/download/pagamento/avviso/${idDomanda}`, 
    {observe: 'response', responseType: 'blob'})
    .subscribe(resp => {
      this.downloadFile(resp);
    });;
  }

  downloadRicevutaPagamento(idDomanda: number) {
    return this.httpClient.get(this.config.getBERootUrl(false)
    + `/documento/download/pagamento/ricevuta/${idDomanda}`, 
    {observe: 'response', responseType: 'blob'})
    .subscribe(resp => {
      this.downloadFile(resp);
    });
  }

  testRiceviRT(){
    let payload = {
      applicationId:1001,
      transactionId:9999,
      //dataOraMsgRicevuta,
      idMsgRicevuta:456789,
      iuv:'xxxxxxtr',
      codiceEsitoPagamento:9,
      descEsitoPagamento:'Attesa Pagamento',
      idMsgRichiesta:12,
     // timestamp: 1656498568,
      //rtData:[],
      //rtPDF:[],
      //importo:21.0
    }
   return this.httpClient.post<any>(this.config.getBERootUrl(false) 
    + `/callback/pagamenti/riceviRT`,payload);
  }




  pdfGenerator(docType:string, id:number, aux:any) {
    let params = '';
    if(aux){
      let keys = Object.keys(aux);
      for(let i in keys){
        params+='&' + keys[i] + '='+ aux[keys[i]];
      }
    }
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/pdf/generate/${docType}?id=${id}${params}`,
      { observe: "response", responseType: "blob" }
    ).subscribe(resp => {
      this.downloadFile(resp);
    });
      // let contentDisposition = resp.headers.get("content-disposition");
      // let filename = contentDisposition
      //   .split(";")[1]
      //   .split("filename")[1]
      //   .split("=")[1];
      //   fileSaver.saveAs(resp.body, filename);
    // });
  }

  downloadFile(resp:any){
    let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
        fileSaver.saveAs(resp.body, filename);
  }
  

  autocompleteFilter(event, res: any, whatFilteringFor) {
    const filtered: any[] = [];
    for (let i = 0; i < res.length; i++) {
      let filtering;
      for (let j = 0; j < this.typeFiltering[whatFilteringFor].length; j++) {
        filtering = res[i][this.typeFiltering[whatFilteringFor][j]];
        if (typeof filtering === 'number') {
          if (filtering.toString().indexOf((event.query).toString()) === 0) {
            filtered.push(res[i]);
          }
        }
        if (typeof filtering === 'string') {
          //  console.log(filtering.toLowerCase().indexOf((event.query).toLowerCase()));
          if (filtering.toLowerCase().indexOf((event.query).toLowerCase()) === 0) {
            filtered.push(res[i]);
          }
        }
      }
    }
    if (this.typeFiltering[whatFilteringFor].length > 1) {
      return this.filterDuplicates(filtered, 'codiceFiscale');
    }
    return filtered;
  }

  filterDuplicates(array, key) {
    return (array = array.filter(
      (item, index, self) => index === self.findIndex(t => t[key] === item[key])
    ));
  }

  searchSoggetti(paging:any,searchValues:any){
    let query = '';
    let keys = Object.keys(searchValues);
    let key:string;
    for(let i in keys){
      key = keys[i];
      query+= this.getQueryParam(key,searchValues);
    }
    let sort = this.getSort(paging);
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}` 
    + `/soggetti/ricerca?page=${paging.page}&limit=${paging.limit}${sort}` + query);
  }

  private getQueryParam(key:string, objValues){
    let val = objValues[key];
    return (val && val.length > 0) ? '&' + key + '=' + val:'';
  }
}
