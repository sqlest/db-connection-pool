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

/**
 * @author ej.park
 *
 */
public enum DbmsType {

	ORACLE("Oracle",
			"oracle.jdbc.driver.OracleDriver",
			new String[] { "jdbc:oracle:thin:@%s:%s:%s", "jdbc:oracle:thin:@//%s:%s/%s" }, 
			"SELECT 1 FROM DUAL",
			1521,
			"SID", "ServiceName")
	,POSTGRESQL("PostgreSQL",
			"org.postgresql.Driver",
			new String[] { "jdbc:postgresql://%s:%s/%s" }, 
			"SELECT 1",
			5432,
			"DatabaseName")
	,
	;

	private String name;
	private String driverClassName;
	private String[] jdbcUrlFormat;
	private String testQuery;
	private int defaultPort;
	private String[] databaseNames;

	private DbmsType(String name, String driverClassName, String[] jdbcUrlFormat, String testQuery, int defaultPort,
			String... databaseNames) {
		this.name = name;
		this.driverClassName = driverClassName;
		this.jdbcUrlFormat = jdbcUrlFormat;
		this.testQuery = testQuery;
		this.defaultPort = defaultPort;
		this.databaseNames = databaseNames;
	}

	public String getName() {
		return this.name;
	}

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public String getJdbcUrlFormat(int idx) {
		return this.jdbcUrlFormat[idx];
	}

	public int getDefaultPort() {
		return this.defaultPort;
	}

	public String[] getDatabaseNames() {
		return this.databaseNames;
	}

	public String getTestQuery() {
		return this.testQuery;
	}
}
