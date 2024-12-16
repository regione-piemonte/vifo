/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package test;

import it.csi.aaep.aaeporch.business.Impresa;
import it.csi.aaep.aaeporch.business.OrchestratoreImplService;
import it.csi.aaep.aaeporch.business.OrchestratoreIntf;
import it.csi.aaep.aaeporch.business.Persona;
import it.csi.aaep.aaeporch.business.PersonaINFOC;

import java.util.List;



public class AaepTest {

    public static void main(String[] args) {
        OrchestratoreImplService orchestratoreImplService = new OrchestratoreImplService();
        OrchestratoreIntf aaepService = orchestratoreImplService.getOrchestratoreImplPort();
        try {
            Impresa impresa = aaepService.getDettaglioImpresa(null, "INFOC", "09205650154");
            List<Persona> pList =  aaepService.cercaPersoneInfoc(null, impresa.getCodiceFiscale(), false);
            if(!pList.isEmpty()) {
                PersonaINFOC p = (PersonaINFOC) aaepService.getDettaglioPersonaInfoc(null, pList.get(0));

                System.out.println(p.getDescrComuneNascita());
            }
//            System.out.println(impresa.getRagioneSociale());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}