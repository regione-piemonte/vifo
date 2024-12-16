/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SavedSearchModel {
  constructor(
    public type?: string,
    public paging?: any,
    public values?: any
  ) {}
}
