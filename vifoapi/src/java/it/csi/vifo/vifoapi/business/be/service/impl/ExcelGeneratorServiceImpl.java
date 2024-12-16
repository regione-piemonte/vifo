/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioFruttiDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioPiantineDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioSemiDAO;
import it.csi.vifo.vifoapi.business.be.service.ExcelGeneratorService;
import it.csi.vifo.vifoapi.dto.InventarioFruttiRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioPiantineRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioSemiRiepilogo;
import it.csi.vifo.vifoapi.dto.XlsCaricoScarico;
import it.csi.vifo.vifoapi.util.ExcelCaricoScaricoEnum;
import it.csi.vifo.vifoapi.util.ExcelFruttiEnum;
import it.csi.vifo.vifoapi.util.ExcelPiantineEnum;
import it.csi.vifo.vifoapi.util.ExcelSemiEnum;

@Component
public class ExcelGeneratorServiceImpl extends BaseApiServiceImpl implements ExcelGeneratorService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	InventarioSemiDAO inventarioSemiDAO;
	
	@Autowired
	InventarioPiantineDAO inventarioPiantineDAO;
	
	@Autowired
	InventarioFruttiDAO inventarioFruttiDAO;

	@Override
	public ByteArrayOutputStream generateExcelSemi() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        HSSFWorkbook hwb = new HSSFWorkbook();
	        Sheet eventoSheet = hwb.createSheet("Semi");
			Row headerRow = eventoSheet.createRow(0);
			headerRow.setHeightInPoints(24);
			List<String> columnsSheet = Stream.of(ExcelSemiEnum.values())
	                .map(Enum::name).collect(Collectors.toList());
	
			createHeaderCells(columnsSheet, headerRow, getCellStyle(hwb));
			
			List<InventarioSemiRiepilogo> list = inventarioSemiDAO.getListInventarioSemi(null);
			
			fillSheetForSemi(list, eventoSheet);
			resizeColums(columnsSheet.size(), eventoSheet);
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e));
			}			
			return baos;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public ByteArrayOutputStream generateExcelPiantine(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));HSSFWorkbook hwb = new HSSFWorkbook();
			Sheet eventoSheet = hwb.createSheet("Piantine");
			Row headerRow = eventoSheet.createRow(0);
			headerRow.setHeightInPoints(24);
			List<String> columnsSheet = Stream.of(ExcelPiantineEnum.values())
	                .map(Enum::name).collect(Collectors.toList());
	
			createHeaderCells(columnsSheet, headerRow, getCellStyle(hwb));
			
			List<InventarioPiantineRiepilogo> list = inventarioPiantineDAO.getListInventarioPiantine(null,idVivaio);
			
			fillSheetForPiantine(list, eventoSheet);
			resizeColums(columnsSheet.size(), eventoSheet);
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e));
			}
			
			return baos;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public ByteArrayOutputStream generateExcelFrutti() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));HSSFWorkbook hwb = new HSSFWorkbook();
			Sheet eventoSheet = hwb.createSheet("Frutti");
			Row headerRow = eventoSheet.createRow(0);
			headerRow.setHeightInPoints(24);
			List<String> columnsSheet = Stream.of(ExcelFruttiEnum.values())
	                .map(Enum::name).collect(Collectors.toList());
	
			createHeaderCells(columnsSheet, headerRow, getCellStyle(hwb));
			
			List<InventarioFruttiRiepilogo> list = inventarioFruttiDAO.getListInventarioFrutti(null);
			
			fillSheetForFrutti(list, eventoSheet);
			resizeColums(columnsSheet.size(), eventoSheet);
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e));
			}
			
			return baos;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public ByteArrayOutputStream generateExcelCaricoScarico(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));HSSFWorkbook hwb = new HSSFWorkbook();
			Sheet eventoSheet = hwb.createSheet("Carico-Scarico");
			Row headerRow = eventoSheet.createRow(0);
			headerRow.setHeightInPoints(24);
			
			ExcelCaricoScaricoEnum[] items = ExcelCaricoScaricoEnum.values();
			List<String> columnsSheet = new ArrayList<String>();
			for(ExcelCaricoScaricoEnum item:items) {
				columnsSheet.add(item.getValue());
			}
			
			createHeaderCells(columnsSheet, headerRow, getCellStyle(hwb));
			
			List<XlsCaricoScarico> list = inventarioPiantineDAO.getListCaricoScarico(idVivaio);
			
			fillSheetForCaricoScarico(list, eventoSheet);
			resizeColums(columnsSheet.size(), eventoSheet);
	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e));
			}			
			return baos;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private void createHeaderCells(List<String> columns, Row headerRowforEventSheet,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRowforEventSheet.createCell(i);
			cell.setCellValue(columns.get(i));
			cell.setCellStyle(headerCellStyle);
			headerRowforEventSheet.setRowStyle(headerCellStyle);
		}
	}
	
	private CellStyle getCellStyle(HSSFWorkbook hwb) {		
		Font headerFont = hwb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);		
		return headerCellStyle;
	}
	
	private void resizeColums(int columnsSize, Sheet sheet) {
		
		
//		for (int i = 0; i < columnsSize; i++) {
//			try {
//				sheet.autoSizeColumn(i);
//			}catch(Exception ex) {
//				 LOGGER.info(getClassFunctionBeginInfo(CLASSNAME, "resizeColums for i: " + i));
//			}
//		}
		
	}
	
	private void fillSheetForSemi(List<InventarioSemiRiepilogo> list, Sheet sheet) {
		int rowNum = 1;
		if (list != null) {
			for (InventarioSemiRiepilogo item : list) {
				Row row = sheet.createRow(rowNum++);

				setCellValue(row.createCell(0),item.getIdInventario());
				setCellValue(row.createCell(1),item.getFkInventarioFrutti());
				setCellValue(row.createCell(2),item.getCertificatoProvenienza());
				setCellValue(row.createCell(3),item.getSpecie());
				setCellValue(row.createCell(4),item.getDataCertificato());
				setCellValue(row.createCell(5),item.getProvenienza());
				setCellValue(row.createCell(6),item.getDescrCategoriaMaterialie());
				setCellValue(row.createCell(7),item.getProduttore());
				setCellValue(row.createCell(8),item.getQtaScaricoFruttiKg());
				setCellValue(row.createCell(9),item.getDataCarico());
				setCellValue(row.createCell(10),item.getQtaCaricoKg());
				setCellValue(row.createCell(11),item.getQtaScaricoPiantineKg());
				setCellValue(row.createCell(12),item.getQtaScaricoVenditaKg());
				setCellValue(row.createCell(13),item.getQtaDistruzioneKg());
				setCellValue(row.createCell(14),item.getQtaResiduaKg());
			}
		}
	}
	
	private void fillSheetForPiantine(List<InventarioPiantineRiepilogo> list, Sheet sheet) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		int rowNum = 1;
		if (list != null) {
			for (InventarioPiantineRiepilogo item : list) {
				Row row = sheet.createRow(rowNum++);
				setCellValue(row.createCell(0),item.getIdInventarioPiante());
				setCellValue(row.createCell(1),item.getFkInventarioSemi());
				setCellValue(row.createCell(2),item.getFkInventarioPiante());
				setCellValue(row.createCell(3),item.getCertificatoProvenienza());
				setCellValue(row.createCell(4),item.getSpecie());
				setCellValue(row.createCell(5),item.getDataCertificato());
				setCellValue(row.createCell(6),item.getProvenienza());
				setCellValue(row.createCell(7),item.getDescrCategMateriale());
				setCellValue(row.createCell(8),item.getProduttore());
				setCellValue(row.createCell(9),item.getDescrTipoAllevamento());
				setCellValue(row.createCell(10),item.getEta());
				setCellValue(row.createCell(11),item.getAltezza());
				setCellValue(row.createCell(12),item.getAnnoRadicazioneSeme());
				setCellValue(row.createCell(13),item.getPrezzoUnitario());
				setCellValue(row.createCell(14),item.getQtaScaricoSemiKg());
				setCellValue(row.createCell(15),item.getDataCarico() == null ? "" : dateFormatter.format(item.getDataCarico()));
				setCellValue(row.createCell(16),item.getQtaCarico());
				setCellValue(row.createCell(17),item.getQtaNonAssegnabile());
				setCellValue(row.createCell(18),item.getQtaScaricoAltrePiante());
				setCellValue(row.createCell(19),item.getQtaAssegnabileTeorica());
				setCellValue(row.createCell(20),item.getQtaAssegnata());
				setCellValue(row.createCell(21),item.getQtaRitirata());
				setCellValue(row.createCell(22),item.getQtaDistruzione());
				setCellValue(row.createCell(23),item.getQtaDisponibile());
				setCellValue(row.createCell(24),item.getPosizione());
			}
		}
	}
	
	private void fillSheetForFrutti(List<InventarioFruttiRiepilogo> list, Sheet sheet) {
		int rowNum = 1;
		int idx;
		if (list != null) {
			for (InventarioFruttiRiepilogo item : list) {
				Row row = sheet.createRow(rowNum++);
				idx = 0;
				setCellValue(row.createCell(idx++),item.getIdInventarioFrutti());
				setCellValue(row.createCell(idx++),item.getCertificatoProvenienza());
				setCellValue(row.createCell(idx++),item.getSpecie());
				setCellValue(row.createCell(idx++),item.getDataCertificato());
				setCellValue(row.createCell(idx++),item.getProvenienza());
				setCellValue(row.createCell(idx++),item.getDescrCategoriaMaterialie());
				setCellValue(row.createCell(idx++),item.getProduttore());
				setCellValue(row.createCell(idx++),item.getDataCarico());
				setCellValue(row.createCell(idx++),item.getQtaCaricoKg());
				setCellValue(row.createCell(idx++),item.getQtaScaricoSemiKg());
				setCellValue(row.createCell(idx++),item.getQtaScaricoVenditaKg());
				setCellValue(row.createCell(idx++),item.getQtaDistruzioneKg());
				setCellValue(row.createCell(idx++),item.getQtaResidua());

			}
		}
	}
	
	private void fillSheetForCaricoScarico(List<XlsCaricoScarico> list, Sheet sheet) {
		int rowNum = 1;
		int idx;
		if (list != null) {
			for (XlsCaricoScarico item : list) {
				Row row = sheet.createRow(rowNum++);
				idx = 0;
				setCellValue(row.createCell(idx++),item.getIdInventario());
				setCellValue(row.createCell(idx++),item.getDataMovimento());
				setCellValue(row.createCell(idx++),item.getSpecie());
				setCellValue(row.createCell(idx++),item.getProvenienza());
				setCellValue(row.createCell(idx++),item.getCertificatoProvenienza());
				setCellValue(row.createCell(idx++),item.getQtaCarico());
				setCellValue(row.createCell(idx++),item.getQtaScarico());
				setCellValue(row.createCell(idx++),item.getIdDomanda());
				setCellValue(row.createCell(idx++),item.getNumeroDdt());
				setCellValue(row.createCell(idx++),item.getTipoDomanda());
				setCellValue(row.createCell(idx++),item.getRichiedente());
			}
		}
	}
	
	private void setCellValue(Cell cel, Integer value) {
		if(value != null) {
			cel.setCellValue(value);
		}else {
			cel.setCellValue("");
		}
	}
	
	private void setCellValue(Cell cel, String value) {
		if(value != null) {
			cel.setCellValue(value);
		}else {
			cel.setCellValue("");
		}
	}
	
	private void setCellValue(Cell cel, Double value) {
		if(value != null) {
			cel.setCellValue(value);
		}else {
			cel.setCellValue("");
		}
	}
	private void setCellValue(Cell cel, Date value) {
		if(value != null) {
			cel.setCellValue(value);
		}else {
			cel.setCellValue("");
		}
	}


	

}
