package domain;

/**
 * Represents custom exceptions specific to the POOBvsZOMBIES game.
 * This class extends the RuntimeException class and is used to handle
 * various error scenarios that may occur within the game.
 * It provides predefined error messages for common issues.
 */
public class POOBvsZOMBIESException extends RuntimeException {
    public static final String INVALID_CONFIG = "Configuración inválida.";
    public static final String NO_PLANT = "No se pudo seleccionar la planta.";
    public static final String NO_ZOMBIE = "No se pudo seleccionar el zombi.";

    /**
     * Constructs a new POOBvsZOMBIESException with the specified detail message.
     * This exception is thrown to indicate specific error scenarios within the
     * POOBvsZOMBIES game.
     *
     * @param message the detail message, which provides more information about
     *                the reason for the exception.
     */
    public POOBvsZOMBIESException(String message) {
        super(message);
    }
}
