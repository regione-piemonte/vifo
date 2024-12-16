/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as fileSaver from "file-saver";
import { CertificatoModel } from 'src/app/shared/models/certificato.molde';
import { PartitaFruttiModel, PartitaPianteModel, PartitaSemiModel } from 'src/app/shared/models/partita.model';
import { ConfigService } from 'src/app/shared/services/config.service';
import { FiltroInventarioModel } from 'src/app/shared/models/filtro-inventario.model';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  constructor(private httpClient: HttpClient, private config: ConfigService) { }

  private getSort(paging:any){
    let sortOrder = paging.sortOrder == -1 ? '-':'+';
    return paging.sortField?'&sort='+ sortOrder + paging.sortField:'';
  }

  //FRUTTI ************************************
  getPaginatedListFrutti(paging: any, filter:FiltroInventarioModel) : Observable<any[]> {
    let sort = this.getSort(paging);
    let qFilter:any = '';
    if(filter){
      qFilter = '&numero=' +(filter.numeroPartita?filter.numeroPartita:0)
      + '&specie=' +(filter.nomeSpecie?filter.nomeSpecie:'')  
      + '&annata='+(filter.annata?filter.annata:'0') 
      + '&attive=' + (filter.attive?filter.attive:'false');
    }
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/frutti/list?page=${paging.page}&limit=${paging.limit}${sort}${qFilter}`)
  }

  getPartitaFruttiById(idInventario): Observable<PartitaFruttiModel> {
    return this.httpClient.get<PartitaFruttiModel>(this.config.getBERootUrl(false) 
    + `/inventario/frutti/${idInventario}`)
  }

  getPartitaFruttiRiepilogoById(idInventario): Observable<any> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/frutti/riepilogo/${idInventario}`)
  }

  savePartitaFrutti(partitaFrutti: PartitaFruttiModel){
    if(!partitaFrutti.idInventarioFrutti){
      return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/inventario/frutti/save`, partitaFrutti);
    }else{
      return this.httpClient.put<any>(`${this.config.getBERootUrl(false)}/inventario/frutti/update`, partitaFrutti);
    }
  }

  deletePartitaFrutti(idInventario: number){
    return this.httpClient.delete<any>(`${this.config.getBERootUrl(false)}/inventario/frutti/delete/${idInventario}`);
  }

  downloadInventarioFruttiExcel() {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/excel/frutti`,
      { observe: "response", responseType: "blob" }
    ).subscribe(resp => {
      let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];

        fileSaver.saveAs(resp.body, filename);
    });
  }

  //SEMI ************************************
  getPaginatedListSemi(paging:any, filter:FiltroInventarioModel) : Observable<any[]> {
    let sort = this.getSort(paging);
    let qFilter:any = '';
    if(filter){
      qFilter ='&numero=' +(filter.numeroPartita?filter.numeroPartita:0)
      + '&specie=' +(filter.nomeSpecie?filter.nomeSpecie:'')  
      + '&annata='+(filter.annata?filter.annata:'0') 
      + '&attive=' + (filter.attive?filter.attive:'false');
    }
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/semi/list?page=${paging.page}&limit=${paging.limit}${sort}${qFilter}`)
  }

  getPartitaSemiById(idInventario): Observable<PartitaSemiModel> {
    return this.httpClient.get<PartitaSemiModel>(this.config.getBERootUrl(false) 
    + `/inventario/semi/${idInventario}`)
  }

  getPartitaSemiRiepilogoById(idInventario): Observable<any> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/semi/riepilogo/${idInventario}`)
  }

  savePartitaSemi(partitaSemi: PartitaSemiModel){
    if(!partitaSemi.idInventarioSemi){
      return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/inventario/semi/save`, partitaSemi);
    }else{
      return this.httpClient.put<any>(`${this.config.getBERootUrl(false)}/inventario/semi/update`, partitaSemi);
    }
  }

  deletePartitaSemi(idInventario: number){
    return this.httpClient.delete<any>(`${this.config.getBERootUrl(false)}/inventario/semi/delete/${idInventario}`);
  }

  downloadInventarioSemiExcel() {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/excel/semi`,
      { observe: "response", responseType: "blob" }
    ).subscribe(resp => {
      let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];

        fileSaver.saveAs(resp.body, filename);
    });
  }

  //PIANTINE ************************************
  getPaginatedListPiante(paging, idVivaio: number, filter:FiltroInventarioModel) : Observable<any[]> {
    let sort = this.getSort(paging);
    let qFilter:any = '';
    if(filter){
      qFilter = '&numero='+(filter.numeroPartita?filter.numeroPartita:0) 
      + '&specie=' +(filter.nomeSpecie?filter.nomeSpecie:'')  
      + '&annata='+(filter.annata?filter.annata:'0') 
      + '&attive=' + (filter.attive?filter.attive:'false');
    }
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/piantine/list?page=${paging.page}&limit=${paging.limit}`
    +`&idVivaio=${idVivaio}${sort}${qFilter}`)
  }

  getRiepilogoPiantineById(id:number) : Observable<any> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/piantine/riepilogo/${id}`)
  }

  getPartitaPianteById(idInventario): Observable<PartitaSemiModel> {
    return this.httpClient.get<PartitaPianteModel>(this.config.getBERootUrl(false) 
    + `/inventario/piantine/${idInventario}`)
  }

  savePartitaPiante(partitaPiante: PartitaPianteModel){
    if(!partitaPiante.idInventarioPiante){
      return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/inventario/piantine/save`, partitaPiante);
    }else{
      return this.httpClient.put<any>(`${this.config.getBERootUrl(false)}/inventario/piantine/update`, partitaPiante);
    }
  }

  deletePartitaPiantine(idInventario: number){
    return this.httpClient.delete<any>(`${this.config.getBERootUrl(false)}/inventario/piantine/delete/${idInventario}`);
  }

  downloadInventarioPiantineExcel(idVivio: number) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/excel/piantine/${idVivio}`,
      { observe: "response", responseType: "blob" }
    ).subscribe(resp => {
      console.log(resp);
      
      let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];

        fileSaver.saveAs(resp.body, filename);
    });
  }

  downloadRegistoCaricoScaricoExcel() {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/excel/caricoScarico`,
      { observe: "response", responseType: "blob" }
    ).subscribe(resp => {
      let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];

        fileSaver.saveAs(resp.body, filename);
    });
  }

  //CERTIFICATI ************************************

  saveCertificato(certificato:CertificatoModel){
    if(certificato.idProvenienza){
      return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/inventario/certificatoPiemonte/save`, certificato);
    }else{
      return this.httpClient.put<any>(`${this.config.getBERootUrl(false)}/inventario/certificatoPiemonte/update`, certificato);
    }
  }

  getCertificatoPiemonteById(id: number): Observable<CertificatoModel>{
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/certificatoPiemonte/${id}`)
  }

  getListCertificatiPiemonte() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/inventario/certificatoPiemonte/list`)
    .pipe(
      map((response: any[]) => {
        const list: SelectItem[] = [];
        response.forEach(item => {
          list.push({
            label: item.codiceCertificatoProvenienza,
            value: item.idProvenienza
          });
        });
        return list;
      })
    );
  }

  getPaginatedListCertificati(paging: any, filter:FiltroInventarioModel) : Observable<any[]> {
    let sort = this.getSort(paging);
    let qFilter = '';
    if(filter){
      qFilter = '&specie=' +(filter.nomeSpecie?filter.nomeSpecie:'')  
      + '&annata='+(filter.annata?filter.annata:'0');
    }
    return this.httpClient.get<any>(this.config.getBERootUrl(false) 
    + `/inventario/certificatoPiemonte/list?page=${paging.page}&limit=${paging.limit}${sort}${qFilter}`)
  }

  //TRASFERIMENTI ************************************

  saveRichiestaTrasferimento(richiesta: any){
    return this.httpClient.post<any>(this.config.getBERootUrl(false)
    + `/inventario/richiestaTrasferimento/save`, richiesta);
  }

  getPaginatedListTrasferimentoPiantine(paging:any){
    let sort = this.getSort(paging);
    return this.httpClient.get<any>(this.config.getBERootUrl(false)
    + `/inventario/richiestaTrasferimento/list?page=${paging.page}&limit=${paging.limit}${sort}`);
  }

  getCountTrasferimentoPiantine(){
    return this.httpClient.get<any>(this.config.getBERootUrl(false)
    + `/inventario/richiestaTrasferimento/count`);
  }

  getDetailTrasferimentoPiantineById(id:number){
    return this.httpClient.get<any>(this.config.getBERootUrl(false)
    + `/inventario/richiestaTrasferimento/${id}`);
  }

  accettaRifiutaTrasferimento(richiesta: any){
    return this.httpClient.put<any>(this.config.getBERootUrl(false)
    + `/inventario/richiestaTrasferimento/accettaRifiuta`, richiesta);
  }

  // nuova annata
  
  getInfoAnnataInventario(idVivaio:number){
    return this.httpClient.get<any>(this.config.getBERootUrl(false)
    + `/inventario/infoAnnata/vivaio/${idVivaio?idVivaio:-1}`);
  }

  creaAnnataInventario(vivaio:any){
    return this.httpClient.post<any>(this.config.getBERootUrl(false)
    + `/inventario/creaAnnata/inventario`,vivaio);
  }

  
}
