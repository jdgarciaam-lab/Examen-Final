# HotelSit – Sistema de Reservas de Hotel

---

## Integrantes del grupo

- Michael Santiago Cordero Moreno (192667) – Programador de cliente y lógica de reservas
- Juan David Garcia Amaya (192595) – Encargada de clases base y herencia
- Juan Jose Sanchez Vacca (192618) – Programador del administrador y gestión de habitaciones
- Alejandro Martinez Guerrero (192559) – Lógica del menú en Main y validaciones

---

## Descripción del problema y solución propuesta

### Problema

La gestión manual de reservas en hoteles es propensa a errores, duplicidades y pérdida de información. Además, la falta de automatización dificulta la consulta de disponibilidad y la administración eficiente de habitaciones y servicios adicionales.

### Solución

HotelSit automatiza el proceso de reservas, permitiendo:
- Registro de clientes y administradores.
- Visualización y filtrado de habitaciones por tipo.
- Reserva y cancelación de habitaciones.
- Selección de planes de alimentación.
- Gestión centralizada de habitaciones por parte del administrador.

---

## Explicación de clases y métodos principales

La aplicación está desarrollada en Java, siguiendo el paradigma de Programación Orientada a Objetos. El código fuente se encuentra en la carpeta `src/` y está organizado en los siguientes paquetes:

- `modelo`: Clases de dominio (usuarios, habitaciones, reservas, etc.)
- `vista`: Interfaz de usuario por consola
- `controlador`: Lógica de negocio y coordinación entre vista y modelo

### Clases principales

#### `modelo.Usuario`
Clase abstracta base para usuarios del sistema.
- **Atributos:** `nombre`, `dni`, `correo`
- **Métodos:** Getters

#### `modelo.Cliente`
Hereda de Usuario. Representa a un cliente que puede realizar reservas.
- **Atributos:** `ArrayList<Reserva> reservas`
- **Métodos:** 
  - `reservarHabitacion(Habitacion, String, String, PlanAlimentacion)`
  - `getReservas()`
  - `cancelarReserva(int)`

#### `modelo.Administrador`
Hereda de Usuario. Permite gestionar habitaciones.
- **Métodos:** 
  - `agregarHabitacion(ArrayList<Habitacion>, int, String, double)`
  - `verHabitaciones(ArrayList<Habitacion>)`

#### `modelo.Habitacion`
Representa una habitación del hotel.
- **Atributos:** `numero`, `tipo`, `precio`, `disponible`
- **Métodos:** 
  - `mostrarInfo()`
  - Getters y setters

#### `modelo.Reserva`
Representa una reserva realizada por un cliente.
- **Atributos:** `cliente`, `habitacion`, `fechaEntrada`, `fechaSalida`, `planAlimentacion`
- **Métodos:** 
  - Getters
  - `getPrecioTotal()`

#### `modelo.PlanAlimentacion`
Define los diferentes planes de alimentación disponibles.
- **Atributos:** `tipo`, `precio`, `descripcion`
- **Métodos:** 
  - `mostrarInfo()`
  - Getters

#### `modelo.GestorHabitaciones`
Inicializa y gestiona el inventario de habitaciones.
- **Atributos:** `ArrayList<Habitacion> habitaciones`
- **Métodos:** 
  - Inicialización de habitaciones por tipo y precio
  - `getHabitaciones()`

#### `vista.HotelVista`
Interfaz de usuario por consola. Muestra menús, solicita datos y muestra información relevante.
- **Métodos:** 
  - `mostrarMenu()`
  - `mostrarHabitacionesDisponibles(ArrayList<Habitacion>)`
  - `mostrarReservas(ArrayList<Reserva>)`
  - `pedirDatoCliente(String, String, String)`
  - `pedirFecha(String)`
  - `mostrarMensaje(String)`
  - `leerEntero()`
  - `leerLinea()`
  - `cerrar()`

#### `controlador.HotelControlador`
Orquesta la lógica principal del sistema, conecta la vista con el modelo y gestiona el flujo de la aplicación.
- **Métodos:** 
  - `iniciar()`
  - `verHabitacionesDisponibles()`
  - `realizarReserva()`
  - `verReservasCliente()`
  - `verTodasLasReservas()`
  - `cancelarReserva()`

#### `Main`
Punto de entrada de la aplicación.

---

## Instrucciones para ejecutar el programa

1. **Compilar todos los archivos Java**  
   Desde la carpeta `src/` ejecuta:
   ```sh
   javac Main.java controlador/*.java modelo/*.java vista/*.java
   ```

2. **Ejecutar el programa**  
   Desde la carpeta `src/` ejecuta:
   ```sh
   java Main
   ```

---

## Ejemplo de uso

### Menú principal (ejemplo en consola):

```
=== MENÚ PRINCIPAL ===
1. Ver habitaciones disponibles
2. Realizar una reserva
3. Ver mis reservas
4. Cancelar una reserva
5. Registrar otro cliente
6. Ver todas las reservas
0. Salir
Seleccione una opción:
```

### Ejemplo de reserva

```
Ingrese el número de habitación a reservar: 101
Fecha de entrada (formato: dd/MM/yyyy): 10/07/2024
Fecha de salida (formato: dd/MM/yyyy): 12/07/2024

=== PLANES DE ALIMENTACIÓN ===
1. Plan Básico
Plan Básico
Precio: $15.0
Incluye: Solo desayuno

2. Plan Intermedio
Plan Intermedio
Precio: $25.0
Incluye: Desayuno y almuerzo

3. Plan Buffet
Plan Buffet
Precio: $40.0
Incluye: Todas las comidas

0. Sin plan

Seleccione un plan (0-3):
```

---

## Estructura del proyecto

```
src/
  Main.java
  controlador/
    HotelControlador.java
  modelo/
    Administrador.java
    Cliente.java
    GestorHabitaciones.java
    Habitacion.java
    PlanAlimentacion.java
    Reserva.java
    Usuario.java
  vista/
    HotelVista.java
```

---

## Capturas de pantalla

