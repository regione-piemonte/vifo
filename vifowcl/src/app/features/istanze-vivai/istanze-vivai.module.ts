/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import * as fromComponents from './components';
import * as fromPages from './pages';
import { SharedModule } from 'src/app/shared';
import { IstanzeVivaiRoutingModule } from './istanze-vivai-routing.module';
import { RiepilogoAssegnataComponent } from './pages/riepilogo-assegnata/riepilogo-assegnata.component';
import { RicercaArchivioComponent } from './pages/ricerca-archivio/ricerca-archivio.component';
import { GestioneInventariComponent } from './pages/gestione-inventari/gestione-inventari.component';
import { GestioneAnagraficheComponent } from './pages/gestione-anagrafiche/gestione-anagrafiche.component';
import { RiepilogoInviataComponent } from './pages/riepilogo-inviata/riepilogo-inviata.component';
import { FormAssegnazioneComponent } from './pages/form-assegnazione/form-assegnazione.component';
import { RiepilogoPreassegnataComponent } from './pages/riepilogo-preassegnata/riepilogo-preassegnata.component';
import { FormRitiroComponent } from './pages/form-ritiro/form-ritiro.component';
import { RiepilogoRitiroComponent } from './pages/riepilogo-ritiro/riepilogo-ritiro.component';
import { FormDdtComponent } from './pages/form-ddt/form-ddt.component';
import { DettaglioDdtComponent } from './pages/dettaglio-ddt/dettaglio-ddt.component';
import { RiepilogoChiusaComponent } from './pages/riepilogo-chiusa/riepilogo-chiusa.component';
import { InventarioFruttiComponent } from './pages/inventario-frutti/inventario-frutti.component';
import { InventarioSemiComponent } from './pages/inventario-semi/inventario-semi.component';
import { InventarioPiantineComponent } from './pages/inventario-piantine/inventario-piantine.component';
import { FormPartitaFruttiComponent } from './pages/form-partita-frutti/form-partita-frutti.component';
import { FormPartitaPianteComponent } from './pages/form-partita-piante/form-partita-piante.component';
import { FormPartitaSemiComponent } from './pages/form-partita-semi/form-partita-semi.component';
import { FormCertificatoComponent } from './pages/form-certificato/form-certificato.component';
import { AssociaCertificatoProvenienzaComponent } from './pages/associa-certificato-provenienza/associa-certificato-provenienza.component';
import { FormRichiestaTrasfComponent } from './pages/form-richiesta-trasf/form-richiesta-trasf.component';
import { VerificaTrasferimentoComponent } from './pages/verifica-trasferimento/verifica-trasferimento.component';
import { FormNuovaAnnataInventarioComponent } from './pages/form-nuova-annata-inventario/form-nuova-annata-inventario.component';
import { AnagraficaSoggettiComponent } from './pages/anagrafica-soggetti/anagrafica-soggetti.component';
import { FormSoggettoComponent } from './pages/form-soggetto/form-soggetto.component';
import { AnagraficaSpecieComponent } from './pages/anagrafica-specie/anagrafica-specie.component';
import { FormSpecieComponent } from './pages/form-specie/form-specie.component';
import { RiepilogoRifiutataComponent } from './pages/riepilogo-rifiutata/riepilogo-rifiutata.component';
import { InventarioCertificatiComponent } from './pages/inventario-certificati/inventario-certificati.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    IstanzeVivaiRoutingModule
  ],
  declarations: [
    ...fromComponents.components,
    ...fromPages.pages,
    RiepilogoAssegnataComponent,
    RicercaArchivioComponent,
    GestioneInventariComponent,
    GestioneAnagraficheComponent,
    RiepilogoInviataComponent,
    FormAssegnazioneComponent,
    RiepilogoPreassegnataComponent,
    FormRitiroComponent,
    RiepilogoRitiroComponent,
    FormDdtComponent,
    DettaglioDdtComponent,
    RiepilogoChiusaComponent,
    InventarioFruttiComponent,
    InventarioSemiComponent,
    InventarioPiantineComponent,
    FormPartitaFruttiComponent,
    FormPartitaPianteComponent,
    FormPartitaSemiComponent,
    FormCertificatoComponent,
    AssociaCertificatoProvenienzaComponent,
    FormRichiestaTrasfComponent,
    VerificaTrasferimentoComponent,
    FormNuovaAnnataInventarioComponent,
    AnagraficaSoggettiComponent,
    FormSoggettoComponent,
    AnagraficaSpecieComponent,
    FormSpecieComponent,
    RiepilogoRifiutataComponent,
    InventarioCertificatiComponent
  ],
  exports: [
    ...fromComponents.components,
    ...fromPages.pages
  ]
})
export class IstanzeVivaiModule { }
