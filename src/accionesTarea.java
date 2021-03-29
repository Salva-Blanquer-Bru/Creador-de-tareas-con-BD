//Importacion de librerias necesarias

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Salvador
 */

public class accionesTarea { //Declaracion de variables
    static Scanner teclado = new Scanner(System.in);


    public static void crearTareas() throws SQLException { //Metodo que crea una tarea y la inserta en la base de datos

        System.out.println("Introduce el titulo de la nueva tarea");
        String titulo = teclado.nextLine();

        Statement stmt = null;
        stmt = accionesDB.conn.createStatement();
        String consulta = "INSERT INTO tarea( titulo, estado) VALUES(?, ?)";
        PreparedStatement prpStateent = accionesDB.conn.prepareStatement(consulta);
        prpStateent.setString(1, titulo);

        try {
            prpStateent.setInt(2, 0);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        ResultSet rs;
        rs = prpStateent.executeQuery();
    }

    public static void leer_tareas_pendientes() throws SQLException { //Metodo que lee de la base de datos las tareas que esten pendientes y las imprime

        Statement stmt;
        stmt = accionesDB.conn.createStatement();
        String sql = "SELECT * FROM tarea WHERE estado=0";
        PreparedStatement prpStatement = accionesDB.conn.prepareStatement(sql);
        ResultSet rs = prpStatement.executeQuery();

        if (!rs.next()) {

            System.out.println("No existen tareas pendientes ahora mismo");

        } else {
            rs.beforeFirst();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3));
            }
        }
    }

    public static void completarTareas() throws SQLException { //Metodo que completa las tareas previamente creadas

        leer_tareas_pendientes();
        System.out.println();
        System.out.println("Introduce el ID de la tarea que quieras completar");
        int ID = teclado.nextInt();

        Statement stmt = accionesDB.conn.createStatement();
        String sql = "UPDATE tarea SET fecha = CURRENT_TIMESTAMP, estado=1 WHERE ID=" + ID;
        PreparedStatement prpStatement = accionesDB.conn.prepareStatement(sql);
        ResultSet rs = prpStatement.executeQuery();
        System.out.println("Su tarea ha sido completada");

    }

    public static void leer_tareas_completadas() throws SQLException { //Metodo que lee de la base de datos las tareas que esten completadas y las imprime


        Statement stmt = accionesDB.conn.createStatement();
        String sql = "SELECT * FROM tarea WHERE estado=1";
        PreparedStatement prpStatement = accionesDB.conn.prepareStatement(sql);
        ResultSet rs = prpStatement.executeQuery();

        if (!rs.next()) {
            System.out.println("No existen tareas completadas ahora mismo");

        } else {
            rs.beforeFirst();

            while (rs.next()) {

                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3));
            }
        }
    }
}






