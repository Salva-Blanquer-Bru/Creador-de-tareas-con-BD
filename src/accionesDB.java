//Importacion de librerias necesarias

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Salvador
 */

public class accionesDB { //Declaracion de variables

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/tareas";
    static final String User = "root";
    static final String Pass = "";
    static Connection conn = null;
    static Statement stmt = null;


    public static Connection conexionBD() { //metodo que conecta con la base de datos

        try {
            Class.forName(accionesDB.JDBC_DRIVER);
            conn = DriverManager.getConnection(accionesDB.DB_URL, accionesDB.User, accionesDB.Pass);

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Revisa el codigo");
            e.printStackTrace();
        }
        return conn;
    }


    public static void crearTablaBD() { // Metodo que crea una tabla con los valores asignados en la base de datos

        try {
            stmt = conn.createStatement();

            String sql = "CREATE TABLE if NOT EXISTS Tarea "
                    + "(ID INT NOT NULL AUTO_INCREMENT PRIMARY  KEY, "
                    + "titulo VARCHAR (50), "
                    + "fecha DATETIME, "
                    + "estado BOOLEAN) ";

            stmt.executeUpdate(sql);
            System.out.println("tabla creada correctamente...");

        } catch (SQLException se) {

            System.out.println("Revisa el codigo SQL");
            se.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Revisa el codigo");
        }
    }


    public static void desconexionBD() { // Metodo que desconecta la base de datos cuando el usuario cierra el programacion

        try {
            if (stmt != null) {
                conn.close();
            }

        } catch (SQLException se) {

            System.out.println("Revisa el codigo SQL");
            se.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException se) {

            System.out.println("Revisa el codigo SQL");
            se.printStackTrace();
        }
    }
}




