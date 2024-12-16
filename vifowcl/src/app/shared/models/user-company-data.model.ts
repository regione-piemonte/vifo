/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel } from "./comune.model";

export class UserCompanyDataModel {
  constructor(
/*   public idSoggetto?: number,
  public fkConfigUtente?: number,
 */
  public tipoIstanzaId?: number,
  public tipoIstanzaDescr?: string,

  /* public idIstanzaIntervento?: number, */

  public personaDatiOption?: string,

  public nome?: string,
  public cognome?: string,
  public codiceFiscale?: string,
  public comune?: ComuneModel,
  public indirizzo?: string,
  public civico?: string,
  public cap?: string,
  public telefono?: string,
  public mail?: string,

  public ragioneSociale?: string,
  public partitaIva?: string,
  public pec?: string,
  public ownerCodiceFiscale?: string,
  public fkComune?: any,
  ) {}
}



