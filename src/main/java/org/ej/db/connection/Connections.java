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

package org.ej.db.connection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.ej.db.connection.pool.ConnectionPoolFactory;
import org.ej.db.connection.pool.IConnectionPool;

/**
 * @author ej.park
 *
 */
public class Connections {

	/** connectionPoolMap */
	private final static Map<String, IConnectionPool> connectionPoolMap = new HashMap<String, IConnectionPool>();

	/**
	 * setConnectionPool
	 *
	 * @param connectionInfo
	 * @param existForce
	 */
	public static void setConnectionPool(ConnectionInfo connectionInfo, boolean existForce) {
		if (existForce || !connectionPoolMap.containsKey(connectionInfo.getConnectionId())) {
			makeConnectionPool(connectionInfo);
		}
	}

	/**
	 * setConnectionPool
	 *
	 * @param connectionInfo
	 */
	public static void setConnectionPool(ConnectionInfo connectionInfo) {
		setConnectionPool(connectionInfo, false);
	}

	/**
	 * getConnection
	 *
	 * @param connectionId
	 * @return Connection
	 */
	public static Connection getConnection(String connectionId) {
		if(!connectionPoolMap.containsKey(connectionId)) {
			throw new RuntimeException("Connection is not set.");
		}
		return connectionPoolMap.get(connectionId).getConnection();
	}
	
	/**
	 * makeConnectionPool
	 *
	 * @param connectionInfo
	 */
	private static void makeConnectionPool(ConnectionInfo connectionInfo) {
		IConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
		connectionPool.makeConnection(connectionInfo);
		connectionPoolMap.put(connectionInfo.getConnectionId(), connectionPool);
	}
}
