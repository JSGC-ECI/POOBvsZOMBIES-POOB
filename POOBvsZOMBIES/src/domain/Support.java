package domain;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Support extends Plant{
    public int sunValue;
    public int time;
    private ScheduledExecutorService scheduler;

    /**
     * Constructor for objects of class Support
     */
    public Support(int hitPoints, int costSun, int sunValue, int time) {
        super(hitPoints, costSun);
        this.sunValue = sunValue;
        this.time = time * 1000;
        generateSun();
    }

    public int getSunValue() {
        return sunValue;
    }

    public double getTime() {
        return time;
    }

    /**
     * Inicia la generación de soles en intervalos especificados
     * Este método utilizará un temporizador para ejecutar la tarea cada cierto tiempo
     */
    public void generateSun() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            GameManager.getInstance().collectSun(getSunValue());
            System.out.println("Generando " + getSunValue() + " soles...");
        }, 0, time, TimeUnit.MILLISECONDS);  // 0 retraso inicial, repite cada 'time' milisegundos
    }

    /**
     * Detiene la generación de soles (por ejemplo, cuando la planta muere)
     */
    public void stopGeneratingSun() {
        if (scheduler != null) {
            scheduler.shutdown();  // Detenemos el temporizador
        }
    }
}
