/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SoggettoModel } from "./soggetto.model";

export class BasedEconomicValueModel {
  constructor(
  public baseValue?: number,
  public superficie?: number,
  public sceltiParametri?: EconomicCalculationTableModel[],
  public totale?: number,
  public soggettoProfess?: SoggettoModel
  ) {}
}

export class EconomicCalculationTableModel {
  constructor(
    public nome?: string,
    public valore?: number
  ) {}
}
