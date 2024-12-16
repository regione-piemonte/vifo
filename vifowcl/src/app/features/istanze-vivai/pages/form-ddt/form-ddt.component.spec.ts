/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDdtComponent } from './form-ddt.component';

describe('FormDdtComponent', () => {
  let component: FormDdtComponent;
  let fixture: ComponentFixture<FormDdtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormDdtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormDdtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
