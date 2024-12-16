/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DizionariService } from 'src/app/features/istanze-vivai/services/dizionari.service';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { ComuneModel } from '../../models/comune.model';
import { DestinazioneModel } from '../../models/destinazione.model';
import { TableHeader } from '../../models/table-header';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-destinazioni-form',
  templateUrl: './destinazioni-form.component.html',
  styleUrls: ['./destinazioni-form.component.css']
})
export class DestinazioniFormComponent implements OnInit {

  @Input() destinazioniForm: FormGroup;
  @Input() listDestinazioni:DestinazioneModel[];
  @Output() emitRemove = new EventEmitter<number>();
  @Output() emitAdd = new EventEmitter<DestinazioneModel>();
  comune: ComuneModel[];
  unsubscribe$ = new Subject<void>();

  tableHeaders: TableHeader[] = [
    { field: 'nomeComune', header: 'Comune'},
    { field: 'sezione', header: 'Sezione'},
    { field: 'foglio', header: 'Foglio'},
    { field: 'particella', header: 'Particella'},
    { field: 'metriLineari', header: 'Metri lineari'},
    { field: 'metriQuadri', header: 'Superficie MQ' }
];

  constructor(private dizionariService: DizionariService, private dialogService: DialogService) { }

  ngOnInit() {
  }

  deleteDestinazione(event){
    this.emitRemove.emit(event.index);
  }

  addDestinazione(): void {
    let values = this.destinazioniForm.value;
    let dest: DestinazioneModel = {
      istatComune: values.istatComune.codIstat,
      nomeComune: values.istatComune.nome,
      sezione: values.sezione?values.sezione.toUpperCase():'-',
      foglio:values.foglio,
      particella:values.particella,
      metriLineari: values.metriLineari,
      metriQuadri: values.metriQuadri
    }
    if(this.listDestinazioni.filter(e => e.istatComune === dest.istatComune && e.sezione === dest.sezione
      && e.foglio === dest.foglio && e.particella === dest.particella ).length > 0){
      this.dialogService.showDialog(true, 'Destinazione già inserita!', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log('BUTTON WORKS');
        }
      });
      return;
    }
    this.emitAdd.emit(dest);
  }

  onComuneSelect(event){
    console.log(event);
  }

  refresh(id){
    let item = this.destinazioniForm.get(id);
    item.updateValueAndValidity();
  }

  filterComune(event) {
    this.dizionariService.getComuniPiemonte().pipe(takeUntil(this.unsubscribe$)).subscribe((res: ComuneModel[]) => {
      this.comune = this.dizionariService.autocompleteFilter(event, res, 'Comune');
    });
  }

}
