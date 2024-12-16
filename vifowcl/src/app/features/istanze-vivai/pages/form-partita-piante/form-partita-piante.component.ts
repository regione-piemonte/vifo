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
import { PartitaPianteModel, PartitaSemiModel } from 'src/app/shared/models/partita.model';
import { SpecieRichiestaFull } from 'src/app/shared/models/specie-richiesta-full.model';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { SpecieModel } from 'src/app/shared/models/specie.model';
import { UserModel } from 'src/app/shared/models/user.model';
import { checkMaxValue, checkQtaNegative, checkQuantita } from 'src/app/validators/string.validators';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-form-partita-piante',
  templateUrl: './form-partita-piante.component.html',
  styleUrls: ['./form-partita-piante.component.css']
})
export class FormPartitaPianteComponent implements OnInit {

  isUserVivaio:boolean = false;
  idUserVivaio: number;
  currentYear: number = new Date().getFullYear();	
  isEdit = true;	  
  it: any = CONST.IT;
  msgPopUp: string;
  idInventario: number;
  fkInventarioSemi: number;
  fkInventarioPiante: number;
  idBackPartitaSemi: number;
  idBackPartitaPiante: number;
  partitaPianteForm: FormGroup;
  idProvenienza: number;
  codiceCertificato: string;
  showAssociaCertificato: boolean = false;
  showEditCertificato: boolean = false;
  listSpecie: SpecieModel[]=[];
  listSpecieFiltered: SelectItem[]=[];  
  listCategoriaMateriali: SelectItem[]=[];  
  listVivai: SelectItem[]=[];
  listTipiAllevamento: SelectItem[]=[];
  listEta: string[];
  listEtaFiltered: string[]=[];
  disabledFieldsCertificato: boolean = false;
  partitaPiante: PartitaPianteModel;
  isPianteFather: boolean = false;
  certificatoType: string;
  certificato: CertificatoModel;
  submited: boolean = false;

  richiestaTrasferimentoPartita: any;
  numAssegnazioni:number=0;
  qtaFkSemiResidua:number=99999999;
  qtaFkPiantineResidua:number=99999999;
  qtaDisponibileDelta:number=0;
  annoRadicazioneSeme:number;
  qtaPerCreaPiantine=0;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  popUpBtn2:string;

  valuesToSave:any;

  constructor(private fb: FormBuilder, private inventarioService :InventarioService, 
    private router: Router, private dizionariService: DizionariService, 
    private utilService: UtilService, public route: ActivatedRoute) { }

