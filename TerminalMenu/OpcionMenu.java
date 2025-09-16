
/**
 * Representa una opción dentro de un menú.
 * Cada opción tiene una descripción y un comando que se ejecuta al seleccionarla.
 * @author <a href="mailto:philone@focused.cl">Felipe M.</a>
 */
public class OpcionMenu
{
    private String descripcion;
    private Comando comando;    
    
    /**
     * Constructor de la opción de menú.
     * 
     * @param descripcion Texto que se mostrará al usuario.
     * @param comando Acción que se ejecutará al seleccionar esta opción.
     */
    public OpcionMenu(String descripcion, Comando comando) {
        this.descripcion = descripcion;
        this.comando = comando;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
     /**
     * Ejecuta el comando asociado a la opción.
     */
    public void ejecutar() {
        comando.ejecutar();
    }
}