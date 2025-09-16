package terminalmenu; 

import java.util.*;

/**
 * Clase que representa un menú de consola.
 * Contiene un conjunto de opciones, las muestra al usuario,
 * y permite navegar entre menús y submenús.
 * @author <a href="mailto:philone@focused.cl">Felipe M.</a>
 */
public class Menu
{
    private String titulo;
    private Map<Integer, OpcionMenu> opciones = new LinkedHashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean enEjecucion = true;
    private boolean esSubmenu;
    
    /**
     * Constructor del menú.
     *
     * @param titulo Título del menú que se mostrará en consola.
     * @param esSubmenu Indica si el menú es un submenú (true) o el menú principal (false).
     */
    public Menu(String titulo, boolean esSubmenu) {
        this.titulo = titulo;
        this.esSubmenu = esSubmenu;
    }
    
    /**
     * Agrega una opción al menú.
     *
     * @param numero Número que el usuario deberá teclear para elegir esta opción.
     * @param descripcion Texto que describe la acción de la opción.
     * @param comando Acción a ejecutar al elegir la opción.
     */
    public void agregarOpcion(int numero, String descripcion, Comando comando) {
        opciones.put(numero, new OpcionMenu(descripcion, comando));
    }
    
    /**
     * Muestra el menú en pantalla y procesa la entrada del usuario.
     * Mantiene un bucle hasta que el usuario elija salir o volver.
     */
    public void mostrar() {
        enEjecucion = true; // cada vez que abrimos el menú
        while (enEjecucion) {
            System.out.println("\n=== " + titulo + " ===");
            for (Map.Entry<Integer, OpcionMenu> entrada : opciones.entrySet()) {
                System.out.println(entrada.getKey() + ". " + entrada.getValue().getDescripcion());
            }
            System.out.print("Seleccione una opción: ");

            int eleccion;
            try {
                eleccion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
                continue;
            }

            OpcionMenu opcion = opciones.get(eleccion);
            if (opcion != null) {
                opcion.ejecutar();
            } else {
                System.out.println("Opción no encontrada.");
            }
        }
    }
    
    /**
     * Método que permite salir del menú actual (usado en submenús).
     */
    public void salir() {
        enEjecucion = false;
    }
    
    public boolean esSubmenu() {
        return esSubmenu;
    }
}
