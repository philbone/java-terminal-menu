
/**
 * Clase principal de ejemplo que demuestra cómo construir
 * un menú interactivo con submenús utilizando el builder.
 */
public abstract class Main
{
    public static void main(String[] args) {
        Menu menuPrincipal = new MenuBuilder("SISTEMA DE PEDIDOS DE PIZZAS", false)
            .opcion("Ingresar Cliente", () -> System.out.println("Cliente ingresado."))
            .opcion("Ingresar Pizzero", () -> System.out.println("Pizzero ingresado."))
            .submenu("Gestionar Pizzas", new MenuBuilder("SUBMENÚ DE PIZZAS", true)
                .opcion("Agregar Pizza", () -> System.out.println("Pizza agregada."))
                .opcion("Listar Pizzas", () -> System.out.println("Listado de pizzas.")))
            .opcion("Registrar Pedido", () -> System.out.println("Pedido registrado."))
            .opcion("Mostrar Pedido", () -> System.out.println("Mostrando pedido."))
            .opcion("Salir", () -> {
                System.out.println("¡Hasta pronto!");
                System.exit(0);
            })
            .build();

        menuPrincipal.mostrar();
    }
}