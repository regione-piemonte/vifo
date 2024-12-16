/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRitiroComponent } from './form-ritiro.component';

describe('FormRitiroComponent', () => {
  let component: FormRitiroComponent;
  let fixture: ComponentFixture<FormRitiroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormRitiroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormRitiroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
