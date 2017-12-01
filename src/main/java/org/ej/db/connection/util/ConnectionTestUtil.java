/**
 * Copyright (c) 2017 ej.park 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ej.db.connection.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

import org.ej.db.connection.ConnectionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ej.park
 *
 */
public class ConnectionTestUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTestUtil.class);
	/**
	 * testConnection
	 *
	 * @param connectionInfo
	 * @return ConnectionResult
	 */
	public static ConnectionResult testConnection(ConnectionInfo connectionInfo) {
		return testConnection(connectionInfo, 3);
	}

	/**
	 * testConnection
	 *
	 * @param connectionInfo
	 * @param timeout
	 * @return ConnectionResult
	 */
	public static ConnectionResult testConnection(ConnectionInfo connectionInfo, int timeout) {
		ConnectionResult result = new ConnectionResult();
		try {
			LOGGER.debug("DriverClassName:{}", connectionInfo.getDriverClassName());
			Driver driver = (Driver) Class.forName(connectionInfo.getDriverClassName()).newInstance();
			LOGGER.debug("JdbcUrl:{}", connectionInfo.getJdbcUrl());
			LOGGER.debug("Options:{}", connectionInfo.getOptions());
			Connection connection = driver.connect(connectionInfo.getJdbcUrl(), connectionInfo.getOptions());
			LOGGER.debug("timeout:{}", timeout);
			if (connection.isValid(timeout)) {
				result.setValid(true);
			} else {
				result.setValid(false);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			LOGGER.error("error:{}", e.getMessage());
			result.setValid(false);
			result.setException(e);
		}
		return result;
	}
}
