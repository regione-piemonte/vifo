/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class UserChoiceModel {
  constructor(
  public tipoIstanzaId?: number,
  public tipoIstanzaDescr?: string,
  public fkTipoAccreditamento?: string,

/*   public idSoggetto?: number,
  public idConfigUtente?: number, */

  public partitaIva?: string,
  public pec?: string,
  public provinciaOrdine?: string,
  public numeroIscrizione?: string,
  public codiceFiscaleDelega?: string
  ) {}
}



