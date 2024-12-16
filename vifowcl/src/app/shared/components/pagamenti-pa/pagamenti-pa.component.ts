/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UtilService } from 'src/app/features/istanze-vivai/services/util.service';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { CONST } from '../../constants';
import { DomandaModel } from '../../models/domanda.model';
import { HomeModel } from '../../models/home.model';
import { PagamentoModel } from '../../models/pagamento.model';
import { SoggettoModel } from '../../models/soggetto.model';
import { ConfigPagamentiModel } from '../../models/configPagamenti.model';
import { codiceFiscale } from 'src/app/validators/string.validators';

@Component({
  selector: 'app-pagamenti-pa', 
  templateUrl: './pagamenti-pa.component.html',
  styleUrls: ['./pagamenti-pa.component.css']
})
export class PagamentiPAComponent implements OnInit {

  @Input() domanda:DomandaModel;
  @Output() emitBack = new EventEmitter();
  fatturaForm: FormGroup;
  codicePagamento: string;
  objPagamento:any;
  objPagamentoOK:any;
  pagatore:SoggettoModel;
  isPagatorePF:boolean; // PERSONA FISICA altrimenti GIURIDICA
  //versante:SoggettoModel;
  user : HomeModel;
  configPagamenti:ConfigPagamentiModel;

  showIstruzioniFattura: boolean = true;

  constructor(private fb: FormBuilder, private vivaiService: VivaiService,
    private utilService: UtilService) { }

  ngOnInit() {
    this.user = this.utilService.getUser();
    this.vivaiService.getSoggettiById(this.domanda.fkSoggetto)
    .subscribe((res)=>{
      this.pagatore = res;
      this.isPagatorePF = !this.pagatore.ragioneSociale;
    });
    this.vivaiService.getConfigationPagamenti()
    .subscribe((res)=>{
      this.configPagamenti = res;
    });
  }

  createfatturaForm(soggetto:SoggettoModel){
    this.fatturaForm = this.fb.group({
      codiceFiscale: [{ value:soggetto.codiceFiscale, disabled: true},[Validators.required, Validators.minLength(16),codiceFiscale()]],
      piva: [{ value:soggetto.partitaIva?soggetto.partitaIva:'', disabled: true},
        this.isPagatorePF?[]:[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.minLength(11)]],
      denominazione: [{ value:soggetto.ragioneSociale?soggetto.ragioneSociale:'', disabled: true},
        this.isPagatorePF?[]:[Validators.required]],
      nome: [{ value:soggetto.nome?soggetto.nome:'', disabled: true},
        this.isPagatorePF?[Validators.required]:[]],
      cognome: [{ value:soggetto.cognome?soggetto.cognome:'', disabled: true},
        this.isPagatorePF?[Validators.required]:[]],
      pec: [{ value:soggetto.pec?soggetto.pec:'', disabled: true}],
      indirizzo: [{ value:soggetto.indirizzo?soggetto.indirizzo:'', disabled: true},[Validators.required]],
      cap: [{ value:soggetto.cap?soggetto.cap:'', disabled: true},[Validators.required, Validators.minLength(5)]],
      comune: [{ value:soggetto.comune && soggetto.comune.nome?soggetto.comune.nome:'', disabled: true},[Validators.required]],
      codiceDestinatario: [{ value:soggetto.codiceBeneficiario?soggetto.codiceBeneficiario:'', disabled: false},
        [Validators.required,Validators.minLength(7)]]
    })
  }

  redirectToPagamento(){
    if(this.codicePagamento == this.configPagamenti.codicePagamentoFattura) {
      this.pagatore.codiceBeneficiario = this.fatturaForm.get('codiceDestinatario').value;
      delete this.pagatore.dataInserimento;
      delete this.pagatore.dataAggiornamento;
      this.vivaiService.saveSoggetto(this.pagatore).subscribe((res)=>{});
    }
    let pagamento = new PagamentoModel();
    pagamento.fkDomanda = this.domanda.idDomanda;
    pagamento.flgFattura = this.codicePagamento == this.configPagamenti.codicePagamentoFattura?1:0;
    this.vivaiService.insertPagamentoPiantine(pagamento)
    .subscribe((res)=>{
      
      this.fillOggettoPagamento(this.getDataScadenza(new Date(res.dataInserimento)));
      console.log(JSON.stringify(this.objPagamento));
      this.post(this.objPagamento,this.configPagamenti.webAppUrl);
    })
  }



