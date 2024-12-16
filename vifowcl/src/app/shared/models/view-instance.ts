/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ViewInstance {
  constructor(
    public idIntervento?: number,
    public nrIstanzaForestale?: number,
    public dataInvio?: DataInvio,
    public richiedente?: Richidente,
    public comune?: Comune,
    public stato?: string
  ) {}
}

export class Content<T> {
  content?: T;
  totalElements?: number;
  limit?: number;
  page?: number;
}

export class DataInvio {
  constructor(
    public year?: number,
    public month?: string,
    public era?: string,
    public dayOfMonth?: number,
    public dayOfWeek?: string,
    public dayOfYear?: number,
    public leapYear?: boolean,
    public monthValue?: number,
    public chronology?: Chronology
  ) {}
}

export class IstanzaInviata {
  constructor(
    public idIstanzaIntervento?: number,
    public dataInvio?: string,
    public dataInvioFormated?: string,
    public dataVerifica?: string,
    public dataProtocollo?: string,
    public dataForm?: string,
    public annoForm?: number,
    public dataAggiornamento?: string,
    public utenteCompilatore?: string,
    public nrProtocollo?: string,
    public fkStatoIstanza?: number
  ) {}
}

export class Chronology {
  constructor(
    public id?: string,
    public calendarType?: string
  ) {}
}

export class InstanceDomandaTable {
  constructor(
    public idDomanda?: number,
    public tipoDomanda?: string,
    public dataInvio?: string,
    public richiedente?: string,
    public vivaio?: string,
    public stato?: string
  ) {}
}

export class Richidente {
  constructor(
    public idSoggeto?: number,
    public nome?: string,
    public cognome?: string,
    public codiceFiscale?: string,
    public partitaIva?: string,
    public denominazione?: string
  ) {}
}

export class Comune {
  constructor(
    public idComune?: number,
    public istatComune?: string,
    public istatProv?: string,
    public denominazioneComune?: string
  ) {}
}