  ngOnInit() {
    let user:UserModel = this.utilService.getUser();
    this.idUserVivaio = user.fkVivaio;
    this.fkInventarioSemi = this.route.snapshot.queryParams['idInventarioSemi'];
    this.fkInventarioPiante = this.route.snapshot.queryParams['idInventarioPiante'];
    this.idInventario = this.route.snapshot.params['idInventario'];
    this.isEdit = !('false' === this.route.snapshot.params['edit']);
    
    this.dizionariService.getListSpecie().subscribe((res: SpecieModel[]) => {
      this.listSpecie = res;
      this.filterSpecie(null);
    });

    this.dizionariService.getListCategorieMateriale().subscribe((response: SelectItem[]) => {
      this.listCategoriaMateriali = response;
    });

    this.dizionariService.getListVivaio().subscribe((response: SelectItem[]) => {
      this.listVivai = response;
    });

    this.dizionariService.getListTipiAllevamento().subscribe((response: SelectItem[]) => {
      this.listTipiAllevamento = response;
    });

    this.dizionariService.getListEta().subscribe((response: string[]) => {
      this.listEta = response;
      this.listEtaFiltered = response
    });

    if(this.idInventario){
      this.inventarioService.getPartitaPianteById(this.idInventario)
      .subscribe((res:PartitaPianteModel) =>{
        this.numAssegnazioni = res.numAssegnazioni;
        this.certificatoType = res.certificatoType;
        this.partitaPiante = res;
        this.isPianteFather = res.isPianteFather;
        this.fkInventarioPiante = res.fkInventarioPiante;
        this.fkInventarioSemi = res.fkInventarioSemi;        
        if(this.certificatoType == 'C'){
          this.idProvenienza = res.idProvenienza;
          this.codiceCertificato = res.codiceCertificato;
        }
        
        this.inventarioService.getRiepilogoPiantineById(this.idInventario)
        .subscribe((res1)=>{ 
          this.qtaPerCreaPiantine = res1.qtaDisponibile;
          this.qtaDisponibileDelta = res1.qtaDisponibile - res.qtaCarico + 
            this.getNotNullNum(res.qtaDistruzione) + this.getNotNullNum(res.qtaNonAssegnabile);
          if(this.fkInventarioPiante){
            this.inventarioService.getRiepilogoPiantineById(this.fkInventarioPiante)
            .subscribe((res2)=>{ 
              this.qtaFkPiantineResidua = res2.qtaDisponibile + res.qtaCarico;
              this.initPartitaPianteForm(res);
            });
          }else if(this.fkInventarioSemi){
            this.inventarioService.getPartitaSemiRiepilogoById(this.fkInventarioSemi)
            .subscribe((res3)=>{ 
              this.qtaFkSemiResidua = res3.qtaResiduaKg + res.qtaScaricoSemiKg;
              this.initPartitaPianteForm(res);
            })
          }else{
            this.initPartitaPianteForm(res);
          }
        });

      })
    }else if(this.fkInventarioPiante != null){
      this.inventarioService.getRiepilogoPiantineById(this.fkInventarioPiante)
      .subscribe((res1)=>{ 
        this.qtaFkPiantineResidua = res1.qtaDisponibile;
        this.annoRadicazioneSeme = res1.annoRadicazioneSeme;
        // if(this.partitaPianteForm){
        //     this.partitaPianteForm.get('annoRadicazioneSeme').patchValue(this.annoRadicazioneSeme);
        // }

        this.idBackPartitaPiante = this.fkInventarioPiante;
      this.inventarioService.getPartitaPianteById(this.fkInventarioPiante)
      .subscribe((res:PartitaPianteModel) =>{
        let formObj = this.getCampiEreditati(res);
        formObj['fkTipoAllevamento'] = res.fkTipoAllevamento;
        formObj['eta'] = res.eta;
        formObj['altezza'] = res.altezza;
        formObj['annoRadicazioneSeme'] = res.annoRadicazioneSeme;
        formObj['prezzoUnitario'] = res.prezzoUnitario;
        formObj['posizionePartita'] = res.posizionePartita;
        formObj['qtaCarico'] = this.qtaFkPiantineResidua;
        this.initPartitaPianteForm(formObj);
      })

      })
    }else if(this.fkInventarioSemi != null){
      this.inventarioService.getPartitaSemiRiepilogoById(this.fkInventarioSemi)
      .subscribe((res)=>{ 
        this.qtaFkSemiResidua = res.qtaResiduaKg;
        this.idBackPartitaSemi = this.fkInventarioSemi;
        this.inventarioService.getPartitaSemiById(this.fkInventarioSemi)
        .subscribe((res:PartitaSemiModel) =>{
          let formObj = this.getCampiEreditati(res);
          formObj['qtaScaricoSemiKg'] = this.qtaFkSemiResidua;
          this.initPartitaPianteForm(formObj);
        })
      })
    }else{
      this.initPartitaPianteForm({fkVivaio:this.idUserVivaio});
    }
  }

  showModalMsg(type:string, msg:string, btn1:string, btn2:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
    this.popUpBtn2 = btn2;
  }

