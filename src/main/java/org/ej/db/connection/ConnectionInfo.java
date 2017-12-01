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

import java.util.Properties;

/**
 * @author ej.park
 *
 */
public class ConnectionInfo {

	private final long LONG_MINUTES = 60L * 1000L;
	private final long LONG_HOURS = 60L * 60L * 1000L;

	private DbmsType dbmsType;
	private String host;
	private int port;
	private int connectType;
	private String databaseName;
	private String username;
	private String password;

	private boolean isAutoCommit = false;
	private int maxPoolSize = 5;
	private int minIdle = 1;

	private long connectionTimeout = 10 * LONG_HOURS;
	private int validationTimeout = 3;
	private boolean testWhileIdle = false;
	private long timeBetweenEvictionRunsMillis = 30 * LONG_MINUTES;

	private String jdbcOption;
	private final Properties options;

	public ConnectionInfo() {
		this.options = new Properties();
	}

	/**
	 * @return the dbmsType
	 */
	public DbmsType getDbmsType() {
		return dbmsType;
	}

	/**
	 * @param dbmsType
	 *            the dbmsType to set
	 */
	public void setDbmsType(DbmsType dbmsType) {
		this.dbmsType = dbmsType;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the connectType
	 */
	public int getConnectType() {
		return connectType;
	}

	/**
	 * @param connectType
	 *            the connectType to set
	 */
	public void setConnectType(int connectType) {
		this.connectType = connectType;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the testWhileIdle
	 */
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * @param testWhileIdle the testWhileIdle to set
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * @return the timeBetweenEvictionRunsMillis
	 */
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	/**
	 * @param timeBetweenEvictionRunsMillis the timeBetweenEvictionRunsMillis to set
	 */
	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	/**
	 * @return the jdbcOption
	 */
	public String getJdbcOption() {
		return jdbcOption;
	}

	/**
	 * @param jdbcOption
	 *            the jdbcOption to set
	 */
	public void setJdbcOption(String jdbcOption) {
		this.jdbcOption = jdbcOption;
	}

	/**
	 * @return the options
	 */
	public Properties getOptions() {
		return options;
	}

	/**
	 * @return the option
	 */
	public String getOption(String key) {
		return options.getProperty(key);
	}

	/**
	 * @param key
	 *            the key to set
	 * @param value
	 *            the value to set
	 */
	public void setOption(String key, String value) {
		this.options.put(key, value);
	}

	/**
	 * @return the jdbcUrl
	 */
	public String getJdbcUrl() {
		return String.format(dbmsType.getJdbcUrlFormat(connectType), host, port, databaseName);
	}

	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return this.dbmsType.getDriverClassName();
	}

	/**
	 * @return the testQuery
	 */
	public String getTestQuery() {
		return this.dbmsType.getTestQuery();
	}

	/**
	 * @return the isAutoCommit
	 */
	public boolean isAutoCommit() {
		return isAutoCommit;
	}

	/**
	 * @param isAutoCommit
	 *            the isAutoCommit to set
	 */
	public void setAutoCommit(boolean isAutoCommit) {
		this.isAutoCommit = isAutoCommit;
	}

	/**
	 * @return the maxPoolSize
	 */
	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * @param maxPoolSize
	 *            the maxPoolSize to set
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	/**
	 * @return the minIdle
	 */
	public int getMinIdle() {
		return minIdle;
	}

	/**
	 * @param minIdle
	 *            the minIdle to set
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	/**
	 * @return the connectionTimeout
	 */
	public long getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout
	 *            the connectionTimeout to set
	 */
	public void setConnectionTimeout(long connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @return the validationTimeout
	 */
	public int getValidationTimeout() {
		return validationTimeout;
	}

	/**
	 * @param validationTimeout
	 *            the validationTimeout to set
	 */
	public void setValidationTimeout(int validationTimeout) {
		this.validationTimeout = validationTimeout;
	}

	public String getConnectionId() {
		return String.format("H%sP%sU%s_%s", this.host, this.port, this.username, this.hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("host=");
		sb.append(this.host);
		sb.append(", port=");
		sb.append(this.port);
		sb.append(", connectType=");
		sb.append(this.connectType);
		sb.append(", databaseName=");
		sb.append(this.databaseName);
		sb.append(", user=");
		sb.append(this.username);
		sb.append(", password=");
		sb.append(this.password);
		sb.append(", jdbcOption=");
		sb.append(this.jdbcOption);
		sb.append(", options=");
		sb.append(this.options.toString());
		sb.append("]");
		return sb.toString();
	}
}
