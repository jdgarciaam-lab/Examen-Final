package modelo;
import java.util.ArrayList;

public class Administrador extends Usuario {

    public Administrador(String nombre, String dni, String correo) {
        super(nombre, dni, correo);
    }

    public void agregarHabitacion(ArrayList<Habitacion> habitaciones, int numero, String tipo, double precio) {
        habitaciones.add(new Habitacion(numero, tipo, precio));
    }

    
    public void verHabitaciones(ArrayList<Habitacion> habitaciones) {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas en el sistema.");
            return;
        }
        
        System.out.println("\n=== LISTADO DE TODAS LAS HABITACIONES ===");
        
        // Habitaciones Simples (100)
        System.out.println("\n--- Habitaciones Simples ---");
        for (Habitacion h : habitaciones) {
            if (h.getNumero() < 200) {
                h.mostrarInfo();
            }
        }
        System.out.println("--------------------------------");
        
        // Habitaciones Dobles (200)
        System.out.println("\n--- Habitaciones Dobles ---");
        for (Habitacion h : habitaciones) {
            if (h.getNumero() >= 200 && h.getNumero() < 300) {
                h.mostrarInfo();
            }
        }
        System.out.println("--------------------------------");
        
        // Suites (300)
        System.out.println("\n--- Suites ---");
        for (Habitacion h : habitaciones) {
            if (h.getNumero() >= 300) {
                h.mostrarInfo();
            }
        }
        System.out.println("--------------------------------");
    }
}