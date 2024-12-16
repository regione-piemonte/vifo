/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSoggettoComponent } from './form-soggetto.component';

describe('FormSoggettoComponent', () => {
  let component: FormSoggettoComponent;
  let fixture: ComponentFixture<FormSoggettoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormSoggettoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormSoggettoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
