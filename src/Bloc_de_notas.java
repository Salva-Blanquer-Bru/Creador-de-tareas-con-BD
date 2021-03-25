import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Salvador
 */
public class Bloc_de_notas {


    static ArrayList<Tareas> ListTareas = new ArrayList();


    static int autoincremental = 0;

    public static void main(String[] args) throws SQLException {
        ConexionesDB.conexionBD();
        Scanner teclado = new Scanner(System.in);// transcibe lo que escribas en el teclado al terminal
        boolean repite;
        while (repite = true) { // Bucle para repetir el menu hasta que el usuario lo detenga


            System.out.println();
            System.out.println("Escoga una opcion ");
            System.out.println();
            System.out.println("1. Escribir nuevas tareas");
            System.out.println("2. Mirar tareas");
            System.out.println("3. Completar tareas pendientes");
            System.out.println("4. Salir");

            int opcion = teclado.nextInt();
            switch (opcion) { // Declaracion de acciones en el menu

                case 1: // Crear Tareas
                    System.out.println("titulo de la nueva tarea");
                    CrearTareas();

                    break;

                case 2: // Ver las tareas pendientes

                    LeerTareas();

                    break;


                case 3: // Completar Tareas

                    CompletarTareas();

                    break;

                case 4: // Finaliza el programa
                    System.out.println("Hasta Luego");


                    return;
            }

        }
    }

    public static void LeerTareas() { //Lee y separa las tareas en pendientes y completadas
        System.out.println("Tareas Pendientes");
        for (int i = 0; i < autoincremental; i++) {
            if (ListTareas.get(i).getEstado() == false) {
                System.out.println(ListTareas.get(i));
            }
        }
        System.out.println("Tareas Completadas");
        for (int i = 0; i < autoincremental; i++) {
            if (ListTareas.get(i).getEstado() == true) {
                System.out.println(ListTareas.get(i));
            }
        }

    }


    public static void CrearTareas() { // Creacion de Tareas

        Scanner Teclado = new Scanner(System.in);
        String tarea = Teclado.nextLine();
        ListTareas.set(autoincremental++, new Tareas(tarea, new Date(), false));


    }

    public static void CompletarTareas() { // Cuando introducimos un titulo que es igual al titulo de una tarea, el estado de la tarea cambia a true
        Scanner teclado = new Scanner(System.in);
        System.out.println("¿QUE TAREA DESEAS COMPLETAR?");
        String EscogerTarea = teclado.nextLine();
        for (int i = 0; i < autoincremental; i++) {
            if (ListTareas.get(i).getTitulo().equals(EscogerTarea)) {
                ListTareas.get(i).setEstado(true);
            }

        }

    }


}