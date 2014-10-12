package com.mapbar.analyzelog.httpserver;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;

import com.mapbar.analyzelog.common.LogWriter;
import com.mapbar.analyzelog.common.ResourcePath;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * <p>
 * $Header:
 * /server/analyzelog/protocol/com.mapbar.analyzelog.httpserver.MServer.
 * java,lijie Exp $ $Version: 1.0 $ $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * MServer: This {@link GrizzlyServerFactory} is installed to the
 * {@link org.glassfish.grizzly.http.server.HttpServer} factory</li>
 * </ul>
 * </p>
 * 
 */
public class MServer {

	private static final Logger LOGGER = Grizzly.logger(MServer.class);
	public static final URI BASE_URI = getBaseURI();

	public static void main(String[] args) throws IllegalArgumentException,
			IOException {
		final HttpServer server = startServer();
		try {
			if (!server.isStarted())
				server.start();
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
			LogWriter
					.SrvDebug("see:com.mapbar.analyzelog.httpserver#main();error:IOException;message:"
							+ ioe.getMessage());
		} catch (RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.toString(), re);
			LogWriter
					.SrvDebug("see:com.mapbar.analyzelog.httpserver#main();error:RuntimeException;message:"
							+ re.getMessage());
		} finally {
		}
		serverExit(server);
	}

	/*private void serverQuit(HttpServer server) {
		try {
			int read;
			System.out.println("Enter char 'q',Server exit!");
			while (true) {
				read = System.in.read();
				if (read == 'q') {
					System.exit(0);
					server.stop();
				}
			}
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
			LogWriter.SrvDebug("");
		}
	}*/

	private static void serverExit(HttpServer server) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String read = reader.readLine();
			System.out.println("Enter char 'q',Server exit!");
			if (read == "q") {
				reader.close();
				server.stop();
			}
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
			LogWriter.SrvDebug("");
		}
	}

	protected static HttpServer startServer() throws IOException {
		LogWriter.SrvDebug("Starting Server...");
		ResourceConfig rc = new PackagesResourceConfig(
				"com.mapbar.analyzelog.resource");
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}

	private static URI getBaseURI() {
		return UriBuilder
				.fromUri(ResourcePath.instance.getPath("server.uri"))
				.port(Integer.parseInt(ResourcePath.instance
						.getPath("server.port"))).build();
	}
}
