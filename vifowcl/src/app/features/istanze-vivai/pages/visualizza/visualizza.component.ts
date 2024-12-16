/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { InstanceDomandaTable, Content } from 'src/app/shared/models/view-instance';
import { VivaiService } from '../../services/vivai.service';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { TableHeader } from 'src/app/shared/models/table-header';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { HomeModel } from 'src/app/shared/models/home.model';
import { UserModel } from 'src/app/shared/models/user.model';
import { CONST } from 'src/app/shared/constants';
import { UtilService } from '../../services/util.service';
import { ConfigService } from 'src/app/shared/services/config.service';

@Component({
  selector: 'app-visualizza',
  templateUrl: './visualizza.component.html',
  styleUrls: ['./visualizza.component.css']
})
export class VisualizzaComponent implements OnInit {
  unsubscribe$ = new Subject<void>();
  tableData: InstanceDomandaTable[] = [];
  tableHeaders: TableHeader[] = [
    {field: 'idDomanda', header: 'Numero domanda'},
    {field: 'dataInvio', header: 'Data invio'},
    {field: 'tipoDomanda', header: 'Tipo domanda'},
    {field: 'richiedente', header: 'Richiedente'},
    {field: 'vivaio', header: 'Vivaio forestale'},
    {field: 'stato', header: 'Stato'}
  ];
  paging: any;
  totalCount: number;
  first: number = 0;
  sortField:any;
  userProfile:string;


  constructor(private vivaiService: VivaiService,
    private dialogService: DialogService, 
    private utilService: UtilService,
    private configService: ConfigService,
    private router: Router) { }

  ngOnInit() {
    this.userProfile = this.utilService.getUserProfile();
    if(this.userProfile == 'RICHIEDENTE'){
      this.getUpdatedTable({page:1,limit:5});
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  getUpdatedTable(event) {
    this.paging = event;
    this.loadDomandeInArchivio(this.paging);
  }

  getRowId(event) {
  }

  deleteRow(event){
    this.dialogService.showDialog(true, 'Confermare la cancellazione della domanda?', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
        this.deleteConfirm(event.idDomanda);
      }
    });
  }

  deleteConfirm(event) {
    this.vivaiService.deleteDomanda(event).subscribe(res => {
      console.log('delete done');
      this.getUpdatedTable({page:1,limit:5});
    })
  }

  editRow(event) {
    if(event.stato == 'BOZZA'){
       this.router.navigate(['content/modifica/' + event.idDomanda]);
    }else{
      this.router.navigate([ this.configService.getPathByStato(event.stato) + event.idDomanda]);
    }
  }

  viewDomanda(event){
    if(event.stato == 'BOZZA'){
      const viewMode : boolean = true;
    this.router.navigate(['content/domanda/' + event.idDomanda, {viewMode:viewMode}]);
    }else{
      this.router.navigate([ this.configService.getPathByStato(event.stato) + event.idDomanda]);
    }
  }


  loadDomandeInArchivio(paging: any){
    this.tableData.length = 0;
    this.vivaiService.getMyDomandeInArchivio(paging).pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: Content<InstanceDomandaTable[]>) => {
      if (res.totalElements !== 0) {
        this.totalCount = res.totalElements;
        res.content.forEach((element, index) => {
          if (element.tipoDomanda == '2') {
            element.tipoDomanda = 'VENDITA';
          }else{
            element.tipoDomanda = 'GRATUITA';
          }
        });
        this.tableData = res.content;
      }
    })
  }

}
