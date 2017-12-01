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

/**
 * @author ej.park
 *
 */
public class ConnectionResult {

	private boolean valid;
	private Throwable cause;

	public ConnectionResult() {
		this.valid = false;
		this.cause = new Error("Invalid Connection");
	}

	/**
	 * @param valid
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return valid
	 */
	public boolean isValid() {
		return this.valid;
	}

	/**
	 * @param cause
	 */
	public void setException(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * @return cause
	 */
	public Throwable getException() {
		return this.cause;
	}
}
