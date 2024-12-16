/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SezioneImportiComponent } from './sezione-importi.component';

describe('SezioneImportiComponent', () => {
  let component: SezioneImportiComponent;
  let fixture: ComponentFixture<SezioneImportiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SezioneImportiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SezioneImportiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
