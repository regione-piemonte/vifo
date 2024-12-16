/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SoggettoModel {
  constructor(
  public idSoggetto?: number,
  public codiceFiscale?: string,
  public cognome?: string,
  public nome?: string,
  public ragioneSociale?: string,
  public partitaIva?: string,
  public indirizzo?: string,
  public cap?: string,
  public comune?: any,
  public telefono?: string,
  public mail?: string,
  public pec?: string,
  public codiceBeneficiario?: string,
  public flgEntePubblico?: number,
  public dataForm?: DataFormModel | string,
  public dataInserimento?: DataFormModel | string,
  public dataAggiornamento?: DataFormModel | string,
  public fkConfigUtente?: string | number
  ) {}
}

export class DataFormModel {
  constructor(
    public year?: number,
    public month?: string,
    public era?: string,
    public dayOfMonth?: number,
    public dayOfWeek?: string,
    public dayOfYear?: number,
    public leapYear?: boolean,
    public monthValue?: number,
    public chronology?: ChronologyModel | string
  ) {}
}

export class ConfigUtenteModel {
  constructor(
    public idConfigUtente?: number,
    public fkTipoProfilo?: number,
    public fkSoggetto?: number,
    public fkVivaio?: number
  ) {}
}

export class ChronologyModel {
  constructor(
    public id?: string,
    public calendarType?: string
  ) {}
}