  getNotNullNum(val: number){
    return val?val:0;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      this.indietro();
    }
  }

  isAnyTouched(items:string[]){
    for(let i in items){
      let cmp = this.partitaPianteForm.get(items[i]);
      if(cmp.dirty || cmp.touched){
        return true;
      }
    }
    return false;
  }

  getCampiEreditati(object){
    this.certificatoType = object.certificatoType;
    if(this.certificatoType == 'C'){
      this.idProvenienza = object.idProvenienza;
      this.codiceCertificato = object.codiceCertificato;
    }
    let res = {'fkCategMateriale': object.fkCategMateriale,
            'fkSpecie': object.fkSpecie,
            'produttore': object.produttore,
            'provenienza': object.provenienza,
            'codiceCertificato': object.codiceCertificato,
            'dataCertificato': object.dataCertificato,
            'certificatoType': object.certificatoType
          };
    if(this.fkInventarioPiante || this.fkInventarioSemi){
      res['idProvenienza'] = object.idProvenienza;
      res['codiceCertificato'] = object.codiceCertificato;
    }
    if(this.annoRadicazioneSeme){
      res['annoRadicazioneSeme'] = this.annoRadicazioneSeme;
    }
    return res;
  }

  getDescrizioneVivaio(){
    if(this.partitaPiante && this.partitaPiante.fkVivaio && this.listVivai){
      let vivaio = this.listVivai.filter(item => item.value == this.partitaPiante.fkVivaio);
      if(vivaio.length > 0){
        return vivaio[0].label;
      }
    }
    return 'N.D.';
  }

  filterEta(val:string){
    this.listEtaFiltered = this.listEta.filter((item)=>{
      return item.toUpperCase().indexOf(val['query'].toUpperCase()) == 0;
    })
  }

  formatTwoDecimal(value:number){
    if(value){
      let valueStr = parseFloat('' + value).toFixed(2);
      return (valueStr).replace('.',',');
    }else{
      return '';
    }
  }

  initPartitaPianteForm(partitaPianteData: PartitaPianteModel) {
    if(partitaPianteData){
      if(partitaPianteData.prezzoUnitario){
        partitaPianteData.prezzoUnitario = (this.formatTwoDecimal(+partitaPianteData.prezzoUnitario));
      }
      if(partitaPianteData.certificatoType == 'C'){
        delete partitaPianteData.codiceCertificato;
      }
      if(!partitaPianteData.fkVivaio){
        partitaPianteData.fkVivaio = this.idUserVivaio;
      }
    }
    
    this.partitaPianteForm = this.fb.group({
      idInventarioPiante: [{ value: partitaPianteData ? partitaPianteData.idInventarioPiante : '', disabled: false }],
      fkInventarioPiante: [{ value: partitaPianteData && partitaPianteData.fkInventarioPiante? 
        partitaPianteData.fkInventarioPiante : this.fkInventarioPiante, disabled: false }],
      fkInventarioSemi: [{ value: partitaPianteData && partitaPianteData.fkInventarioSemi ? 
        partitaPianteData.fkInventarioSemi : this.fkInventarioSemi, disabled: false }],
      fkVivaio: [{ value: partitaPianteData ? partitaPianteData.fkVivaio : '', disabled: false },
      [Validators.required]],
      idProvenienza: [{ value: partitaPianteData ? partitaPianteData.idProvenienza : '', disabled: false }],
      certificatoType: [{ value: partitaPianteData ? partitaPianteData.certificatoType : '', disabled: false },
        [Validators.required]],
      codiceCertificato: [{ value: partitaPianteData ? partitaPianteData.codiceCertificato : '', disabled: false },
      this.certificatoType =='L'?[Validators.required]:[]],
      fkSpecie: [{ value: partitaPianteData ? partitaPianteData.fkSpecie : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]], 
      dataCertificato: [{ value: partitaPianteData ? this.getData(partitaPianteData.dataCertificato) : '', disabled: false },
        this.certificatoType =='L'?[Validators.required]:[]],
      provenienza: [{ value: partitaPianteData ? partitaPianteData.provenienza : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],       
      fkCategMateriale: [{ value: partitaPianteData ? partitaPianteData.fkCategMateriale : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],
      produttore: [{ value: partitaPianteData ? partitaPianteData.produttore : '', disabled: false },
        this.certificatoType !='C'?[Validators.required]:[]],
       dataCarico: [{ value: partitaPianteData ? this.getData(partitaPianteData.dataCarico):'', disabled: false },
         [Validators.required]],
      fkTipoAllevamento: [{ value: partitaPianteData ? partitaPianteData.fkTipoAllevamento:'', disabled: false },
      [Validators.required]],
      eta: [{ value: partitaPianteData ? partitaPianteData.eta:'', disabled: false },
      [Validators.required]],
      altezza: [{ value: partitaPianteData ? partitaPianteData.altezza:'', disabled: false },
      [Validators.required]],
      annoRadicazioneSeme: [{ value: partitaPianteData ? partitaPianteData.annoRadicazioneSeme:'', disabled: false },
      [Validators.required, Validators.minLength(4)]],
      prezzoUnitario: [{ value: partitaPianteData ? partitaPianteData.prezzoUnitario:'', disabled: false },
      [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_2_DECIMAL)]],
      qtaScaricoSemiKg: [{ value: partitaPianteData ? this.utilService.getItNum(partitaPianteData.qtaScaricoSemiKg):'', disabled: false },
      this.fkInventarioSemi?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_3_DECIMAL),checkMaxValue(this.qtaFkSemiResidua)]:[]],
      qtaCarico: [{ value: partitaPianteData ? partitaPianteData.qtaCarico : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),checkMaxValue(this.qtaFkPiantineResidua),
          checkQtaNegative(this.qtaDisponibileDelta)]], 
      qtaNonAssegnabile: [{ value: partitaPianteData ? partitaPianteData.qtaNonAssegnabile : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
          checkQuantita(this.qtaDisponibileDelta,'qtaDistruzione','qtaNonAssegnabile','qtaCarico')]], 
      qtaDistruzione: [{ value: partitaPianteData ? partitaPianteData.qtaDistruzione : '', disabled: false },
        this.idInventario?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
          checkQuantita(this.qtaDisponibileDelta,'qtaDistruzione','qtaNonAssegnabile','qtaCarico')]:[]],
      posizionePartita: [{ value: partitaPianteData ? partitaPianteData.posizionePartita : '', disabled: false },
        this.idInventario?[Validators.required]:[]]
      });
  }

  onValueChange(field){
    let obj = this.partitaPianteForm.get(field);
    obj.patchValue(this.utilService.getItNum(obj.value));
  }

  checkValidation(value:string){
    if(value !='qtaDistruzione'){
      this.partitaPianteForm.controls['qtaDistruzione'].updateValueAndValidity();
    }
    if(value !='qtaNonAssegnabile'){
      this.partitaPianteForm.controls['qtaNonAssegnabile'].updateValueAndValidity();
    }
  }

  getQtaDisponibile(){
    let qtaCarico = this.partitaPianteForm.get('qtaCarico').value;
    return this.qtaDisponibileDelta + (qtaCarico?+qtaCarico:0);
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
    let values = this.partitaPianteForm.value;
    this.initPartitaPianteForm(values);
  }

  onPrezzoChange(event){
    let value: string = event.target.value;
    if(value.indexOf('.') > -1){
      value = value.replace('.',',');
      this.partitaPianteForm.controls['prezzoUnitario'].patchValue(value);
    }
  }

  changeSpecieRadio(value){
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
      this.partitaPianteForm.get('fkCategMateriale').patchValue(0);
    }else if(this.partitaPianteForm.get('fkCategMateriale').value == 0){
      this.partitaPianteForm.get('fkCategMateriale').patchValue('');
    }
    this.partitaPianteForm.get('fkSpecie').patchValue('');
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
    if(this.idBackPartitaPiante){
      this.router.navigate(['/content/dettaglio-piantine/' + this.idBackPartitaPiante + '/true']);
    }else if(this.idBackPartitaSemi){
      this.router.navigate(['/content/dettaglio-semi/' + this.idBackPartitaSemi + '/true']);
    }else{
      this.router.navigate(['/content/inventario-piantine']);
    }
  }

  scaricaQRcode(){
    alert('Non ancora implementato');
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
      this.fillDatiCertificato(resp);
      //
    });
  }

  fillDatiCertificato(certificato: CertificatoModel){
    this.partitaPianteForm.controls['fkSpecie'].patchValue(certificato.fkSpecie);
    this.partitaPianteForm.controls['dataCertificato'].
      patchValue(certificato.dataCertificato? new Date(certificato.dataCertificato): null);
    this.partitaPianteForm.controls['provenienza'].patchValue(certificato.provenienza);
    this.partitaPianteForm.controls['fkCategMateriale'].patchValue(certificato.fkCategMateriale);
    this.partitaPianteForm.controls['produttore'].patchValue(certificato.produttore);
  }

  conferma(){
    let values = this.partitaPianteForm.value;
    if(this.partitaPianteForm.invalid){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK',null);
    }else if(this.qtaFkSemiResidua && values.qtaScaricoSemiKg > this.qtaFkSemiResidua){
      this.showModalMsg('Attenzione',
      'La quantità scarico semi non può essere maggiore della quantità disponible: ' 
        + this.qtaFkSemiResidua,'OK',null);
    }else if(this.qtaFkPiantineResidua && values.qtaCarico > this.qtaFkPiantineResidua){
      this.showModalMsg('Attenzione',
      'La quantità di piante non può essere maggiore della quantità disponible nella parita di partenza: ' 
        + this.qtaFkPiantineResidua,'OK',null);
    }else{      
      if(this.certificatoType =='C'){
        values.idProvenienza = this.idProvenienza;
      }
      if(values && values.prezzoUnitario){
        values.prezzoUnitario = ('' + values.prezzoUnitario).replace(',','.')
      }
      if(this.certificatoType =='N' || this.certificatoType =='A'){
        values.fkCategMateriale = 0;
      }
      values.qtaScaricoSemiKg = this.getNum(values.qtaScaricoSemiKg);
      this.valuesToSave = values;
      if(this.partitaPiante && this.partitaPiante.flgTipoTrasferimento && this.partitaPiante.qtaCarico != (+values.qtaCarico)){
        this.showModalMsg('Attenzione','La presente partita deriva da un trasferimento da altro vivaio.' + 
        ' Variando la quantità totale  sarà nuovamente necessaria una validazione da parte del vivaio ' + 
        ' di origine e la partita non sarà momentaneamente visibile. Continuare?','NO','SI');
      }else{
        this.salvaPartita();
      }
    }
  }

  salvaPartita(){
    this.inventarioService.savePartitaPiante(this.valuesToSave)
    .subscribe((resp: any) =>{
      this.showModalMsg('Info','Partita piantine salvata correttamente!','Termina',null);
    })
  }

  getNum(val){
    if(val){
      val = (val + '').replace(',','.');
    }
	  return val;
  }

  eliminaPartita(){
    this.inventarioService.deletePartitaPiantine(this.idInventario)
    .subscribe((res:any) =>{
      this.showModalMsg('Info','Partita piantine eliminata correttamente!','Termina',null);
    })
  }

  creaPartitaPianteDaPiante(){
    this.router.navigate(['/content/form-piantine'], { queryParams: { idInventarioPiante: this.idInventario }});
  }

  trasferisciPartitaPiante(){
    let values = this.partitaPianteForm.value;
    let vivaioFrom: SelectItem = this.listVivai.filter(item => item.value == this.partitaPiante.fkVivaio)[0];
    let specie: SelectItem = this.listSpecieFiltered.filter(item=>item.value == values.fkSpecie)[0];
    this.richiestaTrasferimentoPartita = {
      'fkInventarioPiante':this.idInventario,
      'fkSpecie':specie.value,
      'specieName':specie.label,
      'fkVivaioFrom':vivaioFrom.value,
      'vivaioFromName':vivaioFrom.label,
      'isTrasferimento': true
    }
  }

  closeRichietaTrasferimento(isSaved:boolean){
    this.richiestaTrasferimentoPartita = null;
    if(isSaved){
      this.indietro();
    }
  }
  
}
