/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class UserModel {
  constructor(
  public codiceFiscale?: string,
  public cognome?: string,
  public nome?: string,
  public ruolo?: string,
  public profile?: number,
  public provider?: string,
  public fkVivaio?: number) {}
}
