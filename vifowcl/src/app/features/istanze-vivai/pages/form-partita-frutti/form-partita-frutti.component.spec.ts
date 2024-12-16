/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPartitaFruttiComponent } from './form-partita-frutti.component';

describe('FormPartitaFruttiComponent', () => {
  let component: FormPartitaFruttiComponent;
  let fixture: ComponentFixture<FormPartitaFruttiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormPartitaFruttiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPartitaFruttiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
