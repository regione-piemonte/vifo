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
import { SpecieRichiestaFull } from 'src/app/shared/models/specie-richiesta-full.model';
import { DizionariService } from '../../services/dizionari.service';
import { VivaiService } from '../../services/vivai.service';

@Component({
  selector: 'app-form-specie',
  templateUrl: './form-specie.component.html',
  styleUrls: ['./form-specie.component.css']
})
export class FormSpecieComponent implements OnInit {

  specieForm: FormGroup;
  idSpecie:number;
  specie: SpecieRichiestaFull;
  listGruppiSpecie: SelectItem[]=[];

  listFamiglieSpecie: string[];
  listFamiglieSpecieFiltered: string[];

  ulrImgSpecie:string;
  ulrImgSpecieInVivaio:string;
  ulrImgSpecieDettaglio:string;
  urlIngrandisciFoto: string;
  submited: boolean = false;
  fileToUpload:File[] = [null, null, null];

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private fb: FormBuilder,private router: Router, public route: ActivatedRoute,
    private vivaiService: VivaiService, private dizionariService: DizionariService) { }

  ngOnInit() {
    this.idSpecie = this.route.snapshot.params['id'];
    this.dizionariService.getListGruppoSpecie().subscribe((response: SelectItem[]) => {
      this.listGruppiSpecie = response;
    });
    if(this.idSpecie){
      this.dizionariService.getSpecieById(this.idSpecie)
      .subscribe((res)=>{
        this.initSpecieForm(res);
      })
     
    }else{
      this.initSpecieForm(null);
    }

    this.dizionariService.getListFamiglieSpecie()
    .subscribe((res)=>{
      this.listFamiglieSpecie = res;
      this.listFamiglieSpecieFiltered = res;
    })
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

  filterFamiglia(val:string){
    this.listFamiglieSpecieFiltered = this.listFamiglieSpecie.filter((item)=>{
      return item.indexOf(val['query'].toUpperCase()) > -1;
    })
  }

  onFamigliaChange(){
    let item = this.specieForm.get('famiglia');
    item.patchValue(item.value.toUpperCase());
  }

  initSpecieForm(specieData:any) {
    this.specie = specieData;
    if(specieData && !specieData.attitudine){
      specieData.attitudine = '';
    }
    this.specieForm = this.fb.group({
      idSpecie: [{ value: specieData ? specieData.idSpecie : '', disabled: false }],
      codiceSpecie: [{ value: specieData ? specieData.codiceSpecie : '', disabled: false },
        [Validators.required]],
      nomeComune: [{ value: specieData ? specieData.nomeComune : '', disabled: false },
        [Validators.required]],
      nomeScientifico: [{ value: specieData ? specieData.nomeScientifico : '', disabled: false },
        [Validators.required]],
      flgProdotta: [{ value: specieData ? specieData.flgProdotta : false, disabled: false },
        [Validators.required]],
      flgSpecieZp: [{ value: specieData ? specieData.flgSpecieZp : false, disabled: false },
        [Validators.required]],
      flgSpecie386: [{ value: specieData ? specieData.flgSpecie386 : false, disabled: false },
        [Validators.required]],
      flgTipoControllo: [{ value: specieData ? specieData.flgTipoControllo : '', disabled: false },
        [Validators.required]],
      densita: [{ value: specieData ? specieData.densita : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      zonaOrigine: [{ value: specieData ? specieData.zonaOrigine : '', disabled: false },
        [Validators.required]],
      dimensione: [{ value: specieData ? specieData.dimensione : '', disabled: false },
        [Validators.required]],
      famiglia: [{ value: specieData ? specieData.famiglia : '', disabled: false },
        [Validators.required]],
      foglia: [{ value: specieData ? specieData.foglia : '', disabled: false },
        [Validators.required]],
      fioreFrutto: [{ value: specieData ? specieData.fioreFrutto : '', disabled: false },
        [Validators.required]],
      ecologia: [{ value: specieData ? specieData.ecologia : '', disabled: false },
        [Validators.required]],
      impiego: [{ value: specieData ? specieData.impiego : '', disabled: false },
        [Validators.required]],
      noteCaratteristiche: [{ value: specieData ? specieData.noteCaratteristiche : '', disabled: false },
        [Validators.required]],
      flgSchedaPubblicabile: [{ value: specieData ? specieData.flgSchedaPubblicabile : '', disabled: false }],
      flgSpeciePubblicabile: [{ value: specieData ? specieData.flgSpeciePubblicabile : '', disabled: false }],
      fkGruppo: [{ value: specieData ? specieData.fkGruppo : '', disabled: false },
        [Validators.required]],
      attitudineR: [{ value: specieData ? specieData.attitudine.indexOf('R') > -1:false, disabled: false }],
      attitudineA: [{ value: specieData ? specieData.attitudine.indexOf('A') > -1:false, disabled: false }],
      attitudineF: [{ value: specieData ? specieData.attitudine.indexOf('F') > -1:false, disabled: false }]

    })

    this.ulrImgSpecie = this.getUrlImg(1);
    this.ulrImgSpecieInVivaio = this.getUrlImg(2);
    this.ulrImgSpecieDettaglio = this.getUrlImg(3);

  }

  getUrlImg(tipoFoto: number){
    if(this.specie && this.specie.tipiFoto &&  this.specie.tipiFoto.indexOf('' + tipoFoto) > -1){
      return this.vivaiService.getBaseUrlImgSpecie() 
        + '/documento/download/image/' + this.specie.idSpecie + '/' + tipoFoto 
        +'?q=' + new Date().getTime();
    }else{
      return 'assets/img/no_foto.png';
    } 
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    this.router.navigate(['/content/anagrafica-specie']);
  }

  handleFileInput(id: number,files: FileList) {
    console.log('handleFileInput');
    console.log(files[0]);
    if(files && files[0]){
      this.fileToUpload[id] = files[0];
    }else{
      this.fileToUpload = null;
    }
}

  conferma(){
    if(this.specieForm.invalid){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK');
      return;
    }
    let values = JSON.parse(JSON.stringify(this.specieForm.value));
    values.attitudine='';
    if(values.attitudineR){
      values.attitudine = 'R,'
    }
    if(values.attitudineA){
      values.attitudine += 'A,'
    }
    if(values.attitudineF){
      values.attitudine += 'F,'
    }
    delete values.attitudineR;
    delete values.attitudineA;
    delete values.attitudineF;

    const formData: FormData = new FormData();
    if(this.fileToUpload[0]) formData.append('FOTO_SPECIE', this.fileToUpload[0]);
    if(this.fileToUpload[1])formData.append('FOTO_IN_VIVAIO', this.fileToUpload[1]);
    if(this.fileToUpload[2])formData.append('FOTO_DI_PARTICOLARE', this.fileToUpload[2]);
    formData.append('data', encodeURIComponent(JSON.stringify(values)));

    if(!this.idSpecie){
      this.vivaiService.saveSpecie(formData)
      .subscribe((res)=>{
        this.initSpecieForm(res);
        this.showModalMsg('Info','Specie salvata correttamente!','Termina');
      })
    }else{
      this.vivaiService.updateSpecie(formData)
      .subscribe((res)=>{
        this.initSpecieForm(res);
        this.showModalMsg('Info','Specie salvata correttamente!','Termina');
      })
    }
  }

  showFoto(id){
    if(id == '1'){
      this.urlIngrandisciFoto = this.ulrImgSpecie;
    }else if(id == '2'){
      this.urlIngrandisciFoto = this.ulrImgSpecieInVivaio;
    }else if(id == '3'){
      this.urlIngrandisciFoto = this.ulrImgSpecieDettaglio;
    }
  }

  closeDialog(){
    this.urlIngrandisciFoto = null;
  }

}
