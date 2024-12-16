/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { DialogModel } from '../models/dialog.model';
import { IButton } from '../models/dialog.model';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  promptObject = new Subject<DialogModel>();

  constructor() { }

  showDialog(displayDialog?: boolean, message?: string, title?: string, buttons?: IButton[], redirectUrl?: string, callback?: Function) {
    const dialog: DialogModel = {
      showDialog: displayDialog,
      message: message,
      title: title,
      buttons: buttons,
      redirectUrl: redirectUrl,
      btnCallback: callback
    };
    return this.promptObject.next(dialog);
  }
}
