/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl;

import java.util.List;

import it.csi.vifo.vifobo.dto.Pagamento;
import it.csi.vifo.vifobo.util.CodicePagamentoEnum;
import it.csi.vifo.vifobo.util.TipoPagamentoEnum;

public interface PagamentoDAO {
	int insert(Pagamento pagamento);
	int updateLastByIdDomandaAndTipo(Pagamento pagamento);
	int updateByIuvIfStatoPagamentoChanged(String iuv, CodicePagamentoEnum statoPagamento);
	List<Pagamento> getPagamentiByIdDomandaAndTipo(int idDomanda, TipoPagamentoEnum tipo);
	int deletePagamentoPendingByIdDomanda(int idDomanda);
}
