# 🖥️ Sistema de Menús Interactivos en Java

Este proyecto implementa un **menú de consola reutilizable** en Java usando el **patrón Command** y el **patrón Builder**.  
Permite crear menús y submenús de forma fluida, evitando repetir código y manteniendo la lógica de negocio separada del sistema de navegación.

---

## ✨ Características
- Menús interactivos por consola.
- Soporte para **submenús anidados**.
- Opción automática de **"Volver"** en los submenús.
- Uso del **patrón Command** para desacoplar acciones del menú.
- **Builder** para construir menús de manera clara y concisa.

---

## 🚀 Ejemplo de Uso

    public class Principal {
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

## Ejemplo de interacción

    === SISTEMA DE PEDIDOS DE PIZZAS ===
1. Ingresar Cliente
2. Ingresar Pizzero
3. Gestionar Pizzas
4. Registrar Pedido
5. Mostrar Pedido
6. Salir  
`Seleccione una opción:`

-

    === SUBMENÚ DE PIZZAS ===
1. Agregar Pizza
2. Listar Pizzas
3. Volver     
`Seleccione una opción:`