  fillOggettoPagamento(dataScadenza:string){
    let origin = document.location.origin + '/vifo/private/';
    this.objPagamento = {
      cf: 'AAAAAA00B77B000F',
      ruolo: this.configPagamenti.ruoloIride,
      liv: this.configPagamenti.livelloIride,
      idProcedimento_accesso: this.configPagamenti.idProcedimento,
      idProcedimento: this.configPagamenti.idProcedimento,
      idFruitore: this.configPagamenti.idProcedimento,
      codicePagamento: this.codicePagamento,
      importo: this.domanda.totFattura,
      'pagatore.nome': this.pagatore.nome?this.pagatore.nome:'',
      'pagatore.cognome': this.pagatore.cognome?this.pagatore.cognome:'',
      'pagatore.ragioneSociale': this.pagatore.ragioneSociale?this.pagatore.ragioneSociale:'',
      'pagatore.codiceFiscale': this.pagatore.flgEntePubblico == 0?
        (this.pagatore.codiceFiscale && this.pagatore.codiceFiscale.length == 16 ?this.pagatore.codiceFiscale:''):'',
      'pagatore.piva': this.pagatore.flgEntePubblico == 0?
        (this.pagatore.partitaIva?this.pagatore.partitaIva:''):(this.pagatore.codiceFiscale?this.pagatore.codiceFiscale:''),
      'pagatore.idUnicoPagatore': this.pagatore.ragioneSociale?'G':'F',
      'pagatore.email': this.pagatore.mail?this.pagatore.mail:'',
      'versante.nome': this.pagatore.nome?this.pagatore.nome:'',
      'versante.cognome': this.pagatore.cognome?this.pagatore.cognome:'',
      'versante.ragioneSociale': this.pagatore.ragioneSociale?this.pagatore.ragioneSociale:'',
      'versante.codiceFiscale': this.pagatore.flgEntePubblico == 0?
        (this.pagatore.codiceFiscale && this.pagatore.codiceFiscale.length == 16?this.pagatore.codiceFiscale:''):'',
      'versante.piva': this.pagatore.flgEntePubblico == 0?
        (this.pagatore.partitaIva?this.pagatore.partitaIva:''):(this.pagatore.codiceFiscale?this.pagatore.codiceFiscale:''),
      'versante.idUnicoPagatore': this.pagatore.ragioneSociale?'G':'F',
      'versante.email': this.pagatore.mail?this.pagatore.mail:'',
      pageReferral: origin,
      pageUrlAnnulla: origin + '?pageAnnulla=' + this.domanda.idDomanda,
      userToken: this.domanda.idDomanda,
      oggettoPagamento: 'Domanda di assegnazione onerosa di piantine forestali n.' + this.domanda.idDomanda,
      'versamenti.tipo_1': 'Base',
      'versamenti.importo_1': '',
      dataScadenzaPagamento:dataScadenza
    }
  }

  changeFattura(value){
    if(value == 'S'){
      this.showIstruzioniFattura = true;
      this.codicePagamento = this.configPagamenti.codicePagamentoFattura
      this.createfatturaForm(this.pagatore);
    }else{
      this.showIstruzioniFattura = false;
      this.codicePagamento = this.configPagamenti.codicePagamentoCorrispettivo
      this.fatturaForm = null;
    }
  }

  post(obj: any, url: any) {
    const mapForm = document.createElement('form');
    mapForm.target = '_self';//'_blanck'
    mapForm.method = 'POST'; // or "post" if appropriate
    mapForm.action = url;
    Object.keys(obj).forEach((param) => {
      const mapInput = document.createElement('input');
      mapInput.type = 'hidden';
      mapInput.name = param;
      mapInput.setAttribute('value', obj[param]);
      mapForm.appendChild(mapInput);
    });
    document.body.appendChild(mapForm);
    mapForm.submit();
  }

  getDataScadenza(data:Date){
    data.setDate(data.getDate() + 30);
    return this.utilService.getFormatDate(data);
  }

  indietro(){
    this.emitBack.emit();
  }

}
