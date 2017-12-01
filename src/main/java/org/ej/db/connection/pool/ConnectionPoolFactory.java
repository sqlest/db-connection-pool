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

import org.ej.db.connection.pool.impl.CommonsDbcp2Impl;
import org.ej.db.connection.pool.impl.HikariCpImpl;

/**
 * @author ej.park
 *
 */
public class ConnectionPoolFactory {

	private volatile static ConnectionPoolFactory instance;

	/** CONNECTION_POOL_TYPE */
	public enum CONNECTION_POOL_TYPE {
		DBCP2, HIKARICP
	};

	/**
	 * @return
	 */
	public static ConnectionPoolFactory getInstance() {
		synchronized (ConnectionPoolFactory.class) {
			if (instance == null) {
				instance = new ConnectionPoolFactory();
			}
		}
		return instance;
	}

	private ConnectionPoolFactory() {
	}
	
	/**
	 * getConnectionPool
	 *
	 * @return IConnectionPool
	 */
	public IConnectionPool getConnectionPool() {
		return getConnectionPool(CONNECTION_POOL_TYPE.DBCP2);
	}

	/**
	 * getConnectionPool
	 *
	 * @param connectionPoolType
	 * @return IConnectionPool
	 */
	public IConnectionPool getConnectionPool(CONNECTION_POOL_TYPE connectionPoolType) {
		IConnectionPool connectionPool = null;
		switch (connectionPoolType) {
			case DBCP2:
				connectionPool = new CommonsDbcp2Impl();
				break;
			case HIKARICP:
				connectionPool = new HikariCpImpl();
				break;
			default:
				connectionPool = new CommonsDbcp2Impl();
				break;
		}
		return connectionPool;
	}
}
