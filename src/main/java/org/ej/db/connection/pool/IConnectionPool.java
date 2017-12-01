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

import org.ej.db.connection.ConnectionInfo;

/**
 * @author ej.park
 *
 */
public interface IConnectionPool {

	/**
	 * getConnection
	 *
	 * @return Connection
	 */
	public Connection getConnection();

	/**
	 * makeConnection
	 *
	 * @param connectionInfo
	 */
	public void makeConnection(ConnectionInfo connectionInfo);
}
