/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAssegnazioneComponent } from './form-assegnazione.component';

describe('FormAssegnazioneComponent', () => {
  let component: FormAssegnazioneComponent;
  let fixture: ComponentFixture<FormAssegnazioneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAssegnazioneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAssegnazioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
