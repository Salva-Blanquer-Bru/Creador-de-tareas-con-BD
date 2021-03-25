import java.sql.*;
import java.util.Scanner;

public class ConexionesDB {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/Tareas";
    static final String User = "root";
    static final String Pass = "";
    static Scanner teclado = new Scanner(System.in);
    static Connection conn;
    static Statement stmt;

    public static void conexionBD() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database....");
            conn = DriverManager.getConnection(DB_URL, User, Pass);
            System.out.println("Connected database successfully....");
            stmt = conn.createStatement();

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

/*
    public static void insertardatos(){
        Date date= new Date();

       SimpleDateFormat plantilla= new SimpleDateFormat("yyyy-mm-dd");
        System.out.println("Introuduce el nombre de la Tarea");
        String titulo= teclado.nextLine();
        System.out.println(" Fecha del partido(yyyy-mm-dd)");
        String fecha= teclado.nextLine();

        try {
            date=plantilla.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date fechaT= new java.sql.Date(date.getTime());

        Connection conn=null;
        Statement stmt=null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database....");
            conn= DriverManager.getConnection(DB_URL,User,Pass);
            System.out.println("Connected database successfully....");

            System.out.println("creating table in given database....");
            stmt=conn.createStatement();

            String consulta="INSERT INTO Equipo VALUES(?, ?, ?, ?, ?)";

            PreparedStatement  prpStateent= conn.prepareStatement(consulta);

            prpStateent.setString(1,titulo);

            prpStateent.setDate(2,fechaT);




            System.out.println("adding dates into Equipo...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (stmt!=null){
                    conn.close();
                }
            } catch (SQLException se) {
            }try {
                if (conn!=null){
                    conn.close();
                }
            }catch (SQLException se) {
            }
        }
        System.out.println("ta luego");
    }
*/

    public static void creartabla() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database....");
            conn = DriverManager.getConnection(DB_URL, User, Pass);
            System.out.println("Connected database successfully....");

            System.out.println("creating table in given database....");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE tareas "
                    + "(titulo VARCHAR(255), "
                    + "fecha DATETIME) ";
            stmt.executeUpdate(sql);
            System.out.println("created table in given database...");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
        }
        System.out.println("goodbye");
    }

    public static void obtener_resultado() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, User, Pass);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM tareas";
        PreparedStatement Stame = conn.prepareStatement(sql);
        ResultSet rs = Stame.executeQuery();
        while (rs.next()) {
            Bloc_de_notas.ListTareas.add(new Tareas(rs.getString("titulo") + rs.getDate("fechaT" + rs.getBoolean("Estado"))));
        }
        System.out.println(rs);
    }

    public static void borrar_datos() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
        Statement stmt = conn.createStatement();
        String sql = "delete from equipo where local LIKE ?";


        PreparedStatement prpStatement = conn.prepareStatement(sql);
        prpStatement.setString(1, "madrid");
        ResultSet rs = prpStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("local") + " " + rs.getString("visitante"));
        }
        System.out.println("Mostrado");
    }
}
