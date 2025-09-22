# 📦 java-terminal-menu

Una pequeña librería en Java para construir **menús interactivos de consola** con soporte para **submenús**, usando **Command** y **Builder pattern**.  

Ideal para proyectos educativos o aplicaciones sencillas de consola.  

## 📚 Ventajas de Usar terminal-menu

  * Construcción de menús limpia y fluida con method chaining.
  * Soporte automático para submenús anidados.
  * Opción "Volver" incluida en cada submenú.
  * Separación de responsabilidades: el menú solo gestiona la navegación, la lógica del negocio va en los comandos.

---

## 🔨 Construcción del JAR

**1)** Coloca las clases (`Menu`, `MenuBuilder`, `Comando`, `OpcionMenu`) dentro de un paquete, por ejemplo:

    src/terminalmenu/Menu.java
    src/terminalmenu/MenuBuilder.java
    src/terminalmenu/Comando.java
    src/terminalmenu/OpcionMenu.java

**2)** Compila el código:

    bash
    javac -d out/ src/terminalmenu/*.java

**3)** Empaqueta el JAR

`jar cf terminal-menu.jar -C out/ .`

Con esto deberías tener el archivo terminal-menu.jar listo para usarse en cualquier proyecto.

## ▶️Usar el JAR desde la línea de comandos
Ejemplo de ejecución de un programa que usa el menú:

`java -cp .:terminal-menu.jar nombredelaclasemain`

En Windows reemplaza : por ;

`java -cp .;terminal-menu.jar nombredelaclasemain`

## 🧩 Usar en BlueJ

1. Copia el archivo terminal-menu.jar en algún directorio de tu computadora.
1. Abre BlueJ → Tools → Preferences → Libraries.
1. En la sección User Libraries, agrega terminal-menu.jar.
1. Reinicia BlueJ.
1. Ahora puedes importar clases desde terminalmenu.* en tus proyectos.

## 🖥️ Usar en NetBeans (proyectos Ant)

1. Abre tu proyecto en NetBeans.
1. Haz clic derecho en el nodo del proyecto → Properties.
1. Ve a Libraries.
1. Haz clic en Add JAR/Folder y selecciona terminal-menu.jar.
1. NetBeans agregará el JAR al classpath de compilación y ejecución.

## 🏗️ Construir un menú con MenuBuilder

El MenuBuilder usa method chaining para encadenar opciones y submenús de manera fluida.

### Ejemplo: Sistema de Pizzas

    import terminalmenu.Menu;
    import terminalmenu.MenuBuilder;
    
    public class Main {
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

### Interacción esperada
    === SISTEMA DE PEDIDOS DE PIZZAS ===
    1. Ingresar Cliente
    2. Ingresar Pizzero
    3. Gestionar Pizzas
    4. Registrar Pedido
    5. Mostrar Pedido
    6. Salir
    Seleccione una opción: 3

    === SUBMENÚ DE PIZZAS ===
    1. Agregar Pizza
    2. Listar Pizzas
    3. Volver
    Seleccione una opción:

### 🔑Ejemplo Realista: Menú de Login

    import terminalmenu.Menu;
    import terminalmenu.MenuBuilder;
    
    public class MainLogin {
        private static boolean autenticado = false;
    
        public static void main(String[] args) {
            Menu menuPrincipal = new MenuBuilder("SISTEMA DE LOGIN", false)
                .opcion("Iniciar Sesión", () -> login())
                .opcion("Registrarse", () -> registrar())
                .opcion("Recuperar Contraseña", () -> recuperar())
                .opcion("Salir", () -> {
                    System.out.println("Cerrando el sistema...");
                    System.exit(0);
                })
                .build();
    
            menuPrincipal.mostrar();
        }
    
        private static void login() {
            // Aquí pondrías la lógica real de autenticación (pedir usuario/clave)
            System.out.println("[LOGIN] Ingrese su usuario y contraseña...");
            autenticado = true; // simula login correcto
            System.out.println("¡Bienvenido al sistema!");
        }
    
        private static void registrar() {
            // Aquí pondrías la lógica para registrar un nuevo usuario
            System.out.println("[REGISTRO] Ingrese sus datos para crear una cuenta...");
        }
    
        private static void recuperar() {
            // Aquí pondrías la lógica de recuperación de contraseña
            System.out.println("[RECUPERACIÓN] Ingrese su correo electrónico...");
        }
    }

### 💻 Ejecución esperada

    === SISTEMA DE LOGIN ===
    1. Iniciar Sesión
    2. Registrarse
    3. Recuperar Contraseña
    4. Salir
    Seleccione una opción: 1
    [LOGIN] Ingrese su usuario y contraseña...
    ¡Bienvenido al sistema!

### 🔎 Puntos interesantes
El menú está desacoplado de la lógica de negocio: cada opción llama a un método (login(), registrar(), recuperar()), pero podrías reemplazarlo por comandos más complejos.

Es fácil de extender. Por ejemplo para que, una vez logueado, se muestre un submenú de usuario autenticado (ejemplo: Ver Perfil, Cerrar Sesión, etc).  
  
### 🔑Ejemplo más completo: Menú de Login con Submenú para Usuario Autenticado

    import terminalmenu.Menu;
    import terminalmenu.MenuBuilder;
    
    public class MainLogin {
        private static boolean autenticado = false;
        private static Menu menuPrincipal;
        private static Menu menuUsuario;
    
        public static void main(String[] args) {
            // Menú disponible después de iniciar sesión
            menuUsuario = new MenuBuilder("MENÚ DEL USUARIO", true)
                .opcion("Ver Perfil", () -> verPerfil())
                .opcion("Cambiar Contraseña", () -> cambiarClave())
                .opcion("Cerrar Sesión", () -> logout())
                .build();
    
            // Menú principal de login
            menuPrincipal = new MenuBuilder("SISTEMA DE LOGIN", false)
                .opcion("Iniciar Sesión", () -> login())
                .opcion("Registrarse", () -> registrar())
                .opcion("Recuperar Contraseña", () -> recuperar())
                .opcion("Salir", () -> {
                    System.out.println("Cerrando el sistema...");
                    System.exit(0);
                })
                .build();
    
            // Arranca en el menú principal
            menuPrincipal.mostrar();
        }
    
        private static void login() {
            System.out.println("[LOGIN] Ingrese su usuario y contraseña...");
            autenticado = true; // Simula login correcto
            System.out.println("¡Bienvenido al sistema!");
            menuUsuario.mostrar(); // Abre el menú del usuario
        }
    
        private static void registrar() {
            System.out.println("[REGISTRO] Ingrese sus datos para crear una cuenta...");
        }
    
        private static void recuperar() {
            System.out.println("[RECUPERACIÓN] Ingrese su correo electrónico...");
        }
    
        private static void verPerfil() {
            System.out.println("[PERFIL] Mostrando información del usuario...");
        }
    
        private static void cambiarClave() {
            System.out.println("[CAMBIO DE CLAVE] Ingrese su nueva contraseña...");
        }
    
        private static void logout() {
            autenticado = false;
            System.out.println("Sesión cerrada. Regresando al menú principal...");
            // vuelve al menú principal al cerrar sesión
            menuPrincipal.mostrar();
        }
    }

### 💻 Ejecución esperada
    === SISTEMA DE LOGIN ===
    1. Iniciar Sesión
    2. Registrarse
    3. Recuperar Contraseña
    4. Salir
    Seleccione una opción: 1
    [LOGIN] Ingrese su usuario y contraseña...
    ¡Bienvenido al sistema!
    
    === MENÚ DEL USUARIO ===
    1. Ver Perfil
    2. Cambiar Contraseña
    3. Cerrar Sesión
    4. Volver
    Seleccione una opción: 3
    Sesión cerrada. Regresando al menú principal...
    
    === SISTEMA DE LOGIN ===
    1. Iniciar Sesión
    2. Registrarse
    3. Recuperar Contraseña
    4. Salir

### 🔎 Fíjate bien!
- **menuUsuario** se construye con `true` → con esto le dices al Builder que es un submenu y este automáticamente incluirá la opción "Volver" al final del submenu.
- Al hacer logout(), cerramos sesión y volvemos al menú principal (menuPrincipal.mostrar()).
- Este patrón se puede usar para flujos más complejos (ejemplo: menú de admin, menú de invitado, etc.).