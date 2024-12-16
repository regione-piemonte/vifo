/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectItem } from 'primeng/components/common/selectitem';
import { CONST } from 'src/app/shared/constants';
import { CertificatoModel } from 'src/app/shared/models/certificato.molde';
import { PartitaFruttiModel } from 'src/app/shared/models/partita.model';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { SpecieModel } from 'src/app/shared/models/specie.model';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { checkMaxValue, checkQtaNegative, checkQuantita } from 'src/app/validators/string.validators';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-form-partita-frutti',
  templateUrl: './form-partita-frutti.component.html',
  styleUrls: ['./form-partita-frutti.component.css']
})
export class FormPartitaFruttiComponent implements OnInit {
  currentYear: number = new Date().getFullYear();	
  isEdit = true;	  
  it: any = CONST.IT;
  msgPopUp: string;
  idInventario: number;
  partitaFruttiForm: FormGroup;
  idProvenienza: number;
  codiceCertificato: string;
  showAssociaCertificato: boolean = false;
  showEditCertificato: boolean = false;
  listSpecie: SpecieModel[]=[];
  listSpecieFiltered: SelectItem[]=[]; 
  listCategoriaMateriali: SelectItem[]=[];
  disabledFieldsCertificato: boolean = false;
  partitaFrutti: PartitaFruttiModel;
  isSemiFather: boolean = false;
  certificatoType: string;
  certificato: CertificatoModel;
  submited: boolean = false;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  qtaPerCreaSemi=0;

  qtaResidua:number=99999999;
  qtaDisponibileDelta:number=0;

  constructor(private fb: FormBuilder, private inventarioService :InventarioService, 
    private router: Router, private dizionariService: DizionariService, 
    private utilService: UtilService, public route: ActivatedRoute) { }

  ngOnInit() {
   
    this.idInventario = this.route.snapshot.params['idInventario'];
    this.isEdit = !('false' === this.route.snapshot.params['edit']);
    
    this.dizionariService.getListSpecie().subscribe((res: SpecieModel[]) => {
      this.listSpecie = res;
      this.filterSpecie(null);
    });

    this.dizionariService.getListCategorieMateriale().subscribe((response: SelectItem[]) => {
      this.listCategoriaMateriali = response;
    });

    if(this.idInventario){
      this.inventarioService.getPartitaFruttiById(this.idInventario)
      .subscribe((res:PartitaFruttiModel) =>{
        this.certificatoType = res.certificatoType;
        this.partitaFrutti = res;
        this.isSemiFather = res.isSemiFather;
        
        if(this.certificatoType == 'C'){
          this.idProvenienza = res.idProvenienza;
          this.codiceCertificato = res.codiceCertificato;
          if(res.qtaCertificato){
            this.qtaResidua = res.qtaCertificato + res.qtaCaricoKg;
          }
        }
        this.inventarioService.getPartitaFruttiRiepilogoById(this.idInventario)
        .subscribe((res1) =>{
          this.qtaPerCreaSemi = res1.qtaResidua;
          this.qtaDisponibileDelta = res1.qtaResidua - res.qtaCaricoKg 
            + this.getNotNullNum(res.qtaDistruzioneKg) + this.getNotNullNum(res.qtaScaricoVenditaKg);
          this.qtaDisponibileDelta = parseFloat(this.qtaDisponibileDelta.toFixed(3));
          this.initPartitaFruttiForm(res); 
        });
      })
    }else{
      this.initPartitaFruttiForm({});
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
      this.indietro();
    }
  }

