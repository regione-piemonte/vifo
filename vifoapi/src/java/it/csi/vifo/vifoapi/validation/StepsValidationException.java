/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.validation;

import java.util.List;

public class StepsValidationException extends BaseException {

	{
		//setCodice(CodiceEnum.ADS_CONSOLIDA_VALIDATION_ERROR);
		setTesto("Validation Errore");
	}
	
	List<StepValidationErrors> stepsErrors;

	public StepsValidationException(List<StepValidationErrors> stepsErrors) {
		super();		
		this.stepsErrors = stepsErrors;
	}

	public List<StepValidationErrors> getStepsErrors() {
		return stepsErrors;
	}

	public void setStepsErrors(List<StepValidationErrors> stepsErrors) {
		this.stepsErrors = stepsErrors;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return stepsErrors;
	}
	
	
	
	
}
