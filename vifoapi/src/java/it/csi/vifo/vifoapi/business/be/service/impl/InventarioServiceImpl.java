/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.Date;
import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.DAOException;
import it.csi.vifo.vifoapi.util.ErrorResponseEnum;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.CertificatoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioFruttiDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioPiantineDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioSemiDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.ProvenienzaDAO;
import it.csi.vifo.vifoapi.business.be.service.InventarioService;
import it.csi.vifo.vifoapi.business.be.service.MailService;
import it.csi.vifo.vifoapi.dto.CertificatoProvAltro;
import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;
import it.csi.vifo.vifoapi.dto.CertificatoRiepilogo;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.InfoNuovaAnnataInventario;
import it.csi.vifo.vifoapi.dto.Inventario;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioFrutti;
import it.csi.vifo.vifoapi.dto.InventarioFruttiRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioPiantine;
import it.csi.vifo.vifoapi.dto.InventarioPiantineRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioSemi;
import it.csi.vifo.vifoapi.dto.InventarioSemiRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaFrutti;
import it.csi.vifo.vifoapi.dto.PartitaPiante;
import it.csi.vifo.vifoapi.dto.PartitaSemi;
import it.csi.vifo.vifoapi.dto.Provenienza;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.TrasferimentoPartita;
import it.csi.vifo.vifoapi.dto.enumeration.CertificatoTypeEnum;
import it.csi.vifo.vifoapi.util.PaginatedList;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;
import it.csi.vifo.vifoapi.util.mail.MailEnum;

