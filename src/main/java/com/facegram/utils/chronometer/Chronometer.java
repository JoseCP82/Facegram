package com.facegram.utils.chronometer;

import com.facegram.logging.Logging;
import com.facegram.utils.message.InfoMessage;
import java.time.LocalTime;

public class Chronometer extends Thread {

    /**
     * Atributos de clase
     */
    private final int TIME_MAX = 5;
    private LocalTime loginTime;
    private String sessionTime;
    private boolean isExceed;

    /**
     * Constructor por defecto
     */
    public Chronometer() {
        this.loginTime = LocalTime.now();
        this.sessionTime ="Horas: 0" + "\nMinutos: 0" + "\nSegundos: 0";
        this.isExceed = false;
    }

    /**
     * Obtiene el tiempo que ha permanecido conectado el usuario
     * @return String con el tiempo de que ha durado la sesión
     */
    public String getSessionTime() {
        return this.sessionTime;
    }

    /**
     * Método run de Thread
     */
    public void run() {
        int seconds = 0;
        try {
            while (!this.isInterrupted()) {
                Thread.sleep(1000);
                calculateSessionTime(++seconds);
                if (!this.isExceed) {
                    if(isOverTime()){
                        //Cambiar sout por message cuando funcione
                        /*
                        new InfoMessage("Ha excedido el tiempo recomendado de uso en Facegram.\n" +
                            "Desde el equipo de Facegram le sugerimos que descanse periodicamente.").showMessage();
                        */

                        System.out.println("Ha excedido el tiempo recomendado de uso en Facegram.\n" +
                                "Desde el equipo de Facegram le sugerimos que descanse periodicamente.");
                        this.isExceed = true;
                    }
                }
            }
        } catch (InterruptedException e) {
            Logging.warningLogging(e+"");
        }
    }

    /**
     * Método por el cual sabremos si el usuario a excedido de tiempo recomendado su sesión
     * @return True o false si excedió el tiempo o no
     */
    private boolean isOverTime() {
        return loginTime.plusSeconds(TIME_MAX).isAfter(LocalTime.now()); // Modificar a minutos con plusMinutes
    }

    /**
     * Calcula cuanto tiempo ha durado la sesión del usuario
     * @param seconds Tiempo en segundos que lleva conectado el usuario.
     */
    private void calculateSessionTime(int seconds){
        int hours = seconds / 3600;
        seconds %= 3600;
        int minuts = seconds / 60;
        seconds %= 60;
        this.sessionTime = String.valueOf("Horas: "+ hours + "\nMinutos: " + minuts + "\nSegundos: " +seconds);
    }
}
