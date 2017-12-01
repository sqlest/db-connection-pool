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

package org.ej.db.connection.pool.impl;

import javax.sql.DataSource;

import org.ej.db.connection.ConnectionInfo;
import org.ej.db.connection.pool.ConnectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.DriverDataSource;

/**
 * @author ej.park
 *
 */
public class HikariCpImpl extends ConnectionPool {

	@Override
	protected DataSource getDataSource(ConnectionInfo connectionInfo) {
		DataSource dataSource = new DriverDataSource(connectionInfo.getJdbcUrl(), connectionInfo.getDriverClassName(),
				connectionInfo.getOptions(), connectionInfo.getUsername(), connectionInfo.getPassword());
		HikariConfig configuration = new HikariConfig();
		configuration.setAutoCommit(connectionInfo.isAutoCommit());
		configuration.setMinimumIdle(connectionInfo.getMinIdle());
		configuration.setMaximumPoolSize(connectionInfo.getMaxPoolSize());
		configuration.setConnectionInitSql(connectionInfo.getTestQuery());
		configuration.setConnectionTimeout(connectionInfo.getConnectionTimeout());
		configuration.setConnectionTestQuery(connectionInfo.getTestQuery());
		configuration.setValidationTimeout(connectionInfo.getValidationTimeout());
		configuration.setDataSource(dataSource);
		DataSource hikariDataSource = new HikariDataSource(configuration);
		return hikariDataSource;
	}
}
