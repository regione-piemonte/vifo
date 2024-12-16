/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SelectItem } from "primeng/components/common/selectitem";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { SpecieRichiestaFull } from 'src/app/shared/models/specie-richiesta-full.model';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { SpecieModel } from 'src/app/shared/models/specie.model';
import { ConfigService } from 'src/app/shared/services/config.service';

@Injectable({
  providedIn: 'root'
})
export class DizionariService {

  typeFiltering = {
    Comune: ['nome'],
    Specie: ['nomeComune'],
    SelectItem: ['label'],
  };

  constructor(private httpClient: HttpClient, private config: ConfigService) { }

  getScontoByImporto(importo:number) : Observable<number> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/sconto?importo=${importo}`);
  }

  getListVivaio() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/vivaio/list`)
    .pipe(
      map((response: any[]) => {
        const list: SelectItem[] = [];
        response.forEach(item => {
          list.push({
            label: item.sigla + ' - ' + item.nome,
            value: item.idVivaio
          });
        });
        return list;
      })
    );
  }

  getListScopo() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/scopo/list`)
    .pipe(
      map((response: any[]) => {
        const list: SelectItem[] = [];
        response.forEach(item => {
          list.push({
            label: item.descrizione,
            value: item.idScopo
          });
        });
        return list;
      })
    );
  }

  getListTitolarita() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/titolarita/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListStatoDomanda() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/statoDomanda/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListTipoDomanda() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/tipoDomanda/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListMaterialiDiMoltiplicazione() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/materialiMoltiplicazione/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListCategorieMateriale() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/categoriaMateriale/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListTipiMaterialeBase() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/tipiMateriale/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListPopolamentoDaSeme() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/popolamentoSeme/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListOrigini() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/origini/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListUdmMateriale() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/udmMateriale/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListTipiAllevamento() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/tipiAllevamento/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListCognomeRichiedente() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/cognomeRichiedente/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItemString(response);})
    );
  }

  getListDenominazioneRichiedente() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/denominazioneRichiedente/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItemString(response);})
    );
  }

  getListGruppoSpecie() : Observable<SelectItem[]> {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/dizionario/gruppoSpecie/list`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  private fillSelectItemString(obj:any){
    const list: SelectItem[] = [];
        obj.forEach(item => {
          list.push({
            label: item,
            value: item
          });
        });
        return list;
  }

  private fillSelectItem(obj:any){
    const list: SelectItem[] = [];
        obj.forEach(item => {
          list.push({
            label: item.descrizione,
            value: item.id
          });
        });
        return list;
  }

  getAllComuni() {
    return this.httpClient.get<ComuneModel[]>(this.config.getBERootUrl(false) + `/dizionario/comuni/list`); 
  }

  getComuniPiemonte() {
    return this.httpClient.get<ComuneModel[]>(this.config.getBERootUrl(false) + `/dizionario/comuni/piemonte`); 
  }

  getComuniByProv(istatProv:string) {
    return this.httpClient.get<ComuneModel[]>(this.config.getBERootUrl(false) + `/dizionario/comuni/list/${istatProv}`); 
  }

  getAllProvince() {
    return this.httpClient.get<SelectItem[]>(this.config.getBERootUrl(false) + `/dizionario/province/list`)
    .pipe(
      map((res: any[]) => {
        const list: SelectItem[] = [{label: '- Seleziona -', value:''}];
        res.forEach(item => {
          list.push({
            label: item.nome,
            value: item.codIstat
          });
        });
        return list;
      })
    );
  }

  getProvincePiemonte() {
    return this.httpClient.get<SelectItem[]>(this.config.getBERootUrl(false) + `/dizionario/province/piemonte`)
    .pipe(
      map((res: any[]) => {
        const list: SelectItem[] = [];
        res.forEach(item => {
          list.push({
            label: item.nome,
            value: item.sigla
          });
        });
        return list;
      })
    );
  }

  getListSpecie() {
    return this.httpClient.get<SpecieModel[]>(this.config.getBERootUrl(false) + `/dizionario/specie/list`); 
  }

  getListSpecieAll(isAllSpecie:boolean) {
    return this.httpClient.get<SpecieRichiestaFull[]>(this.config.getBERootUrl(false) + `/dizionario/specie/list?all=${isAllSpecie}`); 
  }

  getListSpeciePaginated(isAllSpecie:boolean, paging:any, filter:string) {
    let sort = this.getSort(paging);
    return this.httpClient.get<SpecieRichiestaFull[]>(this.config.getBERootUrl(false) 
      + `/dizionario/specie/paginated?all=${isAllSpecie}&page=${paging.page}&limit=${paging.limit}${sort}&filter=${filter}`); 
  }

  getListFamiglieSpecie() {
    return this.httpClient.get<string[]>(this.config.getBERootUrl(false) + `/dizionario/specie/famiglie/list`); 
  }

  getListEta() {
    return this.httpClient.get<string[]>(this.config.getBERootUrl(false) + `/dizionario/piantine/eta/list`); 
  }

  getListSpecieOrderedByGroup(isAllSpecie:boolean) {
    return this.httpClient.get<SpecieRichiestaFull[]>(this.config.getBERootUrl(false) + `/dizionario/specie/byGroup?all=${isAllSpecie}`); 
  }

  getSpecieById(id:number) {
    return this.httpClient.get<SpecieModel>(this.config.getBERootUrl(false) + `/dizionario/specie/${id}`); 
  }

  getFruttiRangeAnni() {
    return this.httpClient.get<SelectItem[]>(this.config.getBERootUrl(false) + `/dizionario/frutti/range/anni`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getPiantineRangeAnni() {
    return this.httpClient.get<SelectItem[]>(this.config.getBERootUrl(false) + `/dizionario/piantine/range/anni`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getSemiRangeAnni() {
    return this.httpClient.get<SelectItem[]>(this.config.getBERootUrl(false) + `/dizionario/semi/range/anni`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItem(response);})
    );
  }

  getListCertificatiAnni() {
    return this.httpClient.get<SelectItem[]>(this.config.getBERootUrl(false) + `/dizionario/certificati/list/anni`)
    .pipe(
      map((response: any[]) => {return this.fillSelectItemString(response);})
    );
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

  autocompleteLikeFilter(event, res: any, whatFilteringFor) {
    const filtered: any[] = [];
    for (let i = 0; i < res.length; i++) {
      let filtering;
      for (let j = 0; j < this.typeFiltering[whatFilteringFor].length; j++) {
        filtering = res[i][this.typeFiltering[whatFilteringFor][j]];
        if (typeof filtering === 'string') {
          if (filtering.toLowerCase().indexOf((event.query).toLowerCase()) > -1) {
            filtered.push(res[i]);
          }
        }
      }
    }
    return filtered;
  }

  filterDuplicates(array, key) {
    return (array = array.filter(
      (item, index, self) => index === self.findIndex(t => t[key] === item[key])
    ));
  }

  private getSort(paging:any){
    let sortOrder = paging.sortOrder == -1 ? '-':'+';
    return paging.sortField?'&sort='+ sortOrder + paging.sortField:'';
  }

}

