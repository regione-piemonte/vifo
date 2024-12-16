/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/components/common/messageservice';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CONST } from 'src/app/shared/constants';
import { User } from 'src/app/shared/models/user';
import { HomeService } from 'src/app/shared/services/home.service';

@Component({
  selector: 'app-accreditamento',
  templateUrl: './accreditamento.component.html',
  styleUrls: ['./accreditamento.component.css'],
  providers: [MessageService]
})
export class AccreditamentoComponent implements OnInit {

  userForm: FormGroup;
  testUser: User;
  user: User;
  unsubscribe$ = new Subject<void>();
  flgPrivacy: number;
  isConfirmed: boolean = false;

  constructor(private fb: FormBuilder, private homeService: HomeService, 
      private messageService: MessageService, private router: Router) { }

  ngOnInit() {
    this.postUser();
  }

  getRedirectUrl(){
    var url_string = window.location.href
    var url = new URL(url_string);
    return url.searchParams.get("redirect");
  }

  postUserTmp(){
    this.testUser = {
      codiceFiscale: 'LDDPLA73P27B354T',
      cognome: 'Loddo',
      nome: 'Paolo',
      ruolo: 'ruolo',
      provider: 'provider',
    };
    this.user = this.testUser;
          this.buildUserForm();
  }
  
  postUser(){
    this.testUser = {
      codiceFiscale: 'LDDPLA73P27B354T',
      cognome: 'Loddo',
      nome: 'Paolo',
      ruolo: 'ruolo',
      provider: 'provider',
    };
    this.homeService.getUser()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: User) => {
          console.log('RESPONSE POST', response);
          this.user = response;
          this.buildUserForm();
        }
      );
  }

  buildUserForm() {
    this.userForm = this.fb.group({
      idSoggetto: [this.user ? this.user.idSoggetto : ""],
      codiceFiscale: [
        { value: this.user ? this.user.codiceFiscale : "", disabled: true },
        [Validators.required]
      ],
      cognome: [
        { value: this.user ? this.user.cognome : "", disabled: true },
        [Validators.required]
      ],
      nome: [
        { value: this.user ? this.user.nome : "", disabled: true },
        [Validators.required]
      ],
      ruolo: [
        this.user ? this.user.ruolo : ""
      ],
      provider: [
        this.user ? this.user.provider : ""
      ],
      flgPrivacy: [
        this.user ? this.user.flgPrivacy : ""
      ],
      nrTelefonico: [
        this.user ? this.user.nrTelefonico : "",
        [Validators.required]
      ],
      email: [
        this.user ? this.user.email : "",
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL)]
      ],
      idConfigUtente: [
        this.user ? this.user.idConfigUtente : ""
      ],
    });
  }

  sendUserForm() {
    const user: User = this.userForm.getRawValue();
    user.flgPrivacy = this.isConfirmed?1:0;
    user.profile = this.user.profile;
    console.log('USER', user);
    this.homeService.saveUser(user).subscribe(
      (res: any) => {
        console.log('user form sent', res);
        this.showSuccessToast();
        var c = this.getRedirectUrl();
            if(c){
              window.location.href = c;
            }else{
              this.router.navigate(['content']);
            }
      },
      error => {
        this.showErrorToast();
      }
    )
  }

  showSuccessToast() {
    this.messageService.add({ severity: 'success', summary: 'Successo', detail: 'Modulo inviato' });
  }

  showErrorToast() {
    this.messageService.add({ severity: 'error', summary: 'Errore', detail: 'Modulo non inviato' });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

}
