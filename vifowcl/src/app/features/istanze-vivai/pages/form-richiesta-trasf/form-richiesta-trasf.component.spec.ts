/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRichiestaTrasfComponent } from './form-richiesta-trasf.component';

describe('FormRichiestaTrasfComponent', () => {
  let component: FormRichiestaTrasfComponent;
  let fixture: ComponentFixture<FormRichiestaTrasfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormRichiestaTrasfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormRichiestaTrasfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
