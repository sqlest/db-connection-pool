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

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ej.park
 *
 */
public class PingTest implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingTest.class);

	/** PING_RESULT */
	public enum PING_RESULT {
		SUCCESS,
		HOST_NOT_FOUND,
		NOT_CONNECTED,
		TIMEOUT
		;
	};

	private String host;
	private int port;
	private long maxWait;
	private PING_RESULT result;

	/**
	 * @param host
	 * @param port
	 * @param maxWait
	 */
	private PingTest(String host, int port, long maxWait) {
		this.host = host;
		this.port = port;
		this.maxWait = maxWait;
		this.result = PING_RESULT.HOST_NOT_FOUND;
	}
	
	@Override
	public void run() {
		Socket socket = null;
		try {
			LOGGER.debug("host:{}", host);
			InetAddress inetAddress = InetAddress.getByName(host);
			LOGGER.debug("maxWait:{}", maxWait);
			if (inetAddress.isReachable((int)maxWait)) {
				LOGGER.debug("port:{}", port);
				socket = new Socket(inetAddress, port);
				if(socket.isConnected()) {
					result = PING_RESULT.SUCCESS;
				}else {
					result = PING_RESULT.NOT_CONNECTED;
				}
			} else {
				result = PING_RESULT.HOST_NOT_FOUND;
			}
		} catch (UnknownHostException e) {
			LOGGER.error("UnknownHostException:{}", e.getMessage());
			result = PING_RESULT.HOST_NOT_FOUND;
		} catch (IOException e) {
			LOGGER.error("IOException:{}", e.getMessage());
			result = PING_RESULT.NOT_CONNECTED;
		} finally {
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					//Ignore.
				}
			}
		}
	}

	/**
	 * ping
	 *
	 * @param host
	 * @param port
	 * @param maxWait
	 * @return PING_RESULT
	 */
	public static PING_RESULT ping(String host, int port, long maxWait) {
		PingTest pingTest = new PingTest(host, port, maxWait);
		
		Thread ping = new Thread(pingTest);
		ping.setDaemon(true);
		ping.start();
		try {
			ping.join(maxWait);
		} catch (InterruptedException e) {
			return PING_RESULT.TIMEOUT;
		}
		return pingTest.result;
	}
}
