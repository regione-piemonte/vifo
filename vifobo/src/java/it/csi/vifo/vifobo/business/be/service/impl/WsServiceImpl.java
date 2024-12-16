/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifobo.business.be.service.WsService;
import it.csi.vifo.vifobo.business.be.service.helper.LimmAmmEnteServiceHelper;
import it.csi.vifo.vifobo.business.be.thread.UpdateComuniRunnable;
import it.csi.vifo.vifobo.dto.Comune;
import it.csi.vifo.vifobo.dto.Provincia;

@Component
public class WsServiceImpl extends BaseApiServiceImpl implements WsService {
	
	private static List<Provincia>listProvincePiemonte;
	private static List<Provincia>listProvinceItalia;
	private static List<Comune> listComuniPiemonte;
	private static List<Comune> listComuniItalia;
	private static Map<String,List<Comune>> mapComuniPerProv;
	public static Map<String,Comune> mapComuniPerIstat = new HashMap<String,Comune>();
	public static Map<String,Comune> mapComuniPiemontePerIstat = new HashMap<String,Comune>();
	public static Map<Long,Provincia> mapProvincePiemontePerId = new HashMap<Long,Provincia>();
	private static Long timestampUpdate = 0L;
	private static final Long timeRefreshData = 24*60*60*1000L;
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	private LimmAmmEnteServiceHelper limmAmmEnteServiceHelper;

	@Override
	public List<Provincia> getListProvince() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			loadData();
			return listProvinceItalia;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Provincia> getListProvincePiemonte() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			loadData();
			return listProvincePiemonte;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Comune> getListComuni() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			loadData();
			return listComuniItalia;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Comune> getListComuniPiemonte() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			loadData();
			return listComuniPiemonte;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Comune> getListComuniByIstatProv(String istatProv) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        loadData();
	        return mapComuniPerProv.get(istatProv);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Comune getComuneByIstat(String istat) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return limmAmmEnteServiceHelper.cercaComunePerCodiceIstat(istat);
		} catch (ClassNotFoundException | ServiceException | IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private void loadData() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			if(listProvincePiemonte == null) {
				update();
			}else if((new Date()).getTime() > timestampUpdate + timeRefreshData) {
				Thread t = new Thread(new UpdateComuniRunnable(this));
				   t.start();
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void update() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			timestampUpdate = (new Date()).getTime();
			listProvincePiemonte = Arrays.asList(limmAmmEnteServiceHelper.cercaProvinceDelPiemonte());
			
			listProvinceItalia = Arrays.asList(limmAmmEnteServiceHelper.cercaProvince());
			
			listComuniPiemonte = new ArrayList<Comune>();
			mapComuniPerProv = new HashMap<String,List<Comune>>();
			mapProvincePiemontePerId = new HashMap<Long,Provincia>();
			Map<String,Comune> mapComuniIstat = new HashMap<String,Comune>();
			for(Provincia prov : listProvincePiemonte) {
				mapProvincePiemontePerId.put(prov.getId(),prov);
				mapComuniPerProv.put(prov.getCodIstat(),
						Arrays.asList(limmAmmEnteServiceHelper.cercaComuniPerCodiceIstatProvincia(prov.getCodIstat())));
				listComuniPiemonte.addAll(mapComuniPerProv.get(prov.getCodIstat()));
			}
			
			mapComuniPiemontePerIstat = new HashMap<String,Comune>();
			for(Comune item: listComuniPiemonte) {
				mapComuniPiemontePerIstat.put(item.getCodIstat(), item);
			}
			Collections.sort(listComuniPiemonte);
			listComuniItalia = new ArrayList<Comune>();
			listComuniItalia.addAll(Arrays.asList(limmAmmEnteServiceHelper.cercaTuttiIComuni()));
			listComuniItalia.add(getComuneEstero());
			for(Comune item:listComuniItalia) {
				mapComuniIstat.put(item.getCodIstat(),item);
			}
			Collections.sort(listComuniItalia);
			this.mapComuniPerIstat = mapComuniIstat;
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"Caricamento comuni completato!"));
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private static Comune getComuneEstero() {
		Comune com = new Comune();
		com.setCodIstat("999999");
		com.setNome("Comune estero");
		return com;
	}
	
	@Override
	public List<Comune> getListComuniByName(String name) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Arrays.asList(limmAmmEnteServiceHelper.cercaComuniPerNomeEsatto(name));
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
}
