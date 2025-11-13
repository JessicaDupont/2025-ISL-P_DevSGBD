/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jessi
 */
public class Connect2DB {

    private static final String PROPERTIES_FILE = "db.properties";
    private static Connection conn = null;

    public Connection getConn() {
        return conn;
    }

    public Connect2DB() {
        try {
            if (conn == null || conn.isClosed()) {
                Properties props = loadProperties();
                String sCon = props.getProperty("db.url");
                String sUser = props.getProperty("db.user");
                String sPwd = props.getProperty("db.password");
                String sDriver = props.getProperty("db.driver");
                if (sCon == null || sUser == null || sPwd == null || sDriver == null) {
                    throw new IllegalStateException("The properties file 'db.properties' is incomplete or missing.");
                }

                Driver pilote = (Driver) Class.forName(sDriver).newInstance();

                conn = DriverManager.getConnection(sCon, sUser, sPwd);

            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connect2DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close() {
        try {
            conn.close();
            System.out.println("Closing DB connection");
        } catch (SQLException ex) {
            Logger.getLogger(Connect2DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Properties loadProperties() throws IllegalStateException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {

            if (input == null) {
                throw new IllegalStateException("Properties file " + PROPERTIES_FILE
                        + " not found in the Classpath (must be in src/main/resources).");
            }

            props.load(input);

        } catch (IOException ex) {
            Logger.getLogger(Connect2DB.class.getName()).log(Level.SEVERE, "Error reading file " + PROPERTIES_FILE, ex);
            throw new IllegalStateException("Error reading the configuration file.", ex);
        }
        return props;
    }
}
