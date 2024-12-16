/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Directive } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';
import { forbiddenValidator } from '../validators/string.validators';

@Directive({
  providers: [{
    multi: true,
    provide: NG_VALIDATORS,
    useExisting: ForbiddenDirective
  }],
  selector: '[appOneOfTwo]'
})
export class ForbiddenDirective implements Validator {

  constructor() { }

  public validate(c: AbstractControl): ValidationErrors | null {
    return forbiddenValidator('xxx')(c);
  }

}
