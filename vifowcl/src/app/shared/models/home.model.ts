/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class HomeModel {
  constructor(
    public codiceFiscale?: string,
    public cognome?: string,
    public nome?: string,
    public ruolo?: any,
    public profile?: any,    
    public provider?: string,
    public fkProfiloUtente? : any,
    public opzioneId?: number,
    public opzioneDescr?: string,

    public fkComune?: number[],
    public comune?: string[],
    public comuneId?: number,
    public fkSoggetto? : number,
    public indirizzo?: string,
    public nrTelefonico?: string,
    public eMail?: string,
    public civico?: string,
    public cap?: number,
    public flgEntePubblico?: number,

    public fkVivaio?: number,
    public fkProfilo?: number,

    public fkTipoAccreditamento?: string,
    public tipoAccreditamento?: string,

    public ragioneSociale?: string,

    public partitaIva?: string,
    public pec?: string,
    public numeroIscrizione?: string,
    public provinciaOrdine?: string,
    public codiceFiscaleDelega?: string
  ) {}
}