  initPartitaFruttiForm(partitaFruttiData) {
    if(partitaFruttiData && partitaFruttiData.certificatoType == 'C'){
      delete partitaFruttiData.codiceCertificato;
    }
    this.partitaFruttiForm = this.fb.group({
      idInventarioFrutti: [{ value: partitaFruttiData ? partitaFruttiData.idInventarioFrutti : '', disabled: false }],
      idProvenienza: [{ value: partitaFruttiData ? partitaFruttiData.idProvenienza : '', disabled: false }],
      certificatoType: [{ value: partitaFruttiData ? partitaFruttiData.certificatoType : '', disabled: false },
        [Validators.required]],
      codiceCertificato: [{ value: partitaFruttiData ? partitaFruttiData.codiceCertificato : '', disabled: false },
      this.certificatoType =='L'?[Validators.required]:[]],
      fkSpecie: [{ value: partitaFruttiData ? partitaFruttiData.fkSpecie : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]], 
      dataCertificato: [{ value: partitaFruttiData ? this.getData(partitaFruttiData.dataCertificato) : '', disabled: false },
        this.certificatoType =='L'?[Validators.required]:[]],
      provenienza: [{ value: partitaFruttiData ? partitaFruttiData.provenienza : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],       
      fkCategMateriale: [{ value: partitaFruttiData ? partitaFruttiData.fkCategMateriale : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],
      produttore: [{ value: partitaFruttiData ? partitaFruttiData.produttore : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],
      dataCarico: [{ value: partitaFruttiData ? this.getData(partitaFruttiData.dataCarico):'', disabled: false },
        [Validators.required]],        
      qtaCaricoKg: [{ value: partitaFruttiData ? this.utilService.getItNum(partitaFruttiData.qtaCaricoKg) : '', disabled: false },
      [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),checkMaxValue(this.qtaResidua),
        checkQtaNegative(this.qtaDisponibileDelta)]],
      qtaDistruzioneKg: [{ value: partitaFruttiData ? this.utilService.getItNum(partitaFruttiData.qtaDistruzioneKg) : '', disabled: false },
        this.idInventario?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),
          checkQuantita(this.qtaDisponibileDelta,'qtaDistruzioneKg','qtaScaricoVenditaKg','qtaCaricoKg')]:[]],
      qtaScaricoVenditaKg: [{ value: partitaFruttiData ? this.utilService.getItNum(partitaFruttiData.qtaScaricoVenditaKg) : '', disabled: false },
        this.idInventario?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),
          checkQuantita(this.qtaDisponibileDelta,'qtaDistruzioneKg','qtaScaricoVenditaKg','qtaCaricoKg')]:[]]
      });
  }

  filterSpecie(value){
    if(value != null){
      let aux = this.listSpecie.filter(item=>item.flgSpecie386 == value);
      this.setSpecieFiltered(aux);
    }else{
      this.setSpecieFiltered(this.listSpecie);
    }
  }

  isAnyTouched(items:string[]){
    for(let i in items){
      let cmp = this.partitaFruttiForm.get(items[i]);
      if(cmp.dirty || cmp.touched){
        return true;
      }
    }
    return false;
  }

  onValueChange(field){
    let obj = this.partitaFruttiForm.get(field);
    obj.patchValue(this.utilService.getItNum(obj.value));
    this.checkValidity(field);
  }

  checkValidity(value:string){
    if(value !='qtaDistruzioneKg'){
      this.partitaFruttiForm.controls['qtaDistruzioneKg'].updateValueAndValidity();
    }
    if(value !='qtaScaricoVenditaKg'){
      this.partitaFruttiForm.controls['qtaScaricoVenditaKg'].updateValueAndValidity();
    }
  }

  getNotNullNum(val: number){
    return val?val:0;
  }

  getQtaDisponibile(){
    let qtaCarico = this.partitaFruttiForm.get('qtaCaricoKg').value;
    let qtaDisp =  this.qtaDisponibileDelta + (qtaCarico?this.utilService.getNum(qtaCarico):0);
    return this.utilService.getItNum(parseFloat(qtaDisp.toFixed(3)));
  }

 setSpecieFiltered(items:SpecieModel[]){
  const list: SelectItem[] = [];
    items.forEach(item => {
      list.push({
        label: item.nomeComune + ' - ' + item.nomeScientifico,
        value: item.idSpecie
      });
    });
  this.listSpecieFiltered = list;
 }

  getData(value){
    if(value){
      if(value.getFullYear){
        return value;
      }else{return new Date(value)}
    }else{
      return '';
    }
  }

  regenerateForm(){
    let values = this.partitaFruttiForm.value;
    console.log(this.partitaFruttiForm.value);
    this.initPartitaFruttiForm(values);
  }

  changeSpecieRadio(value){
    this.qtaResidua = 99999999;
    if('C' === value){
      this.disabledFieldsCertificato = true;
      this.fillDatiCertificato(this.certificato?this.certificato:{});
    }else{
      if('C' === this.certificatoType){
        this.fillDatiCertificato({});
      }
      this.disabledFieldsCertificato = false;
    }
    if('N' === value || 'A' === value){
      this.partitaFruttiForm.get('fkCategMateriale').patchValue(0);
    }else if(this.partitaFruttiForm.get('fkCategMateriale').value == 0){
      this.partitaFruttiForm.get('fkCategMateriale').patchValue('');
    }
    this.partitaFruttiForm.get('fkSpecie').patchValue('');
    this.filterSpecie(value=='N' || value=='L'?true:value=='A'?false:null);
    this.certificatoType = value;
    this.regenerateForm();
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    this.router.navigate(['/content/inventario-frutti'])
  }

  apriAnagrafica(){
    this.showEditCertificato = false;
    this.showAssociaCertificato = true;
  }

  hideAssociaCertificato(){
    this.showAssociaCertificato = false;
  }

  editCertificato(event){
    this.idProvenienza = event;
    this.showAssociaCertificato = false;
    this.showEditCertificato = true;
  }

  associaCertificato(certificato:any){
    this.idProvenienza = certificato.idProvenienza;
    this.codiceCertificato = certificato.codiceCertificatoProvenienza;
    this.showAssociaCertificato = false;

    this.inventarioService.getCertificatoPiemonteById(this.idProvenienza)
    .subscribe((resp: CertificatoModel) => {
      this.certificato = resp;
      if(resp.qtaResidua && resp.fkUdmMateriale == 2){
        this.qtaResidua = resp.qtaResidua;
        let qtaCaricoControl = this.partitaFruttiForm.controls['qtaCaricoKg'];
        if(!qtaCaricoControl.value){
          qtaCaricoControl.patchValue(this.qtaResidua);
        }
        if(this.partitaFrutti && this.partitaFrutti.certificatoType == 'C' 
          && this.partitaFrutti.idProvenienza == resp.idProvenienza){
            this.qtaResidua+= this.partitaFrutti.qtaCaricoKg;
        }
      }

      this.fillDatiCertificato(resp);
      this.regenerateForm();
    });
  }

  fillDatiCertificato(certificato: CertificatoModel){
    this.partitaFruttiForm.controls['fkSpecie'].patchValue(certificato.fkSpecie);
    this.partitaFruttiForm.controls['dataCertificato'].
      patchValue(certificato.dataCertificato? new Date(certificato.dataCertificato): null);
    this.partitaFruttiForm.controls['provenienza'].patchValue(certificato.provenienza);
    this.partitaFruttiForm.controls['fkCategMateriale'].patchValue(certificato.fkCategMateriale);
    this.partitaFruttiForm.controls['produttore'].patchValue(certificato.produttore);
  }

  conferma(){
    if(this.partitaFruttiForm.invalid){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK');
    }else{
      let values = this.partitaFruttiForm.value;
      if(this.certificatoType =='C'){
        values.idProvenienza = this.idProvenienza;
      }
      values.qtaCaricoKg = this.getNum(values.qtaCaricoKg);
      values.qtaDistruzioneKg = this.getNum(values.qtaDistruzioneKg);
      values.qtaScaricoVenditaKg = this.getNum(values.qtaScaricoVenditaKg);
      this.inventarioService.savePartitaFrutti(values)
      .subscribe((resp: any) =>{
        this.showModalMsg('Info','Partita frutti salvata correttamente!','Termina');
      })
    }
  }

  getNum(val){
    if(val){
      val = (val + '').replace(',','.');
    }
	  return val;
  }

  eliminaPartita(){
    this.inventarioService.deletePartitaFrutti(this.idInventario)
    .subscribe((res:any) =>{
      this.showModalMsg('Info','Partita frutti eliminata correttamente!','Termina');
    })
  }

  creaPartitaSemiDaFrutti(){
    this.router.navigate(['/content/form-semi'], { queryParams: { fkInventarioFrutti: this.idInventario }});
  }
  
}