@Component
public class InventarioServiceImpl extends BaseApiServiceImpl implements InventarioService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	InventarioFruttiDAO inventarioFruttiDAO;
	
	@Autowired
	InventarioSemiDAO inventarioSemiDAO;
	
	@Autowired
	InventarioPiantineDAO inventarioPiantineDAO;
	
	@Autowired
	CertificatoDAO certificatoDAO;
	
	@Autowired
	ProvenienzaDAO provenienzaDAO;
	
	@Autowired
	InventarioDAO inventarioDAO;
	
	@Autowired
	MailService mailService;

	@Override
	public PaginatedList<InventarioFruttiRiepilogo> getListInventarioFrutti(int page, int limit, String sort, InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioFruttiDAO.getListInventarioFrutti(page, limit, sort, filter);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}	
	}
	


	@Override
	public InventarioFruttiRiepilogo getInventarioFruttiRiepilogoById(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioFruttiDAO.getInventarioFruttiRiepilogoById(id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public CertificatoRiepilogo saveCertificatoPiemonte(CertificatoProvPiemonte certificato, ConfigUtente confUtente) throws DAOException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Provenienza prov = new Provenienza();
			prov.setIdProvenienza(certificato.getIdProvenienza());
			prov.setFkCategMateriale(certificato.getFkCategMateriale());
			prov.setFkSpecie(certificato.getFkSpecie());
			prov.setFkCategMateriale(certificato.getFkCategMateriale());
			prov.setProvenienza(certificato.getProvenienza());
			prov.setProduttore(certificato.getProduttore());
			prov.setFlgNoFiniForestali(certificato.getDestinazione().byteValue());
			prov.setFkConfigUtente(confUtente.getIdConfigUtente());
			if(certificato.getFkOrigine() != 3 && certificato.getFkOrigine() != 4) {
				certificato.setDescrizioneOrigine(null);
			}
			if(certificato.getIdProvenienza() == null) {
				int idProvenienza = provenienzaDAO.insert(prov);
				certificato.setIdProvenienza(idProvenienza);
				certificatoDAO.insertCertPiem(certificato);
			}else {
				provenienzaDAO.update(prov);
				certificatoDAO.updateCertPiem(certificato);
			}
			return certificatoDAO.getRiepilogoCertificatoById(certificato.getIdProvenienza());
		}catch (PSQLException | NullPointerException e){
			throw new DAOException("Errore dati in input non validi"+ e.getMessage());
		}
		finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public CertificatoProvPiemonte getCertificatoPiemonteById(int idProvenienza) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        CertificatoProvPiemonte cert = certificatoDAO.getCertificatoPiemonteById(idProvenienza);
	        cert.setQtaResidua(certificatoDAO.getQtaResiduaByIdProvenienza(idProvenienza));
			return cert; 
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<CertificatoRiepilogo> getListCertificatiPiemonte() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return certificatoDAO.getListCertificatiPiemonteRiepilogo();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	

	@Override
	public PaginatedList<CertificatoRiepilogo> getPaginatedCertificati(int page, int limit,
			String sort, InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return certificatoDAO.getPaginatedCertificati(page, limit, sort, filter);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public Integer savePartitaFrutta(PartitaFrutti partitaFrutti, ConfigUtente confUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			boolean isUpdate = partitaFrutti.getIdInventarioFrutti() != null;
//			if(isUpdate) {
//				PartitaFrutti partFrutti  = inventarioFruttiDAO.getPartitaFruttaById(partitaFrutti.getIdInventarioFrutti());
//				if(partFrutti.getIsSemiFather()) {
//					throw new Exception("Partita frutti non modificabile per dipendenza con partiata semi");
//				}
//			}
			if(!CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaFrutti.getCertificatoType())) {
				Provenienza provenienza = new Provenienza();
				provenienza.setIdProvenienza(partitaFrutti.getIdProvenienza());
				provenienza.setFkCategMateriale(partitaFrutti.getFkCategMateriale());
				provenienza.setFkSpecie(partitaFrutti.getFkSpecie());
				provenienza.setProduttore(partitaFrutti.getProduttore());
				provenienza.setProvenienza(partitaFrutti.getProvenienza());
				provenienza.setFkConfigUtente(confUtente.getIdConfigUtente());
				provenienza.setFlgNoFiniForestali(
						CertificatoTypeEnum.NO_FINI_FORESTALI.getValue().equals(partitaFrutti.getCertificatoType())?(byte)1:(byte)0);
				if(!isUpdate) {
					partitaFrutti.setIdProvenienza(provenienzaDAO.insert(provenienza));
				}else {
					provenienzaDAO.update(provenienza);
				}
			}
			
			if(CertificatoTypeEnum.LIBERO.getValue().equals(partitaFrutti.getCertificatoType())) {
				CertificatoProvAltro certifAltro = new CertificatoProvAltro();
				certifAltro.setCodice(partitaFrutti.getCodiceCertificato());
				certifAltro.setDataCertificato(partitaFrutti.getDataCertificato());
				certifAltro.setIdProvenienza(partitaFrutti.getIdProvenienza());
				if(!isUpdate) {
					certificatoDAO.insertCertAltro(certifAltro);
				}else {
					certificatoDAO.updateCertAltro(certifAltro);
				}
			}
		
			Inventario inventario = new Inventario();
			inventario.setDataCarico(partitaFrutti.getDataCarico());
			inventario.setFkProventienza(partitaFrutti.getIdProvenienza());
			inventario.setFkConfigUtente(confUtente.getIdConfigUtente());
			if(!isUpdate) {
				partitaFrutti.setIdInventarioFrutti(inventarioDAO.insert(inventario));
			}else {
				inventario.setIdInventario(partitaFrutti.getIdInventarioFrutti());
				inventarioDAO.update(inventario);
			}
		
			InventarioFrutti invFrutti = new InventarioFrutti();
			invFrutti.setQtaCaricoKg(partitaFrutti.getQtaCaricoKg());
			invFrutti.setQtaDistruzioneKg(partitaFrutti.getQtaDistruzioneKg());
			invFrutti.setQtaScaricoVenditaKg(partitaFrutti.getQtaScaricoVenditaKg());
			invFrutti.setIdInventarioFrutti(partitaFrutti.getIdInventarioFrutti());
			if(!isUpdate) {
				inventarioFruttiDAO.insert(invFrutti);
			}else {
				inventarioFruttiDAO.update(invFrutti);
			}
			return partitaFrutti.getIdInventarioFrutti();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PartitaFrutti getPartitaFruttaById(Integer id) throws DAOException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaFrutti partitaFrutti = inventarioFruttiDAO.getPartitaFruttaById(id);
			if(CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaFrutti.getCertificatoType())) {
				CertificatoRiepilogo  certificato = certificatoDAO.getRiepilogoCertificatoById(partitaFrutti.getIdProvenienza());
				partitaFrutti.setCodiceCertificato(certificato.getCodiceCertificatoProvenienza());
				if(certificato.getFkUdmMateriale() == 2) {
					partitaFrutti.setQtaCertificato(certificatoDAO.getQtaResiduaByIdProvenienza(partitaFrutti.getIdProvenienza()));
				}
			}
			return partitaFrutti;
		} catch (PSQLException e) {
            throw new DAOException("Errore");
        } finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public void deletePartitaFrutta(Integer idInventario) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaFrutti partitaFrutti  = inventarioFruttiDAO.getPartitaFruttaById(idInventario);
			if(partitaFrutti.getIsSemiFather()) {
				throw new Exception("Partita frutti non eliminabile per dipendenza con partiata semi");
			}
			inventarioFruttiDAO.deletePartitaFruttaById(idInventario);
			inventarioDAO.deleteById(idInventario);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public Integer savePartitaSemi(PartitaSemi partitaSemi, ConfigUtente confUtente) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			boolean isUpdate = partitaSemi.getIdInventarioSemi() != null;
