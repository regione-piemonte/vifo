/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { VisualizzaComponent } from './pages/visualizza/visualizza.component';
import { FormDomandaComponent } from './pages/form-domanda/form-domanda.component';
import { FormPartitaFruttiComponent } from './pages/form-partita-frutti/form-partita-frutti.component';
import { FormPartitaPianteComponent } from './pages/form-partita-piante/form-partita-piante.component';
import { FormPartitaSemiComponent } from './pages/form-partita-semi/form-partita-semi.component';
import { FormAssegnazioneComponent} from './pages/form-assegnazione/form-assegnazione.component';
import { FormCertificatoComponent } from './pages/form-certificato/form-certificato.component';
import { RiepilogoAssegnataComponent } from './pages/riepilogo-assegnata/riepilogo-assegnata.component';
import { AuthGuard } from 'src/app/shared//auth/auth.guard';
import { VivaiShellComponent } from './pages/vivai-shell.component';
import { RicercaArchivioComponent } from './pages/ricerca-archivio/ricerca-archivio.component';
import { GestioneInventariComponent } from './pages/gestione-inventari/gestione-inventari.component';
import { InventarioFruttiComponent } from './pages/inventario-frutti/inventario-frutti.component';
import { InventarioSemiComponent } from './pages/inventario-semi/inventario-semi.component';
import { InventarioPiantineComponent } from './pages/inventario-piantine/inventario-piantine.component';
import { GestioneAnagraficheComponent } from './pages/gestione-anagrafiche/gestione-anagrafiche.component';
import { RiepilogoInviataComponent } from './pages/riepilogo-inviata/riepilogo-inviata.component';
import { RiepilogoRifiutataComponent } from './pages/riepilogo-rifiutata/riepilogo-rifiutata.component';
import { RiepilogoPreassegnataComponent } from './pages/riepilogo-preassegnata/riepilogo-preassegnata.component';
import { FormRitiroComponent } from './pages/form-ritiro/form-ritiro.component';
import { RiepilogoRitiroComponent } from './pages/riepilogo-ritiro/riepilogo-ritiro.component';
import { FormDdtComponent } from './pages/form-ddt/form-ddt.component';
import { DettaglioDdtComponent } from './pages/dettaglio-ddt/dettaglio-ddt.component';
import { RiepilogoChiusaComponent } from './pages/riepilogo-chiusa/riepilogo-chiusa.component';
import { VerificaTrasferimentoComponent } from './pages/verifica-trasferimento/verifica-trasferimento.component';
import { FormRichiestaTrasfComponent } from './pages/form-richiesta-trasf/form-richiesta-trasf.component';
import { FormNuovaAnnataInventarioComponent } from './pages/form-nuova-annata-inventario/form-nuova-annata-inventario.component';
import { AnagraficaSoggettiComponent } from './pages/anagrafica-soggetti/anagrafica-soggetti.component';
import { FormSoggettoComponent } from './pages/form-soggetto/form-soggetto.component';
import { AnagraficaSpecieComponent } from './pages/anagrafica-specie/anagrafica-specie.component';
import { FormSpecieComponent } from './pages/form-specie/form-specie.component';
import { InventarioCertificatiComponent } from './pages/inventario-certificati/inventario-certificati.component';

