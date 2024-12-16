/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;

public class RichiestaTrasferimentoPianteMapper extends BaseMapper implements RowMapper<RichiestaTrasferimentoPiante>{

	@Override
	public RichiestaTrasferimentoPiante mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RichiestaTrasferimentoPiante richiesta = new RichiestaTrasferimentoPiante();
		richiesta.setIdInventario(rs.getInt("id_inventario_piante"));
		richiesta.setFkInventarioPiante(rs.getInt("fk_inventario_piante"));
		richiesta.setSpecieName(rs.getString("specie_descr"));
		String specieLatino = rs.getString("specie_latino");
		richiesta.setSpecieName(richiesta.getSpecieName() + (specieLatino == null?"": " - " + specieLatino));
		richiesta.setVivaioToName(rs.getString("vivaio_to_name"));
		richiesta.setVivaioFromName(rs.getString("vivaio_from_name"));
		richiesta.setFkVivaioTo(rs.getInt("fk_vivaio_to"));
		richiesta.setFkVivaioFrom(rs.getInt("fk_vivaio_from"));
		richiesta.setDataInvioNotifica(getFormatedDate(rs.getDate("data_ins")));
		richiesta.setQtaCarico(rs.getInt("qta_carico"));
		richiesta.setIsTrasferimento("T".equals(rs.getString("flg_tipo_trasferimento")));
		richiesta.setMotivoRichiesta(rs.getString("motivo_trasferimento"));
		
		return richiesta;
	}

}
