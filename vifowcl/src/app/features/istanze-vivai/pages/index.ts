/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { VivaiShellComponent } from './vivai-shell.component';
import { HomePageComponent } from './home-page/home-page.component';
import { VisualizzaComponent } from './visualizza/visualizza.component';
import { FormDomandaComponent } from './form-domanda/form-domanda.component';
import { FormAssegnazioneComponent} from './form-assegnazione/form-assegnazione.component';

export const pages: any[] = [
  VivaiShellComponent,
  HomePageComponent,
  VisualizzaComponent,
  FormDomandaComponent,
  FormAssegnazioneComponent
];
