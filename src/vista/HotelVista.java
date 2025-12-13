package vista;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.*;

    



public class HotelVista {
    private Scanner input;

    public HotelVista() {
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE RESERVAS HOTELSOFT ===");
        System.out.println("1. Ver habitaciones disponibles");
        System.out.println("2. Reservar habitación");
        System.out.println("3. Ver mis reservas");
        System.out.println("4. Cancelar reserva");
        System.out.println("0. Salir");
        System.out.print("\nIngrese su opción: ");
    }

    public void mostrarHabitacionesDisponibles(ArrayList<Habitacion> habitaciones) {
        System.out.println("\n=== HABITACIONES DISPONIBLES ===");
        
        System.out.println("\n--- Habitaciones Simples ---");
        mostrarHabitacionesPorTipo(habitaciones, 100, 200);
        
        System.out.println("\n--- Habitaciones Dobles ---");
        mostrarHabitacionesPorTipo(habitaciones, 200, 300);
        
        System.out.println("\n--- Suites ---");
        mostrarHabitacionesPorTipo(habitaciones, 300, 400);
    }

    private void mostrarHabitacionesPorTipo(ArrayList<Habitacion> habitaciones, 
                                          int inicio, int fin) {
        boolean hayDisponibles = false;
        for (Habitacion h : habitaciones) {
            if (h.isDisponible() && h.getNumero() >= inicio && h.getNumero() < fin) {
                h.mostrarInfo();
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("No hay habitaciones disponibles de este tipo");
        }
        System.out.println("--------------------------------");
    }

    public void mostrarReservas(ArrayList<Reserva> reservas) {
        if (reservas.isEmpty()) {
            System.out.println("\nNo tienes reservas activas.");
            return;
        }

        System.out.println("\n=== TUS RESERVAS ===");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.println("\nReserva #" + (i + 1));
            System.out.println("--------------------------------");
            mostrarDetallesReserva(r);
            System.out.println("--------------------------------");
        }
    }

    private void mostrarDetallesReserva(Reserva r) {
        System.out.println("Habitación: " + r.getHabitacion().getNumero());
        System.out.println("Tipo: " + r.getHabitacion().getTipo());
        System.out.println("Precio habitación: $" + r.getHabitacion().getPrecio());
        System.out.println("Fecha entrada: " + r.getFechaEntrada());
        System.out.println("Fecha salida: " + r.getFechaSalida());
        
        if (r.getPlanAlimentacion() != null) {
            System.out.println("Plan: " + r.getPlanAlimentacion().getTipo());
            System.out.println("Precio plan: $" + r.getPlanAlimentacion().getPrecio());
        } else {
            System.out.println("Sin plan de alimentación");
        }
        System.out.println("PRECIO TOTAL POR NOCHE: $" + r.getPrecioTotal());
    }

    public String pedirDatoCliente(String dato, String regex, String mensajeError) {
        String valor;
        while (true) {
            System.out.print("Ingrese su " + dato + ": ");
            valor = input.nextLine();
            if (valor.matches(regex)) {
                break;
            }
            System.out.println("\nError: " + mensajeError);
        }
        return valor;
    }

    public String pedirFecha(String tipo) {
        System.out.print("Fecha de " + tipo + ": ");
        return input.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int leerEntero() {
        return Integer.parseInt(input.nextLine());
    }

    public String leerLinea() {
        return input.nextLine();
    }

    public void cerrar() {
        input.close();
    }
}

