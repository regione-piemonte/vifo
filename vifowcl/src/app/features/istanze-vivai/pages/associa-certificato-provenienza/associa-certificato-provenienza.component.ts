/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/components/common/selectitem';
import { InventarioService } from '../../services/inventario.service';

@Component({
  selector: 'app-associa-certificato-provenienza',
  templateUrl: './associa-certificato-provenienza.component.html',
  styleUrls: ['./associa-certificato-provenienza.component.css']
})
export class AssociaCertificatoProvenienzaComponent implements OnInit {

  @Input() idProvenienza: number;
  @Input() isInternalPage: boolean;
  @Output() emitIndietro = new EventEmitter();
  @Output() emitCertificato = new EventEmitter<number>();

  associaCertificatoForm: FormGroup;
  listCertificati: SelectItem[]=[];

  constructor(private fb: FormBuilder, private inventarioService :InventarioService, 
    private router: Router) { }

  ngOnInit() {
    this.initAssociaCertificatoForm(this.idProvenienza);
    this.inventarioService.getListCertificatiPiemonte()
    .subscribe((response: SelectItem[]) => {
      this.listCertificati = response;
    });
  }

  initAssociaCertificatoForm(partitaFruttiData) {
    this.associaCertificatoForm = this.fb.group({
      idProvenienza: [{ value: this.idProvenienza ? this.idProvenienza : '', disabled: false },
      [Validators.required]]
      });
  }

  editCertificato(){
    let idProv = this.associaCertificatoForm.controls['idProvenienza'].value;
    if(this.isInternalPage){
          this.emitCertificato.emit(idProv);
    }else{
      this.router.navigate(['/content/modifica-certificato/' + idProv]);
    }
  }

  newCertificato(){
    if(this.idProvenienza){
    this.emitCertificato.emit(null);
    }else{
      this.router.navigate(['/content/form-certificato']);
    }
  }

  indietro(){
    if(this.isInternalPage){
      this.emitIndietro.emit();
    }else{
      this.router.navigate(['/content/anagrafiche']);
    }
  }

  goToHome(){
    this.router.navigate(['/content']);
  }


}
