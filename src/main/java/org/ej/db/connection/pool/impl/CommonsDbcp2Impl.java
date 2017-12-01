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

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.ej.db.connection.ConnectionInfo;
import org.ej.db.connection.pool.ConnectionPool;

/**
 * @author ej.park
 *
 */
public class CommonsDbcp2Impl extends ConnectionPool {

	@Override
	protected DataSource getDataSource(ConnectionInfo connectionInfo) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(connectionInfo.getJdbcUrl());
		dataSource.setDriverClassName(connectionInfo.getDriverClassName());
		dataSource.setUsername(connectionInfo.getUsername());
		dataSource.setPassword(connectionInfo.getPassword());
		dataSource.setConnectionProperties(connectionInfo.getJdbcOption());
		dataSource.setDefaultAutoCommit(connectionInfo.isAutoCommit());
		dataSource.setInitialSize(connectionInfo.getMinIdle());
		
		ConnectionFactory connectionFactory = new DataSourceConnectionFactory(dataSource, connectionInfo.getUsername(), connectionInfo.getPassword());

		PoolableConnectionFactory pcf = new PoolableConnectionFactory(connectionFactory, null);
		List<String> connectionInitSqls = new ArrayList<String>();
		connectionInitSqls.add(connectionInfo.getTestQuery());
		pcf.setConnectionInitSql(connectionInitSqls);
		pcf.setValidationQuery(connectionInfo.getTestQuery());
		pcf.setValidationQueryTimeout((int)connectionInfo.getValidationTimeout());
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setTestWhileIdle(connectionInfo.isTestWhileIdle());
		poolConfig.setNumTestsPerEvictionRun((int)connectionInfo.getTimeBetweenEvictionRunsMillis());
		poolConfig.setMinIdle(connectionInfo.getMinIdle());
		poolConfig.setMaxIdle(connectionInfo.getMaxPoolSize());

		GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(pcf, poolConfig);
		pcf.setPool(connectionPool);
		return dataSource;
	}

}
