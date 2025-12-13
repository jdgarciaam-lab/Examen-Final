package modelo;
import java.util.ArrayList;

public class GestorHabitaciones {
    private ArrayList<Habitacion> habitaciones;
    private Administrador admin;

    public GestorHabitaciones(Administrador admin) {
        this.habitaciones = new ArrayList<>();
        this.admin = admin;
        inicializarHabitaciones();
        System.out.println("Sistema inicializado con " + habitaciones.size() + " habitaciones.");
    }

    private void inicializarHabitaciones() {
        // Habitaciones Simples
        admin.agregarHabitacion(habitaciones, 101, "Simple", 50.0);
        admin.agregarHabitacion(habitaciones, 102, "Simple", 50.0);
        admin.agregarHabitacion(habitaciones, 103, "Simple", 50.0);
        admin.agregarHabitacion(habitaciones, 104, "Simple", 50.0);

        

        // Habitaciones Dobles
        admin.agregarHabitacion(habitaciones, 201, "Doble", 75.0);
        admin.agregarHabitacion(habitaciones, 202, "Doble", 75.0);
        admin.agregarHabitacion(habitaciones, 203, "Doble", 75.0);
        admin.agregarHabitacion(habitaciones, 204, "Doble", 75.0);

        // Suites
        admin.agregarHabitacion(habitaciones, 301, "Suite", 100.0);
        admin.agregarHabitacion(habitaciones, 302, "Suite", 100.0);
        admin.agregarHabitacion(habitaciones, 303, "Suite", 100.0);
        admin.agregarHabitacion(habitaciones, 304, "Suite", 100.0);
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}