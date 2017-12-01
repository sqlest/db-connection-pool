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

/**
 * @author ej.park
 *
 */
public class ConnectionFailException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9009543745487935531L;

	/**
	 * constructor
	 *
	 * @param message
	 *            exception message
	 */
	public ConnectionFailException(String message) {
		super(message);
	}

	/**
	 * constructor
	 *
	 * @param message
	 *            exception message
	 * @param cause
	 *            the cause
	 */
	public ConnectionFailException(String message, Throwable cause) {
		super(message, cause);
	}
}