const routes: Routes = [
  {
    path: '',
    component: VivaiShellComponent,
    children: [
      { path: ``, component: HomePageComponent, pathMatch: 'full'},
      { path: `visualizza`, component: VisualizzaComponent, canActivate:[AuthGuard]},
      { path: `form-domanda/:tipoDomanda`, component: FormDomandaComponent, canActivate:[AuthGuard]},
      { path: `form-assegnazione/:idDomanda`, component: FormAssegnazioneComponent, canActivate:[AuthGuard]},
      { path: `form-ddt/:idDomanda`, component: FormDdtComponent, canActivate:[AuthGuard]},
      { path: `form-ddt/:numeroDdt/:annoDdt`, component: FormDdtComponent, canActivate:[AuthGuard]},
      { path: `form-frutti`, component: FormPartitaFruttiComponent, canActivate:[AuthGuard]},
      { path: `dettaglio-frutti/:idInventario/:edit`, component: FormPartitaFruttiComponent, canActivate:[AuthGuard]},
      { path: `form-semi`, component: FormPartitaSemiComponent, canActivate:[AuthGuard]},
      { path: `dettaglio-semi/:idInventario/:edit`, component: FormPartitaSemiComponent, canActivate:[AuthGuard]},
      { path: `form-piantine`, component: FormPartitaPianteComponent, canActivate:[AuthGuard]},
      { path: `dettaglio-piantine/:idInventario/:edit`, component: FormPartitaPianteComponent, canActivate:[AuthGuard]},
      { path: `form-certificato`, component: FormCertificatoComponent, canActivate:[AuthGuard]},
      { path: `modifica-certificato/:id`, component: FormCertificatoComponent, canActivate:[AuthGuard]},
      { path: `modifica-ddt/:numeroDdt/:annoDdt`, component: FormDdtComponent, canActivate:[AuthGuard]},
      { path: `dettaglio-ddt/:numeroDdt/:annoDdt`, component: DettaglioDdtComponent, canActivate:[AuthGuard]},
      { path: `modifica/:idDomanda`, component: FormDomandaComponent, canActivate:[AuthGuard]},
      { path: `domanda/:idDomanda`, component: FormDomandaComponent, canActivate:[AuthGuard]},
      { path: `riepilogo/inviata/:idDomanda`, component: RiepilogoInviataComponent, canActivate:[AuthGuard]},
      { path: `riepilogo/rifiutata/:idDomanda`, component: RiepilogoRifiutataComponent, canActivate:[AuthGuard]},
      { path: `riepilogo/preassegnata/:idDomanda`, component: RiepilogoPreassegnataComponent, canActivate:[AuthGuard]},
      { path: `riepilogo/assegnata/:idDomanda`, component: RiepilogoAssegnataComponent, canActivate:[AuthGuard]},
      { path: `riepilogo/ritiro/:idDomanda`, component: RiepilogoRitiroComponent, canActivate:[AuthGuard]},
      { path: `riepilogo/chiusa/:idDomanda`, component: RiepilogoChiusaComponent, canActivate:[AuthGuard]},
      { path: `form-ritiro/:idDomanda`, component: FormRitiroComponent, canActivate:[AuthGuard]},
      { path: `ricerca`, component: RicercaArchivioComponent, canActivate:[AuthGuard]},
      { path: `inventari`, component: GestioneInventariComponent, canActivate:[AuthGuard]},
      { path: `inventario-frutti`, component: InventarioFruttiComponent, canActivate:[AuthGuard]},
      { path: `inventario-semi`, component: InventarioSemiComponent, canActivate:[AuthGuard]},
      { path: `inventario-piantine`, component: InventarioPiantineComponent, canActivate:[AuthGuard]},
      { path: `anagrafiche`, component: GestioneAnagraficheComponent, canActivate:[AuthGuard]},
      { path: `trasferimenti`, component: VerificaTrasferimentoComponent, canActivate:[AuthGuard]},
      { path: `dettaglio-trasferimento/:id/:edit`, component: FormRichiestaTrasfComponent, canActivate:[AuthGuard]},
      { path: `form-nuova-annata-inventario`, component: FormNuovaAnnataInventarioComponent, canActivate:[AuthGuard]},
      { path: `anagrafica-soggetti`, component: AnagraficaSoggettiComponent, canActivate:[AuthGuard]},
      { path: `form-soggetto/:id`, component: FormSoggettoComponent, canActivate:[AuthGuard]},
      { path: `anagrafica-specie`, component: AnagraficaSpecieComponent, canActivate:[AuthGuard]},
      { path: `form-specie`, component: FormSpecieComponent, canActivate:[AuthGuard]},
      { path: `form-specie/:id`, component: FormSpecieComponent, canActivate:[AuthGuard]},
      //{ path: `certificati`, component: AssociaCertificatoProvenienzaComponent, canActivate:[AuthGuard]},
      { path: `certificati`, component: InventarioCertificatiComponent, canActivate:[AuthGuard]}
      

      
    ],
  },
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IstanzeVivaiRoutingModule { }
