//Importacion de librerias necesarias

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Salvador
 */

public class opciones {
    public static void main() {
        accionesDB.conexionBD();
        Scanner teclado = new Scanner(System.in);
        boolean repite = true;
        while (repite) { // Bucle para repetir el menu hasta que el usuario lo detenga

            try {

                System.out.println();
                System.out.println("____________CREADOR__DE__TAREAS___________");
                System.out.println();
                System.out.println("\t\t\t1.Crear una nueva tarea");
                System.out.println();
                System.out.println("\t\t\t2.Mirar tareas pendientes");
                System.out.println();
                System.out.println("\t\t\t3.Mirar tareas completadas");
                System.out.println();
                System.out.println("\t\t\t4.Completar tareas");
                System.out.println();
                System.out.println("\t\t\t5.Cerrar");
                System.out.println("------------------------------------------");

                int opcion = teclado.nextInt();
                switch (opcion) { // Declaracion de acciones en el menu

                    case 1: // Crear Tareas
                        accionesDB.crearTablaBD();
                        accionesTarea.crearTareas();


                        break;

                    case 2: // Leer las tareas pendientes
                        accionesTarea.leer_tareas_pendientes();

                        break;


                    case 3: // Leer las tareas completadas
                        accionesTarea.leer_tareas_completadas();

                        break;


                    case 4: // Completar Tareas
                        accionesTarea.completarTareas();

                        break;


                    case 5: // Finaliza el programa
                        System.out.println("Vuelve pronto :D");

                        repite = false;
                        accionesDB.desconexionBD();

                }

            } catch (InputMismatchException | SQLException e) { // Control de una excepcion en concreto
                System.out.println("Escriba un caracter valido");
                teclado.nextLine();
            }

        }
    }


}