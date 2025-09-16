
/**
 * Builder para facilitar la construcción de menús.
 * Permite agregar opciones y submenús de manera fluida.
 * @author <a href="mailto:philone@focused.cl">Felipe M.</a>
 */
public class MenuBuilder
{
    private Menu menu;
    private int contador = 1;
    
    /**
     * Crea un builder para construir un menú.
     *
     * @param titulo Título del menú.
     * @param esSubmenu Indica si es un submenú (true) o el menú principal (false).
     */
    public MenuBuilder(String titulo, boolean esSubmenu) {
        menu = new Menu(titulo, esSubmenu);
    }
    
    /**
     * Agrega una opción al menú.
     *
     * @param descripcion Texto que describe la opción.
     * @param comando Acción que se ejecutará al seleccionarla.
     * @return El mismo builder para encadenar llamadas.
     */
    public MenuBuilder opcion(String descripcion, Comando comando) {
        menu.agregarOpcion(contador++, descripcion, comando);
        return this;
    }
    
    /**
     * Agrega un submenú como opción dentro del menú.
     *
     * @param descripcion Texto que describe la opción del submenú.
     * @param submenuBuilder Builder que construirá el submenú.
     * @return El mismo builder para encadenar llamadas.
     */
    public MenuBuilder submenu(String descripcion, MenuBuilder submenuBuilder) {
        Menu submenu = submenuBuilder.build();
        menu.agregarOpcion(contador++, descripcion, () -> submenu.mostrar());
        return this;
    }
    
    /**
     * Construye el menú final.
     * Si es un submenú, agrega automáticamente una opción "Volver".
     *
     * @return El menú construido.
     */
    public Menu build() {
        // agregar automáticamente opción de volver si es submenú
        if (menu.esSubmenu()) {
            menu.agregarOpcion(contador++, "Volver", () -> menu.salir());
        }
        return menu;
    }
}