package domain;

public class POOBvsZOMBIESException extends RuntimeException {
    public static final String INVALID_CONFIG = "Configuración inválida.";
    public static final String NO_PLANT = "No se pudo seleccionar la planta.";
    public static final String NO_ZOMBIE = "No se pudo seleccionar el zombi.";

    public POOBvsZOMBIESException(String message) {
        super(message);
    }
}
