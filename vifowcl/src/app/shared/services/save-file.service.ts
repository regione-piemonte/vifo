/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import * as fileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class SaveFileService {

  constructor() { }

  saveFile(data: any, filename?: string) {
    fileSaver.saveAs(data.body, filename);
  }
}
