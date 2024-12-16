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
import { PartitaFruttiModel, PartitaSemiModel } from 'src/app/shared/models/partita.model';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { SpecieModel } from 'src/app/shared/models/specie.model';
import { checkMaxValue, checkQtaNegative, checkQuantita } from 'src/app/validators/string.validators';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-form-partita-semi',
  templateUrl: './form-partita-semi.component.html',
  styleUrls: ['./form-partita-semi.component.css']
})
export class FormPartitaSemiComponent implements OnInit {

  currentYear: number = new Date().getFullYear();	
  isEdit = true;	  
  it: any = CONST.IT;
  msgPopUp: string;
  idInventario: number;
  fkInventarioFrutti: number;
  partitaSemiForm: FormGroup;
  idProvenienza: number;
  codiceCertificato: string;
  tmpCodiceCertificato: string;
  showAssociaCertificato: boolean = false;
  showEditCertificato: boolean = false;
  listSpecie: SpecieModel[]=[];
  listSpecieFiltered: SelectItem[]=[];   
  listCategoriaMateriali: SelectItem[]=[];
  disabledFieldsCertificato: boolean = false;
  partitaSemi: PartitaSemiModel;
  isPianteFather: boolean = false;
  certificatoType: string;
  certificato: CertificatoModel;
  submited: boolean = false;

