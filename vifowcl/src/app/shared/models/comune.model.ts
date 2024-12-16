/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ComuneModel {
  constructor(
  public cap?: string,
  public codCatasto?: string,
  public codIstat?: string,
  public id?: number,
  public idProvincia?: number,
  public nome?: string
  ) {}
}
