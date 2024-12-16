/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import * as fromComponents from './components';
import * as fromDirectives from './directives';
import * as fromPipes from './pipes';
import { FileUploadModule } from 'primeng/fileupload';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { TabViewModule } from 'primeng/tabview';
import { RadioButtonModule } from 'primeng/radiobutton';
import { KeyFilterModule } from 'primeng/keyfilter';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { MultiSelectModule } from 'primeng/multiselect';
// import { DataTableModule } from 'angular7-data-table';
// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonModule } from 'primeng/button';
import { ChartModule } from 'primeng/chart';
import { MenuModule } from 'primeng/menu';
// import { TranslateModule } from "@ngx-translate/core";
import { PaginatorModule } from 'primeng/paginator';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {AccordionModule} from 'primeng/accordion';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {TooltipModule} from 'primeng/tooltip';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {StepsModule} from 'primeng/steps';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import { ReusableFrontTableComponent } from './components/reusable-front-table/reusable-front-table.component';
import { AuthGuard } from './auth/auth.guard';
import { FooterComponent } from './components/footer/footer.component';
import { PersonaFormComponent } from './components/persona-form/persona-form.component';
import { DestinazioniFormComponent } from './components/destinazioni-form/destinazioni-form.component';
import { SpecieArboreeFormComponent } from './components/specie-arboree-form/specie-arboree-form.component';
import { SpecieArboreeComponent } from './components/specie-arboree/specie-arboree.component';
import { SpecieArboreeDettaglioComponent } from './components/specie-arboree-dettaglio/specie-arboree-dettaglio.component';
import { VisualizzaFotoComponent } from './components/visualizza-foto/visualizza-foto.component';
import { FormFileComponent } from './components/form-file/form-file.component';
import { ShowRichiedenteComponent } from './components/show-richiedente/show-richiedente.component';
import { ShowDestinazioniComponent } from './components/show-destinazioni/show-destinazioni.component';
import { ShowSpecieRichiesteComponent } from './components/show-specie-richieste/show-specie-richieste.component';
import { ShowAllegatiComponent } from './components/show-allegati/show-allegati.component';
import { ShowAssegnazioniComponent } from './components/show-assegnazioni/show-assegnazioni.component';
import { ShowRitiriComponent } from './components/show-ritiri/show-ritiri.component';
import { RisultatiRicercaComponent } from './components/risultati-ricerca/risultati-ricerca.component';
import { TableFixHeaderComponent } from './components/table-fix-header/table-fix-header.component';
import { PartiteNoSelectTableComponent } from './components/partite-no-select-table/partite-no-select-table.component';
import { DomandeNoSelectTableComponent } from './components/domande-no-select-table/domande-no-select-table.component';
import { HeaderRiepilogoComponent } from './components/header-riepilogo/header-riepilogo.component';
import { SezioneImportiComponent } from './components/sezione-importi/sezione-importi.component';
import { CertificatiNoSelectTableComponent } from './components/certificati-no-select-table/certificati-no-select-table.component';
import { AlertUtenteComponent } from './components/alert-utente/alert-utente.component';
import { PagamentiPAComponent } from './components/pagamenti-pa/pagamenti-pa.component';


const modules: any = [
 CommonModule,
 TableModule,
 ButtonModule,
 DialogModule,
 DropdownModule,
 FormsModule,
 CalendarModule,
 TabViewModule,
 RadioButtonModule,
 FileUploadModule,
 KeyFilterModule,
 AutoCompleteModule,
 MultiSelectModule,
 ChartModule,
 MenuModule,
 // DataTableModule,
 // TranslateModule,
 NgbModule,
 // FontAwesomeModule,
 ReactiveFormsModule,
 PaginatorModule,
 DropdownModule,
 InputTextareaModule,
 DialogModule,
 AccordionModule,
 BreadcrumbModule,
 TooltipModule,
 OverlayPanelModule,
 StepsModule,
];

@NgModule({
  imports: [...modules],

  declarations: [
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes,
    FooterComponent,
    PersonaFormComponent,
    DestinazioniFormComponent,
    SpecieArboreeFormComponent,
    SpecieArboreeComponent,
    SpecieArboreeDettaglioComponent,
    VisualizzaFotoComponent,
    FormFileComponent,
    ShowRichiedenteComponent,
    ShowDestinazioniComponent,
    ShowSpecieRichiesteComponent,
    ShowAllegatiComponent,
    ShowAssegnazioniComponent,
    ShowRitiriComponent,
    RisultatiRicercaComponent,
    TableFixHeaderComponent,
    PartiteNoSelectTableComponent,
    DomandeNoSelectTableComponent,
    HeaderRiepilogoComponent,
    SezioneImportiComponent,
    CertificatiNoSelectTableComponent,
    AlertUtenteComponent,
    PagamentiPAComponent
  ],

  exports: [
    ...modules,
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes
  ],

  providers:[DatePipe, AuthGuard]
})
export class SharedModule { }
