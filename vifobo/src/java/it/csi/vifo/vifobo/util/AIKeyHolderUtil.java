/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AIKeyHolderUtil{

	private AIKeyHolderUtil() {}

	public static int updateWithGenKey(String idName, String sql, List<Object> args, JdbcTemplate jdbcTemplate) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { idName });
			if (args != null && ListUtil.isNotEmpty(args)) {
				for (int i = 1; i <= args.size(); i++) {
					ps.setObject(i, args.get(i - 1));
				}
			}
			return ps;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
