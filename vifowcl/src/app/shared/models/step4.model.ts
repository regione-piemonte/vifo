/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Step4Model {
  constructor(
    public nonNecessaria?: boolean,
    public flgA?: boolean,
    public flgB?: boolean,
    public flgC?: boolean,
    public flgD?: boolean,
    public flgDbis?: boolean,
    public necessaria?: boolean,
    public compFisica?: boolean,
    public compMonetaria?: boolean
  ) {}
}
