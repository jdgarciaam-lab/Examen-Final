package controlador;

import modelo.*;
import vista.HotelVista;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HotelControlador {
    private HotelVista vista;
    private Cliente cliente;
    private Administrador admin;
    private GestorHabitaciones gestor;
    private HashMap<String, Cliente> clientes = new HashMap<>();
    private static final PlanAlimentacion PLAN_BASICO = new PlanAlimentacion("Básico", 15.0, "Solo desayuno");
    private static final PlanAlimentacion PLAN_INTERMEDIO = new PlanAlimentacion("Intermedio", 25.0, "Desayuno y almuerzo");
    private static final PlanAlimentacion PLAN_BUFFET = new PlanAlimentacion("Buffet", 40.0, "Todas las comidas");

    public HotelControlador(HotelVista vista) {
        this.vista = vista;
        this.admin = new Administrador("Admin", "00000000", "admin@hotel.com");
        this.gestor = new GestorHabitaciones(admin);
        iniciarSesion();
    }

    private void iniciarSesion() {
        vista.mostrarMensaje("=== REGISTRO DE CLIENTE ===");

        String nombre = vista.pedirDatoCliente("nombre completo",
                "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+",
                "El nombre solo puede contener letras y espacios.");

        String dni = vista.pedirDatoCliente("documento de identidad",
                "\\d+",
                "El documento solo puede contener números.");

        this.cliente = new Cliente(nombre, dni, "cliente@hotel.com");
        clientes.put(dni, cliente);
        vista.mostrarMensaje("\n¡Bienvenido/a " + nombre + "!");
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            try {
                vista.mostrarMensaje("\n=== MENÚ PRINCIPAL ===");
                vista.mostrarMensaje("1. Ver habitaciones disponibles");
                vista.mostrarMensaje("2. Realizar una reserva");
                vista.mostrarMensaje("3. Ver mis reservas");
                vista.mostrarMensaje("4. Cancelar una reserva");
                vista.mostrarMensaje("5. Registrar otro cliente");
                vista.mostrarMensaje("6. Ver todas las reservas");
                vista.mostrarMensaje("0. Salir");
                vista.mostrarMensaje("Seleccione una opción:");

                opcion = vista.leerEntero();

                switch (opcion) {
                    case 1: verHabitacionesDisponibles(); break;
                    case 2: realizarReserva(); break;
                    case 3: verReservasCliente(); break;
                    case 4: cancelarReserva(); break;
                    case 5: iniciarSesion(); break;
                    case 6: verTodasLasReservas(); break;
                    case 0: vista.mostrarMensaje("\n¡Gracias por usar HotelSoft!"); break;
                    default: vista.mostrarMensaje("\nOpción no válida.");
                }

                if (opcion != 0) {
                    vista.mostrarMensaje("\nPresione ENTER para continuar...");
                    vista.leerLinea();
                }
            } catch (NumberFormatException e) {
                vista.mostrarMensaje("\nError: Debe ingresar un número válido.");
            } catch (Exception e) {
                vista.mostrarMensaje("\nError: " + e.getMessage());
            }
        }
    }

    private void verHabitacionesDisponibles() {
        vista.mostrarHabitacionesDisponibles(gestor.getHabitaciones());
    }

    private void realizarReserva() {
        verHabitacionesDisponibles();
        try {
            vista.mostrarMensaje("\nIngrese el número de habitación a reservar: ");
            int numHabitacion = vista.leerEntero();

            Habitacion habitacion = null;
            for (Habitacion h : gestor.getHabitaciones()) {
                if (h.getNumero() == numHabitacion && h.isDisponible()) {
                    habitacion = h;
                    break;
                }
            }

            if (habitacion != null) {
                LocalDate fechaEntrada = null;
                LocalDate fechaSalida = null;
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                while (true) {
                    try {
                        String entradaTexto = vista.pedirFecha("entrada (formato: dd/MM/yyyy)").trim();
                        String salidaTexto = vista.pedirFecha("salida (formato: dd/MM/yyyy)").trim();
                        fechaEntrada = LocalDate.parse(entradaTexto, formato);
                        fechaSalida = LocalDate.parse(salidaTexto, formato);

                        if (!fechaSalida.isAfter(fechaEntrada)) {
                            vista.mostrarMensaje("\nError: La fecha de salida debe ser posterior a la de entrada.");
                        } else if (fechaEntrada.isBefore(LocalDate.now())) {
                            vista.mostrarMensaje("\nError: La fecha de entrada no puede estar en el pasado.");
                        } else {
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        vista.mostrarMensaje("\nError: Formato de fecha inválido. Usa dd/MM/yyyy.");
                    }
                }

                vista.mostrarMensaje("\n=== PLANES DE ALIMENTACIÓN ===");
                vista.mostrarMensaje("1. Plan Básico");
                PLAN_BASICO.mostrarInfo();
                vista.mostrarMensaje("\n2. Plan Intermedio");
                PLAN_INTERMEDIO.mostrarInfo();
                vista.mostrarMensaje("\n3. Plan Buffet");
                PLAN_BUFFET.mostrarInfo();
                vista.mostrarMensaje("\n0. Sin plan");

                int planSeleccionado = -1;
                while (true) {
                    vista.mostrarMensaje("\nSeleccione un plan (0-3): ");
                    try {
                        planSeleccionado = vista.leerEntero();
                        if (planSeleccionado >= 0 && planSeleccionado <= 3) break;
                        vista.mostrarMensaje("\nOpción inválida. Intente de nuevo.");
                    } catch (NumberFormatException e) {
                        vista.mostrarMensaje("\nError: Debe ingresar un número entre 0 y 3.");
                    }
                }

                PlanAlimentacion plan = null;
                switch (planSeleccionado) {
                    case 1: plan = PLAN_BASICO; break;
                    case 2: plan = PLAN_INTERMEDIO; break;
                    case 3: plan = PLAN_BUFFET; break;
                }

                cliente.reservarHabitacion(habitacion, fechaEntrada.toString(), fechaSalida.toString(), plan);
            } else {
                vista.mostrarMensaje("Habitación no disponible o no existe.");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Error: Número de habitación inválido.");
        }
    }

    private void verReservasCliente() {
        vista.mostrarMensaje("\n=== RESERVAS DE " + cliente.getNombre() + " (DNI: " + cliente.getDni() + ") ===");
        vista.mostrarReservas(cliente.getReservas());
    }

    private void verTodasLasReservas() {
        vista.mostrarMensaje("\n=== TODAS LAS RESERVAS ===");
        for (Cliente c : clientes.values()) {
            vista.mostrarMensaje("\nCliente: " + c.getNombre() + " (DNI: " + c.getDni() + ")");
            vista.mostrarReservas(c.getReservas());
        }
    }

    private void cancelarReserva() {
        try {
            vista.mostrarMensaje("\nIngrese el número de habitación a cancelar: ");
            int numHabitacion = vista.leerEntero();
            cliente.cancelarReserva(numHabitacion);
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Error: Número de habitación inválido.");
        }
    }
}
