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

package org.ej.db.connection.pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.ej.db.connection.ConnectionInfo;

/**
 * @author ej.park
 *
 */
public abstract class ConnectionPool implements IConnectionPool {

	private DataSource dataSource;

	@Override
	public void makeConnection(ConnectionInfo connectionInfo) {
		this.dataSource = getDataSource(connectionInfo);
		try {
			Connection connection = this.dataSource.getConnection();
			if (!connection.isValid(connectionInfo.getValidationTimeout())) {
				throw new ConnectionFailException("Connection is not valid.");
			}
		} catch (SQLException e) {
			throw new ConnectionFailException("Fail.", e);
		}
	}

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			if (this.dataSource == null) {
				throw new ConnectionFailException("Connection Pool is not initialized.");
			}
			connection = this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new ConnectionFailException("Fail.", e);
		}
		return connection;
	}

	/**
	 * getDataSource
	 *
	 * @param connectionInfo
	 * @return DataSource
	 */
	protected abstract DataSource getDataSource(ConnectionInfo connectionInfo);
}
