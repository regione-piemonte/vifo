/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { TextMaskModule } from 'angular2-text-mask';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ConfigService } from './shared/services/config.service';
import { SharedModule } from './shared';
import { LoaderInterceptor } from './shared/interceptors/loader.interceptor';
import { HttpErrorInterceptor } from './shared/interceptors/http-error.interceptor';
import { HttpXsrfInterceptor } from './shared/interceptors/http-xrsf.interceptor';
import { AccreditamentoComponent } from './features/home/components/accreditamento/accreditamento.component';

import {CheckboxModule} from 'primeng/checkbox';
import { ForbiddenDirective } from './directives/forbiden.directive';

@NgModule({
  declarations: [AppComponent, AccreditamentoComponent, ForbiddenDirective],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    RouterModule,
    ReactiveFormsModule,
    AppRoutingModule,
    TextMaskModule,
    SharedModule,
    CheckboxModule
  ],
  providers: [
    ConfigService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpXsrfInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
