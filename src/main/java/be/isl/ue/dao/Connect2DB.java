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
                    throw new IllegalStateException("Le fichier de propriétés 'db.properties' est incomplet ou manquant.");
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
                throw new IllegalStateException("Fichier de propriétés " + PROPERTIES_FILE
                        + " introuvable dans le Classpath (doit être dans src/main/resources).");
            }

            props.load(input);

        } catch (IOException ex) {
            Logger.getLogger(Connect2DB.class.getName()).log(Level.SEVERE, "Erreur de lecture du fichier " + PROPERTIES_FILE, ex);
            throw new IllegalStateException("Erreur lors de la lecture du fichier de configuration.", ex);
        }
        return props;
    }
}