  qtaFkFruttiResidua:number;
  qtaFkSemiResidua:number=99999999;
  qtaDisponibileDelta:number=0;
  qtaPerCreaPiantine=0;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private fb: FormBuilder, private inventarioService :InventarioService, 
    private router: Router, private dizionariService: DizionariService, 
    private utilService: UtilService, public route: ActivatedRoute) { }

  ngOnInit() {
   
    this.fkInventarioFrutti = this.route.snapshot.queryParams['fkInventarioFrutti'];
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
      this.inventarioService.getPartitaSemiById(this.idInventario)
      .subscribe((res:PartitaSemiModel) =>{
        this.certificatoType = res.certificatoType;
        if(res.fkInventarioFrutti == 0){
          delete res.fkInventarioFrutti;
        }
        this.partitaSemi = res;
        this.isPianteFather = res.isPianteFather;
        this.fkInventarioFrutti = res.fkInventarioFrutti;
        
        if(this.certificatoType == 'C'){
          this.idProvenienza = res.idProvenienza;
          this.codiceCertificato = res.codiceCertificato;
          if(res.qtaCertificato || res.qtaCertificato == 0){
            this.qtaFkSemiResidua = res.qtaCertificato + res.qtaCaricoKg;
          }
        }
        this.inventarioService.getPartitaSemiRiepilogoById(this.idInventario)
        .subscribe((res1) =>{
          this.qtaPerCreaPiantine = res1.qtaResiduaKg;
          this.qtaDisponibileDelta = res1.qtaResiduaKg - res.qtaCaricoKg 
          + this.getNotNullNum(res.qtaDistruzioneKg) + this.getNotNullNum(res.qtaScaricoVenditaKg);
          this.qtaDisponibileDelta = parseFloat(this.qtaDisponibileDelta.toFixed(3));
          if(res.fkInventarioFrutti){
            this.inventarioService.getPartitaFruttiRiepilogoById(this.fkInventarioFrutti)
            .subscribe((res2:any)=>{
              this.qtaFkFruttiResidua = res2.qtaResidua + res.qtaScaricoFruttiKg;
              this.initPartitaSemiForm(res);
            });
          }else{
            this.initPartitaSemiForm(res);
          }
        })
      })
    }else if(this.fkInventarioFrutti != null){
      this.inventarioService.getPartitaFruttiRiepilogoById(this.fkInventarioFrutti)
      .subscribe((res:any)=>{
        this.qtaFkFruttiResidua = res.qtaResidua
        this.tmpCodiceCertificato = res.certificatoProvenienza;
        this.inventarioService.getPartitaFruttiById(this.fkInventarioFrutti)
        .subscribe((res:PartitaFruttiModel) =>{
          this.certificatoType = res.certificatoType;
          delete res.dataCarico;
          delete res.qtaCaricoKg;
          delete res.qtaDistruzioneKg;
          delete res.qtaScaricoVenditaKg;
          res['qtaScaricoFruttiKg'] = this.qtaFkFruttiResidua;
          this.initPartitaSemiForm(res);
          if(this.certificatoType == 'C'){
            this.idProvenienza = res.idProvenienza;
            this.codiceCertificato = this.tmpCodiceCertificato;
          }
        })
      })
    }else{
      this.initPartitaSemiForm({});
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

  initPartitaSemiForm(partitaSemiData: PartitaSemiModel) {
    if(partitaSemiData && partitaSemiData.certificatoType == 'C'){
      delete partitaSemiData.codiceCertificato;
    }
    this.partitaSemiForm = this.fb.group({
      idInventarioSemi: [{ value: partitaSemiData ? partitaSemiData.idInventarioSemi : '', disabled: false }],
      fkInventarioFrutti: [{ value: partitaSemiData && partitaSemiData.fkInventarioFrutti ? partitaSemiData.fkInventarioFrutti : this.fkInventarioFrutti?this.fkInventarioFrutti:'', disabled: false }],
      idProvenienza: [{ value: partitaSemiData ? partitaSemiData.idProvenienza : '', disabled: false }],
      certificatoType: [{ value: partitaSemiData ? partitaSemiData.certificatoType : '', disabled: false },
        [Validators.required]],
      codiceCertificato: [{ value: partitaSemiData ? partitaSemiData.codiceCertificato : '', disabled: false },
      this.certificatoType =='L'?[Validators.required]:[]],
      fkSpecie: [{ value: partitaSemiData ? partitaSemiData.fkSpecie : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]], 
      dataCertificato: [{ value: partitaSemiData ? this.getData(partitaSemiData.dataCertificato) : '', disabled: false },
        this.certificatoType =='L'?[Validators.required]:[]],
      provenienza: [{ value: partitaSemiData ? partitaSemiData.provenienza : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],       
      fkCategMateriale: [{ value: partitaSemiData ? partitaSemiData.fkCategMateriale : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],
      produttore: [{ value: partitaSemiData ? partitaSemiData.produttore : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],
      dataCarico: [{ value: partitaSemiData ? this.getData(partitaSemiData.dataCarico):'', disabled: false },
        [Validators.required]],       
      qtaCaricoKg: [{ value: partitaSemiData ? this.utilService.getItNum(partitaSemiData.qtaCaricoKg) : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),
          checkMaxValue(this.qtaFkSemiResidua || this.qtaFkSemiResidua == 0?this.qtaFkSemiResidua:99999999),
          ,checkQtaNegative(this.qtaDisponibileDelta)]], 
      qtaScaricoFruttiKg: [{ value: partitaSemiData ? this.utilService.getItNum(partitaSemiData.qtaScaricoFruttiKg) : '', disabled: false },
        this.fkInventarioFrutti?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),
          checkMaxValue(this.qtaFkFruttiResidua || this.qtaFkFruttiResidua == 0?this.qtaFkFruttiResidua:99999999)]:[Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL)]],
      qtaDistruzioneKg: [{ value: partitaSemiData ? this.utilService.getItNum(partitaSemiData.qtaDistruzioneKg) : '', disabled: false },
        this.idInventario?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),
          checkQuantita(this.qtaDisponibileDelta,'qtaDistruzioneKg','qtaScaricoVenditaKg','qtaCaricoKg')]:[Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL)]],
      qtaScaricoVenditaKg: [{ value: partitaSemiData ? this.utilService.getItNum(partitaSemiData.qtaScaricoVenditaKg) : '', disabled: false },
        this.idInventario?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),
          checkQuantita(this.qtaDisponibileDelta,'qtaDistruzioneKg','qtaScaricoVenditaKg','qtaCaricoKg')]:[Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL)]]
      });
  }

  onValueChange(field){
    let obj = this.partitaSemiForm.get(field);
    obj.patchValue(this.utilService.getItNum(obj.value));
    this.checkValidity(field);
  }

  checkValidity(value:string){
    if(value !='qtaDistruzioneKg'){
      this.partitaSemiForm.controls['qtaDistruzioneKg'].updateValueAndValidity();
    }
    if(value !='qtaScaricoVenditaKg'){
      this.partitaSemiForm.controls['qtaScaricoVenditaKg'].updateValueAndValidity();
    }
  }

  isAnyTouched(items:string[]){
    for(let i in items){
      let cmp = this.partitaSemiForm.get(items[i]);
      if(cmp.dirty || cmp.touched){
        return true;
      }
    }
    return false;
  }

  getNotNullNum(val: number){
    return val?val:0;
  }

  getQtaDisponibile(){
    let qtaCarico = this.partitaSemiForm.get('qtaCaricoKg').value;
    let qtaDisp =  this.qtaDisponibileDelta + (qtaCarico?this.utilService.getNum(qtaCarico):0);
    return this.utilService.getItNum(parseFloat(qtaDisp.toFixed(3)));
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
    let values = this.partitaSemiForm.value;
    this.initPartitaSemiForm(values);
  }

  changeSpecieRadio(value){
    this.qtaFkSemiResidua = 99999999;
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
      this.partitaSemiForm.get('fkCategMateriale').patchValue(0);
    }else if(this.partitaSemiForm.get('fkCategMateriale').value == 0){
      this.partitaSemiForm.get('fkCategMateriale').patchValue('');
    }
    this.partitaSemiForm.get('fkSpecie').patchValue('');
    this.filterSpecie(value=='N' || value=='L'?true:value=='A'?false:null);
    this.certificatoType = value;
    this.regenerateForm();
  }

  filterSpecie(value){
    if(value != null){
      let aux = this.listSpecie.filter(item=>item.flgSpecie386 == value);
      this.setSpecieFiltered(aux);
    }else{
      this.setSpecieFiltered(this.listSpecie);
    }
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

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    if(this.fkInventarioFrutti && !this.idInventario){
      this.router.navigate(['/content/dettaglio-frutti/' + this.fkInventarioFrutti + '/true']);
    }else{
      this.router.navigate(['/content/inventario-semi']);
    }
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
      if(resp.qtaResidua && resp.fkUdmMateriale == 1){
        this.qtaFkSemiResidua = resp.qtaResidua;
        
        let qtaCaricoControl = this.partitaSemiForm.controls['qtaCaricoKg'];
        if(!qtaCaricoControl.value && !this.idInventario){
          qtaCaricoControl.patchValue(this.qtaFkSemiResidua);
        }

        if(this.partitaSemi && this.partitaSemi.certificatoType == 'C' 
          && this.partitaSemi.idProvenienza == resp.idProvenienza){
            this.qtaFkSemiResidua+= this.partitaSemi.qtaCaricoKg;
        }
      }
      this.fillDatiCertificato(resp);
      this.regenerateForm();
    });
  }

  fillDatiCertificato(certificato: CertificatoModel){
    this.partitaSemiForm.controls['fkSpecie'].patchValue(certificato.fkSpecie);
    this.partitaSemiForm.controls['dataCertificato'].
      patchValue(certificato.dataCertificato? new Date(certificato.dataCertificato): null);
    this.partitaSemiForm.controls['provenienza'].patchValue(certificato.provenienza);
    this.partitaSemiForm.controls['fkCategMateriale'].patchValue(certificato.fkCategMateriale);
    this.partitaSemiForm.controls['produttore'].patchValue(certificato.produttore);
  }

  conferma(){
    let values = this.partitaSemiForm.value;
    if(this.partitaSemiForm.invalid){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK');
    }else if(this.qtaFkFruttiResidua && this.utilService.getNum(values.qtaScaricoFruttiKg) > this.qtaFkFruttiResidua){
      this.showModalMsg('Attenzione',
      'La quantità scarico frutti non può essere maggiore della quantità disponible: ' 
        + this.utilService.getItNum(this.qtaFkFruttiResidua),'OK');
    
    }else{
      if(this.certificatoType =='C'){
        values.idProvenienza = this.idProvenienza;
      }
      values.qtaCaricoKg = this.getNum(values.qtaCaricoKg);
      values.qtaScaricoFruttiKg = this.getNum(values.qtaScaricoFruttiKg);
      values.qtaDistruzioneKg = this.getNum(values.qtaDistruzioneKg);
      values.qtaScaricoVenditaKg = this.getNum(values.qtaScaricoVenditaKg);
      this.inventarioService.savePartitaSemi(values)
      .subscribe((resp: any) =>{
        this.showModalMsg('Info','Partita semi salvata correttamente!','Termina');
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
    this.inventarioService.deletePartitaSemi(this.idInventario)
    .subscribe((res:any) =>{
      this.showModalMsg('Info','Partita semi eliminata correttamente!','Termina');
    })
  }

  creaPartitaPianteDaSemi(){
    this.router.navigate(['/content/form-piantine'], { queryParams: { idInventarioSemi: this.idInventario }});
  }
  
}
