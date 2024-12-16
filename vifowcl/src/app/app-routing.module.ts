/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';
import { AccreditamentoComponent } from './features/home/components/accreditamento/accreditamento.component';

const routes: Routes = [
  {
    path: '',
    component: AccreditamentoComponent
  },
  {
    path: 'content',
    loadChildren: './features/istanze-vivai/istanze-vivai.module#IstanzeVivaiModule'
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules,
    scrollPositionRestoration: 'enabled'}) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
