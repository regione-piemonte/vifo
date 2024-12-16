/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbTypeaheadWindow } from '@ng-bootstrap/ng-bootstrap/typeahead/typeahead-window';
import { SelectItem } from 'primeng/components/common/selectitem';
import { CONST } from 'src/app/shared/constants';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { CertificatoModel } from 'src/app/shared/models/certificato.molde';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { SpecieModel } from 'src/app/shared/models/specie.model';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';
import { VivaiService } from '../../services/vivai.service';

@Component({
  selector: 'app-form-certificato',
  templateUrl: './form-certificato.component.html',
  styleUrls: ['./form-certificato.component.css']
})
export class FormCertificatoComponent implements OnInit {
  @Input() idProvenienza: number;
  @Input() isInternalPage: boolean;
  @Output() emitIndietro = new EventEmitter();
  currentYear: number = new Date().getFullYear();		  
  it: any = CONST.IT;
  submited:boolean = false;
  certificatoForm: FormGroup;
  listSpecie: SelectItem[]=[];
  listNaturaMateriali: SelectItem[]=[];
  listCategoriaMateriali: SelectItem[]=[];
  listTipoMateriali: SelectItem[]=[];
  listPopolamentoDaSeme: SelectItem[]=[];
  listOrigini: SelectItem[]=[];
  listUdmMateriale: SelectItem[]=[];
  codiceCertificatoProvenienza:string;
  qtaCertificatoUtilizzata:number=0;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;  

  constructor(private fb: FormBuilder, private inventarioService :InventarioService, 
    private dizionariService :DizionariService,private router: Router, 
    public route: ActivatedRoute, private dialogService: DialogService,
    public vivaiService: VivaiService, public utilService:UtilService) { }