//			if(isUpdate) {
//				PartitaSemi partiSemi  = inventarioSemiDAO.getPartitaSemiById(partitaSemi.getIdInventarioSemi());
//				if(partiSemi.getIsPianteFather()) {
//					throw new Exception("Partita semi non modificabile per dipendenza con partiata piantine");
//				}
//			}
			
			if(partitaSemi.getFkInventarioFrutti() == null){
				if(!CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaSemi.getCertificatoType())) {
					Provenienza provenienza = new Provenienza();
					provenienza.setIdProvenienza(partitaSemi.getIdProvenienza());
					provenienza.setFkCategMateriale(partitaSemi.getFkCategMateriale());
					provenienza.setFkSpecie(partitaSemi.getFkSpecie());
					provenienza.setProduttore(partitaSemi.getProduttore());
					provenienza.setProvenienza(partitaSemi.getProvenienza());
					provenienza.setFkConfigUtente(confUtente.getIdConfigUtente());
					provenienza.setFlgNoFiniForestali(
							CertificatoTypeEnum.NO_FINI_FORESTALI.getValue().equals(partitaSemi.getCertificatoType())?(byte)1:(byte)0);
					if(!isUpdate) {
						partitaSemi.setIdProvenienza(provenienzaDAO.insert(provenienza));
					}else {
						provenienzaDAO.update(provenienza);
					}
				}
				
				if(CertificatoTypeEnum.LIBERO.getValue().equals(partitaSemi.getCertificatoType())) {
					CertificatoProvAltro certifAltro = new CertificatoProvAltro();
					certifAltro.setCodice(partitaSemi.getCodiceCertificato());
					certifAltro.setDataCertificato(partitaSemi.getDataCertificato());
					certifAltro.setIdProvenienza(partitaSemi.getIdProvenienza());
					if(!isUpdate) {
						certificatoDAO.insertCertAltro(certifAltro);
					}else {
						certificatoDAO.updateCertAltro(certifAltro);
					}
				}
			}
					
			Inventario inventario = new Inventario();
			inventario.setDataCarico(partitaSemi.getDataCarico());
			inventario.setFkProventienza(partitaSemi.getIdProvenienza());
			inventario.setFkConfigUtente(confUtente.getIdConfigUtente());
			if(!isUpdate) {
				partitaSemi.setIdInventarioSemi(inventarioDAO.insert(inventario));
			}else {
				inventario.setIdInventario(partitaSemi.getIdInventarioSemi());
				inventarioDAO.update(inventario);
			}
		
			InventarioSemi invSemi = new InventarioSemi();
			invSemi.setIdInventarioSemi(partitaSemi.getIdInventarioSemi());
			invSemi.setFkInventarioFrutti(partitaSemi.getFkInventarioFrutti());
			invSemi.setQtaScaricoFruttiKg(partitaSemi.getQtaScaricoFruttiKg());
			invSemi.setQtaCaricoKg(partitaSemi.getQtaCaricoKg());
			invSemi.setQtaDistruzioneKg(partitaSemi.getQtaDistruzioneKg());
			invSemi.setQtaScaricoVenditaKg(partitaSemi.getQtaScaricoVenditaKg());
			if(!isUpdate) {
				inventarioSemiDAO.insert(invSemi);
			}else {
				inventarioSemiDAO.update(invSemi);
			}
			return partitaSemi.getIdInventarioSemi();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public void deletePartitaSemi(Integer idInventario) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaSemi partitaSemi  = inventarioSemiDAO.getPartitaSemiById(idInventario);
			if(partitaSemi.getIsPianteFather()) {
				throw new Exception("Partita semi non eliminabile per dipendenza con partiata piante");
			}
			inventarioSemiDAO.deletePartitaSemiById(idInventario);
			inventarioDAO.deleteById(idInventario);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		
	}

	@Override
	public PartitaSemi getPartitaSemiById(Integer id) throws PSQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaSemi partitaSemi = inventarioSemiDAO.getPartitaSemiById(id);
			if(CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaSemi.getCertificatoType())) {
				CertificatoRiepilogo  certificato = certificatoDAO.getRiepilogoCertificatoById(partitaSemi.getIdProvenienza());
				partitaSemi.setCodiceCertificato(certificato.getCodiceCertificatoProvenienza());
				if(certificato.getFkUdmMateriale() == 1) {
					partitaSemi.setQtaCertificato(certificatoDAO.getQtaResiduaByIdProvenienza(partitaSemi.getIdProvenienza()));
				}
			}
			return partitaSemi;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<InventarioSemiRiepilogo> getListInventarioSemi(int page, int limit, String sort,InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioSemiDAO.getListInventarioSemi(page, limit, sort, filter);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public InventarioSemiRiepilogo getInventarioSemiRiepilogoById(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioSemiDAO.getInventarioSemiRiepilogoById(id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<InventarioPiantineRiepilogo> getListInventarioPiante(int page, int limit, int idVivaio, String sort,InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioPiantineDAO.getListInventarioPiantine(page, limit, sort, filter);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public InventarioPiantineRiepilogo getRiepilogoPiantineById(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioPiantineDAO.getRiepilogoPiantineById(id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public Integer savePartitaPiante(PartitaPiante partitaPiante, ConfigUtente confUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			boolean isUpdate = partitaPiante.getIdInventarioPiante() != null;
//			if(isUpdate) {
//				PartitaPiante partiPiante  = inventarioPiantineDAO.getPartitaPiantineById(partitaPiante.getIdInventarioPiante());
//				if(partiPiante.getIsPianteFather()) {
//					throw new Exception("Partita piantine non modificabile per dipendenza con partiata piantine");
//				}
//			}
			
			if(partitaPiante.getFkInventarioPiante() == null && partitaPiante.getFkInventarioSemi() == null){
				if(!CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaPiante.getCertificatoType())) {
					Provenienza provenienza = new Provenienza();
					provenienza.setIdProvenienza(partitaPiante.getIdProvenienza());
					provenienza.setFkCategMateriale(partitaPiante.getFkCategMateriale());
					provenienza.setFkSpecie(partitaPiante.getFkSpecie());
					provenienza.setProduttore(partitaPiante.getProduttore());
					provenienza.setProvenienza(partitaPiante.getProvenienza());
					provenienza.setFkConfigUtente(confUtente.getIdConfigUtente());
					provenienza.setFlgNoFiniForestali(
							CertificatoTypeEnum.NO_FINI_FORESTALI.getValue().equals(partitaPiante.getCertificatoType())?(byte)1:(byte)0);
					if(!isUpdate) {
						partitaPiante.setIdProvenienza(provenienzaDAO.insert(provenienza));
					}else {
						provenienzaDAO.update(provenienza);
					}
				}
				
				if(CertificatoTypeEnum.LIBERO.getValue().equals(partitaPiante.getCertificatoType())) {
					CertificatoProvAltro certifAltro = new CertificatoProvAltro();
					certifAltro.setCodice(partitaPiante.getCodiceCertificato());
					certifAltro.setDataCertificato(partitaPiante.getDataCertificato());
					certifAltro.setIdProvenienza(partitaPiante.getIdProvenienza());
					if(!isUpdate) {
						certificatoDAO.insertCertAltro(certifAltro);
					}else {
						certificatoDAO.updateCertAltro(certifAltro);
					}
				}
			}
					
			Inventario inventario = new Inventario();
			inventario.setDataCarico(partitaPiante.getDataCarico());
			inventario.setFkProventienza(partitaPiante.getIdProvenienza());
			inventario.setFkConfigUtente(confUtente.getIdConfigUtente());
			if(!isUpdate) {
				partitaPiante.setIdInventarioPiante(inventarioDAO.insert(inventario));
			}else {
				inventario.setIdInventario(partitaPiante.getIdInventarioPiante());
				inventarioDAO.update(inventario);
			}
		
			InventarioPiantine invPiante = new InventarioPiantine();
			invPiante.setIdInventarioPiante(partitaPiante.getIdInventarioPiante());
			invPiante.setAltezza(partitaPiante.getAltezza());
			invPiante.setAnnoRadicazioneSeme(partitaPiante.getAnnoRadicazioneSeme());
			invPiante.setEta(partitaPiante.getEta());
			invPiante.setFkInventarioPiante(partitaPiante.getFkInventarioPiante());
			invPiante.setFkInventarioSemi(partitaPiante.getFkInventarioSemi());
			invPiante.setFkTipoAllevamento(partitaPiante.getFkTipoAllevamento());
			invPiante.setFkVivaio(partitaPiante.getFkVivaio());
			invPiante.setPosizionePartita(partitaPiante.getPosizionePartita());
			invPiante.setPrezzoUnitario(partitaPiante.getPrezzoUnitario());
			invPiante.setQtaCarico(partitaPiante.getQtaCarico());
			invPiante.setQtaDistruzione(partitaPiante.getQtaDistruzione());
			invPiante.setQtaNonAssegnabile(partitaPiante.getQtaNonAssegnabile());
			invPiante.setQtaScaricoSemiKg(partitaPiante.getQtaScaricoSemiKg());
			
			if(!isUpdate) {
				inventarioPiantineDAO.insert(invPiante);
			}else {
				if(inventario.getDataCarico() != null) {
					RichiestaTrasferimentoPiante  richTransf = inventarioPiantineDAO.getRichiestaTrasferimentoById(partitaPiante.getIdInventarioPiante());
					if(richTransf != null) {
						inventarioPiantineDAO.resetAccettazioneTrasferimento(partitaPiante.getIdInventarioPiante());
					}
				}
				inventarioPiantineDAO.update(invPiante);
			}
			return partitaPiante.getIdInventarioPiante();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PartitaPiante getPartitaPianteById(Integer id) throws DAOException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaPiante partitaPiante = inventarioPiantineDAO.getPartitaPiantineById(id);
			if(CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaPiante.getCertificatoType())) {
				CertificatoRiepilogo  certificato = certificatoDAO.getRiepilogoCertificatoById(partitaPiante.getIdProvenienza());
				partitaPiante.setCodiceCertificato(certificato.getCodiceCertificatoProvenienza());
			}
			return partitaPiante;
		} catch (PSQLException e) {
            throw new DAOException("Errore dati");
        } finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	@Transactional
	public void deletePartitaPiante(Integer idInventario) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaPiante partitaPiante  = inventarioPiantineDAO.getPartitaPiantineById(idInventario);
			if(partitaPiante.getIsPianteFather()) {
				throw new Exception("Partita Piante non eliminabile per dipendenza con altra partiata piante");
			}
			inventarioPiantineDAO.deletePartitaPianteById(idInventario);
			inventarioDAO.deleteById(idInventario);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public int saveRichiestaTrasferimentoPiante(RichiestaTrasferimentoPiante richiestaTrasf, ConfigUtente confUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PartitaPiante partPiante  = inventarioPiantineDAO.getPartitaPiantineById(richiestaTrasf.getFkInventarioPiante());
			boolean isUpdate = richiestaTrasf.getIdInventario() != null;
			Inventario inventario = new Inventario();
			inventario.setFkProventienza(partPiante.getIdProvenienza());
			inventario.setFkConfigUtente(confUtente.getIdConfigUtente());
			if(!isUpdate) {
				richiestaTrasf.setIdInventario(inventarioDAO.insert(inventario));
			}else {
				inventario.setIdInventario(richiestaTrasf.getIdInventario());
				PartitaPiante oldPartita = inventarioPiantineDAO.getPartitaPiantineById(richiestaTrasf.getIdInventario());
				if(!oldPartita.getQtaCarico().equals(richiestaTrasf.getQtaCarico())) {
					inventario.setDataCarico(new Date());
				}
				inventarioDAO.update(inventario);
			}
			
			InventarioPiantine invPiante = new InventarioPiantine();
			invPiante.setIdInventarioPiante(richiestaTrasf.getIdInventario());
			invPiante.setAltezza(partPiante.getAltezza());
			invPiante.setAnnoRadicazioneSeme(partPiante.getAnnoRadicazioneSeme());
			invPiante.setEta(partPiante.getEta());
			invPiante.setFkInventarioPiante(richiestaTrasf.getFkInventarioPiante());
			invPiante.setFkTipoAllevamento(partPiante.getFkTipoAllevamento());
			invPiante.setFkVivaio(richiestaTrasf.getFkVivaioTo());
			invPiante.setPrezzoUnitario(partPiante.getPrezzoUnitario());
			invPiante.setQtaCarico(richiestaTrasf.getQtaCarico());
			invPiante.setQtaNonAssegnabile(0);
			invPiante.setFlgTipoTrasferimento(richiestaTrasf.getIsTrasferimento()?"T":"R");
			invPiante.setMotivoTrasferimento(richiestaTrasf.getMotivoRichiesta());
			
			if(!isUpdate) {
				inventarioPiantineDAO.insert(invPiante);
			}else {
				inventarioPiantineDAO.update(invPiante);
			}
			mailService.sendMail(richiestaTrasf.getIdInventario(), richiestaTrasf.getIsTrasferimento()?
					MailEnum.TRASFERIMENTO_PARTITA_TIPO_T:MailEnum.TRASFERIMENTO_PARTITA_TIPO_R);
			return richiestaTrasf.getIdInventario();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<TrasferimentoPartita> getListTrasferimentoPiantine(int page, int limit, String sort, ConfigUtente confUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PaginatedList<TrasferimentoPartita> res = inventarioPiantineDAO.getListTrasferimentoPiantine(page, limit, sort, confUtente.getFkVivaio());
			List<TrasferimentoPartita> list = res.getContent();
			for(TrasferimentoPartita item: list) {
				if("R".equals(item.getFlgTipoTrasferimento())) {
					item.setVivaioNotificaDescr(item.getVivaioDestinazioneDescr());
					if(confUtente.getFkVivaio() > 0 && item.getFkVivaioDestinazione() != confUtente.getFkVivaio()){
						item.setTipoNotifica("RICHIESTA DA PARTE DI UN ALTRO VIVAIO DI UNA PARTITA DEL PROPRIO VIVAIO");
						item.setStato("DEVI VALIDARE");
					}else if(confUtente.getFkVivaio() > 0 && item.getFkVivaioDestinazione() == confUtente.getFkVivaio()){
						item.setTipoNotifica("RICHIESTA DA PARTE DEL PROPRIO VIVAIO DI UNA PARTITA DI UN ALTRO VIVAIO");
						item.setStato("DEVI ATTENDERE LA VALIDAZIONE ");
					}else if(confUtente.getFkTipoProfilo() == ProfiloUtenteEnum.GESTORE.getValue()) {
						item.setTipoNotifica("RICHIESTA DA PARTE DI UN VIVAIO DI UNA PARTITA DI UN ALTRO VIVAIO");
						item.setStato("IN ATTESA DI VALIDAZIONE");
					}
				}else if("T".equals(item.getFlgTipoTrasferimento())) {
					item.setVivaioNotificaDescr(item.getVivaioOrigineDescr());
					if(confUtente.getFkVivaio() > 0 && item.getFkVivaioDestinazione() == confUtente.getFkVivaio()){
						item.setTipoNotifica("TRASFERIMENTO NEL PROPRIO VIVAIO DI UNA PARTITA DI UN ALTRO VIVAIO");
						item.setStato("DEVI VALIDARE");
					}else if(confUtente.getFkVivaio() > 0 && item.getFkVivaioDestinazione() != confUtente.getFkVivaio()){
						item.setTipoNotifica("TRASFERIMENTO AD ALTRO VIVAIO DI UNA PARTITA DEL PROPRIO VIVAIO");
						item.setStato("DEVI ATTENDERE LA VALIDAZIONE ");
					}else if(confUtente.getFkTipoProfilo() == ProfiloUtenteEnum.GESTORE.getValue()) {
						item.setTipoNotifica("TRASFERIMENTO DA PARTE DI UN VIVAIO DI UNA PARTITA AD UN ALTRO VIVAIO");
						item.setStato("IN ATTESA DI VALIDAZIONE");
					}
				}
				
			}
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int getCountTrasferimentoPiantine(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioPiantineDAO.getCountTrasferimentoPiantine(idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public RichiestaTrasferimentoPiante getRichiestaTrasferimentoById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioPiantineDAO.getRichiestaTrasferimentoById(id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int accettaRifiutaTrasferimento(RichiestaTrasferimentoPiante richiesta) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        int res = inventarioPiantineDAO.accettaRifiutaTrasferimento(richiesta);
	        if("S".equals(richiesta.getFlgValid())) {
	        	mailService.sendMail(richiesta.getIdInventario(), richiesta.getIsTrasferimento()?
						MailEnum.TRASFERIMENTO_PARTITA_TIPO_T_ACCETTAZIONE:MailEnum.TRASFERIMENTO_PARTITA_TIPO_R_ACCETTAZIONE);
	        }else if("N".equals(richiesta.getFlgValid())) {
	        	mailService.sendMail(richiesta.getIdInventario(), richiesta.getIsTrasferimento()?
						MailEnum.TRASFERIMENTO_PARTITA_TIPO_T_RIFIUTO:MailEnum.TRASFERIMENTO_PARTITA_TIPO_R_RIFIUTO);
	        }	        
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public InfoNuovaAnnataInventario getInfoAnnataInventario(int idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return inventarioPiantineDAO.getInfoAnnataInventario(idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	@Transactional
	public int creaNuovaAnnataInventario(int idVivaio, ConfigUtente confUtente) {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        
			List<PartitaPiante> listPartitePiante = inventarioPiantineDAO.getListPartitaPiantineDisponibili(idVivaio);
		
			for(PartitaPiante partitaPiante : listPartitePiante) {
				
				if(partitaPiante.getFkInventarioPiante() == null && partitaPiante.getFkInventarioSemi() == null){
					if(!CertificatoTypeEnum.CALCOLATO.getValue().equals(partitaPiante.getCertificatoType())) {
						Provenienza provenienza = new Provenienza();
						provenienza.setIdProvenienza(partitaPiante.getIdProvenienza());
						provenienza.setFkCategMateriale(partitaPiante.getFkCategMateriale());
						provenienza.setFkSpecie(partitaPiante.getFkSpecie());
						provenienza.setProduttore(partitaPiante.getProduttore());
						provenienza.setProvenienza(partitaPiante.getProvenienza());
						provenienza.setFkConfigUtente(confUtente.getIdConfigUtente());
						provenienza.setFlgNoFiniForestali(
								CertificatoTypeEnum.NO_FINI_FORESTALI.getValue().equals(partitaPiante.getCertificatoType())?(byte)1:(byte)0);
						partitaPiante.setIdProvenienza(provenienzaDAO.insert(provenienza));
			
					}
					
					if(CertificatoTypeEnum.LIBERO.getValue().equals(partitaPiante.getCertificatoType())) {
						CertificatoProvAltro certifAltro = new CertificatoProvAltro();
						certifAltro.setCodice(partitaPiante.getCodiceCertificato());
						certifAltro.setDataCertificato(partitaPiante.getDataCertificato());
						certifAltro.setIdProvenienza(partitaPiante.getIdProvenienza());
						certificatoDAO.insertCertAltro(certifAltro);
					}
				}
				InventarioPiantine invPiante2 = new InventarioPiantine();
				invPiante2.setIdInventarioPiante(partitaPiante.getIdInventarioPiante());
				invPiante2.setQtaNonAssegnabile(0);
				inventarioPiantineDAO.updateQuantitaNonAssegnabile(invPiante2);

				Inventario inventario = new Inventario();
				inventario.setDataCarico(new Date());
				inventario.setFkProventienza(partitaPiante.getIdProvenienza());
				inventario.setFkConfigUtente(confUtente.getIdConfigUtente());	
				partitaPiante.setFkInventarioPiante(partitaPiante.getIdInventarioPiante());
				partitaPiante.setIdInventarioPiante(inventarioDAO.insert(inventario));
				
				InventarioPiantine invPiante = new InventarioPiantine();
				invPiante.setIdInventarioPiante(partitaPiante.getIdInventarioPiante());
				invPiante.setFkInventarioPiante(partitaPiante.getFkInventarioPiante());
				invPiante.setFkVivaio(partitaPiante.getFkVivaio());
				invPiante.setFkTipoAllevamento(partitaPiante.getFkTipoAllevamento());
				invPiante.setEta(partitaPiante.getEta());
				invPiante.setAltezza(partitaPiante.getAltezza());
				invPiante.setAnnoRadicazioneSeme(partitaPiante.getAnnoRadicazioneSeme());
				invPiante.setPrezzoUnitario(partitaPiante.getPrezzoUnitario());
				invPiante.setPosizionePartita(partitaPiante.getPosizionePartita());
				invPiante.setQtaCarico(partitaPiante.getQtaCarico());


				inventarioPiantineDAO.insert(invPiante);
			}
			return listPartitePiante.size();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}



	@Override
	public Double getQtaResiduaByIdProvenienza(int idProvenienza) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return certificatoDAO.getQtaResiduaByIdProvenienza(idProvenienza);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	
}
