package factorymethod.impl;

import factorymethod.IDBAdapter;
import factorymethod.util.PropertiesUtil;
import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PostgresSQLDBAdapter implements IDBAdapter {

    private static final String DB_PROPERTIES = "META-INF/DBPostgres.properties";

    private static final String DB_DATABASE_PROP = "database";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP = "password";
    private static final String DB_PORT_PROP = "port";
    private static final String DB_USER_PROP = "user";

    static {
        //Bloque para registrar el Driver de Postgres
        try {
            new org.postgresql.Driver();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        try {
            String connectionString = createConnectionString();
            Connection connection = DriverManager.getConnection(connectionString);
            System.out.println("Connection class ==> " + connection.getClass().getName());
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String createConnectionString() {
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String service = prop.getProperty(DB_DATABASE_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);


        String connectionString = "jdbc:postgres:thin:" +
                user + "/" + password + "@//" + host + ":" + port + "/" + service;
        System.out.println("ConnectionString ==>" + connectionString);
        return connectionString;

    }

}