  ngOnInit() {
    if(!this.isInternalPage){
      this.idProvenienza = this.route.snapshot.params['id'];
    }
    this.dizionariService.getListSpecie().subscribe((res: SpecieModel[]) => {
      const list: SelectItem[] = [];
        res.forEach(item => {
          if(item.flgSpecie386){
            list.push({
              label: item.nomeComune + ' - ' + item.nomeScientifico,
              value: item.idSpecie
            });
          }
        });
      this.listSpecie = list;
    });
    this.dizionariService.getListMaterialiDiMoltiplicazione().subscribe((response: SelectItem[]) => {
      this.listNaturaMateriali = response;
    });
    this.dizionariService.getListCategorieMateriale().subscribe((response: SelectItem[]) => {
      this.listCategoriaMateriali = response;
    });
    this.dizionariService.getListTipiMaterialeBase().subscribe((response: SelectItem[]) => {
      this.listTipoMateriali = response;
    });
    this.dizionariService.getListPopolamentoDaSeme().subscribe((response: SelectItem[]) => {
      this.listPopolamentoDaSeme = response;
    });
    this.dizionariService.getListOrigini().subscribe((response: SelectItem[]) => {
      this.listOrigini = response;
    });
    this.dizionariService.getListUdmMateriale().subscribe((response: SelectItem[]) => {
      this.listUdmMateriale = response;
    });

    if(this.idProvenienza){
      this.inventarioService.getCertificatoPiemonteById(this.idProvenienza)
      .subscribe((res: CertificatoModel) => {
        if((res.qtaResidua || res.qtaResidua == 0) && res.fkUdmMateriale < 3){
          this.qtaCertificatoUtilizzata = res.qtaMaterialeBase - res.qtaResidua
        }
        this.initCertificatoForm(res);
      });
    }else{
      this.initCertificatoForm(null);
    }
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      this.closePopUpEnd();
    }
  }

  closePopUpEnd(){
    if(this.isInternalPage){
      this.indietro();
    }else{
      this.router.navigate(['/content/certificati']);
    }
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    if(this.isInternalPage){
      this.emitIndietro.emit();
    }else{
      this.router.navigate(['/content/certificati'])
    }
  }

  initCertificatoForm(certificatoData:CertificatoModel) {
    this.certificatoForm = this.fb.group({
      numeroCertificato: [{ value: certificatoData ? certificatoData.numeroCertificato : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      flgTipoProduzione: [{ value: certificatoData ? certificatoData.flgTipoProduzione : '', disabled: false },
        [Validators.required]],
      fkNaturaMateriale: [{ value: certificatoData ? certificatoData.fkNaturaMateriale : '', disabled: false },
        [Validators.required]],
      fkSpecie: [{ value: certificatoData ? certificatoData.fkSpecie : '', disabled: false },
        [Validators.required]],
      fkCategMateriale: [{ value: certificatoData ? certificatoData.fkCategMateriale : '', disabled: false },
        [Validators.required]],
      fkTipoMateriale: [{ value: certificatoData ? certificatoData.fkTipoMateriale : '', disabled: false },
        [Validators.required]],
      destinazione: [{ value: certificatoData ? certificatoData.destinazione : '', disabled: false },
        [Validators.required]],
      fkPopolamento: [{ value: certificatoData ? certificatoData.fkPopolamento : '', disabled: false },
        [Validators.required]],
      miscuglio: [{ value: certificatoData ? certificatoData.miscuglio : '', disabled: false }],
      fkOrigine: [{ value: certificatoData ? certificatoData.fkOrigine : '', disabled: false },
        [Validators.required]],
      // descrizioneOrigine: [{ value: certificatoData ? certificatoData.descrizioneOrigine : '', disabled: false },
      //   [Validators.required]],
      provenienza: [{ value: certificatoData ? certificatoData.provenienza : '', disabled: false },
        [Validators.required]],
      produttore: [{ value: certificatoData ? certificatoData.produttore : '', disabled: false },
        [Validators.required]],
      altitudine: [{ value: certificatoData ? certificatoData.altitudine : '', disabled: false }],
      annoMaturazioneSemi: [{ value: certificatoData ? certificatoData.annoMaturazioneSemi : '', disabled: false },
        [Validators.minLength(4)]],
      qtaMaterialeBase: [{ value: certificatoData ? this.utilService.getItNum(certificatoData.qtaMaterialeBase) : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),Validators.min(this.qtaCertificatoUtilizzata)]], 
      fkUdmMateriale: [{ value: certificatoData ? certificatoData.fkUdmMateriale : '', disabled: false },
        [Validators.required]],
      flgPartitaPrecedente: [{ value: certificatoData ? certificatoData.flgPartitaPrecedente : '', disabled: false },
        [Validators.required]],
      nCertificatoPrecedente: [{ value: certificatoData ? certificatoData.nCertificatoPrecedente : '', disabled: false }],
      qtaPartitaIniziale: [{ value: certificatoData ? certificatoData.qtaPartitaIniziale : '', disabled: false },
        [Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      durataAllevamento: [{ value: certificatoData ? certificatoData.durataAllevamento : '', disabled: false }],
      flgPropagazione: [{ value: certificatoData ? certificatoData.flgPropagazione : '', disabled: false },
        [Validators.required]],
      metodoPropagazione: [{ value: certificatoData ? certificatoData.metodoPropagazione : '', disabled: false }],
      nCicliPropagazione: [{ value: certificatoData ? certificatoData.nCicliPropagazione : '', disabled: false }],
      dataCertificato: [{ value: certificatoData ? new Date(certificatoData.dataCertificato) : '', disabled: false },
        [Validators.required]],
      note: [{ value: certificatoData ? certificatoData.note : '', disabled: false }],
      origineNonAutoctoni: [{ value: certificatoData ? certificatoData.origineNonAutoctoni : '', disabled: false }]
      });
  }

  changePropVegetativa(val){
    console.log('changePropVegetativa: ' + val);
  }

  onValueChange(field){
    let obj = this.certificatoForm.get(field);
    obj.patchValue(this.utilService.getItNum(obj.value));
  }

  conferma(){
    if(!this.certificatoForm.valid){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK');
    }else{
      let values = this.certificatoForm.value;
      if(this.idProvenienza){
        values.idProvenienza = this.idProvenienza;
      }
      console.log(values);
      values.qtaMaterialeBase = this.getNum(values.qtaMaterialeBase);
      this.inventarioService.saveCertificato(values)
      .subscribe((response: any) => {
        this.idProvenienza = response;
        this.codiceCertificatoProvenienza = response.codiceCertificatoProvenienza;
        this.showModalMsg('Info','Certificato salvato correttamente con codice: ' 
         + this.codiceCertificatoProvenienza,'Termina');
      });
    }
  }

  getNum(val){
    if(val){
      val = (val + '').replace(',','.');
    }
    return val;
  }

  downloadCertificato(){
    this.vivaiService.pdfGenerator(CONST.PDF_TYPE.CERTIFICATO ,this.idProvenienza,{});
  }

}
