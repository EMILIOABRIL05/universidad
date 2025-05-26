package comercio;

import java.util.Scanner;
import java.util.Locale;

public class AppComercio {
    private static Scanner sc = new Scanner(System.in);

    private static int leerEntero(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = sc.nextLine().trim();
                int valor = Integer.parseInt(input);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("Error: El valor debe estar entre %d y %d. Intente nuevamente:%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido. Intente nuevamente:");
            }
        }
    }

    private static String leerString(String prompt) {
        while (true) {
            System.out.println(prompt);
            String valor = sc.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Error: Este campo no puede estar vacío. Intente nuevamente:");
        }
    }

    private static Cliente leerCliente() {
        System.out.println("\nDatos del cliente:");
        String nombre = leerString("Nombre: ");
        String email = leerString("Correo Electrónico: ");
        String cedula = leerString("Cédula sin guión: ");
        String id = leerString("ID de cliente: ");
        int edad = leerEntero("Edad en años: ", 18, 90);
        return new Cliente(nombre, email, edad, cedula, id);
    }

    private static Vendedor leerVendedor() {
        System.out.println("\nDatos del vendedor:");
        String nombre = leerString("Nombre: ");
        String email = leerString("Correo Electrónico: ");
        String cedula = leerString("Cédula sin guión: ");
        String id = leerString("ID de vendedor: ");
        int edad = leerEntero("Edad en años: ", 25, 65);
        return new Vendedor(nombre, email, edad, cedula, id);
    }

    private static Producto seleccionarProducto(Producto pr1, Producto pr2, Producto pr3, Producto pr4) {
        System.out.println("\nCatálogo de productos:");
        System.out.printf("1. %s%n2. %s%n3. %s%n4. %s%n",
                pr1.toString(), pr2.toString(), pr3.toString(), pr4.toString());
        int opcion = leerEntero("Opción(1-4): ", 1, 4);
        switch (opcion) {
            case 1: return pr1;
            case 2: return pr2;
            case 3: return pr3;
            default: return pr4;
        }
    }

    private static double leerPago(double totalAPagar) {
        while (true) {
            try {
                System.out.println("Ingrese el monto del pago:");
                String input = sc.nextLine().trim().replace(",", ".");
                double pago = Double.parseDouble(input);

                if (pago < totalAPagar) {
                    System.out.printf("Error: Pago insuficiente. Debe pagar al menos: $%.2f%n", totalAPagar);
                    continue;
                }
                return pago;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un monto válido");
            }
        }
    }

    private static void crearOrden(Producto pr1, Producto pr2, Producto pr3, Producto pr4, GestorOrden gestor) {
        try {
            Cliente cl1 = leerCliente();
            Vendedor vr1 = leerVendedor();
            Orden or1 = new Orden(cl1, vr1);

            int numItems = leerEntero("Ingrese la cantidad de productos (1-3): ", 1, 3);

            for (int i = 0; i < numItems; i++) {
                try {
                    Producto seleccionado = seleccionarProducto(pr1, pr2, pr3, pr4);
                    System.out.printf("Stock disponible: %d%n", seleccionado.getStock());
                    int cantidad = leerEntero("Unidades del producto: ", 1, seleccionado.getStock());
                    or1.agregarItem(seleccionado, cantidad);
                } catch (ExCantidadInvalida e) {
                    System.out.println("Error: " + e.getMessage());
                    i--; // Permite reintentar
                }
            }

            // Mostrar resumen de la compra
            double subtotal = or1.calculoTotal();
            double totalConDescuento = or1.aplicarDescuento(subtotal);
            System.out.println("\nResumen de la compra:");
            System.out.printf("Subtotal: $%.2f%n", subtotal);
            System.out.printf("Descuento (10%%): $%.2f%n", subtotal - totalConDescuento);
            System.out.printf("Total a pagar: $%.2f%n", totalConDescuento);

            // Procesar el pago
            double pago = leerPago(totalConDescuento);
            or1.pago(pago);

            // Mostrar el cambio si corresponde
            if (pago > totalConDescuento) {
                System.out.printf("Su cambio es: $%.2f%n", pago - totalConDescuento);
            }

            gestor.encolarOrden(or1);
            System.out.println("\n¡Pedido creado exitosamente!");

        } catch (Exception e) {
            System.out.println("Error al crear la orden: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Configurar el locale para usar punto como separador decimal
        Locale.setDefault(new Locale("en", "US"));
        sc = new Scanner(System.in).useLocale(Locale.US);

        // Crear productos
        Producto pr1 = new Producto("P001", "Arroz", 1.0, 1.2, 100);
        Producto pr2 = new Producto("P002", "Papas", 1.0, 1.5, 50);
        Producto pr3 = new Producto("P003", "Avena", 0.5, 0.6, 40);
        Producto pr4 = new Producto("P004", "Frejol", 0.5, 1.2, 50);

        GestorOrden gestor = new GestorOrden();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú de Ventas ===");
            System.out.println("1. Crear pedido");
            System.out.println("2. Ver pedidos");
            System.out.println("3. Salir");
            System.out.println("====================");

            int opcion = leerEntero("Seleccione una opción: ", 1, 3);

            switch (opcion) {
                case 1:
                    crearOrden(pr1, pr2, pr3, pr4, gestor);
                    break;
                case 2:
                    if (gestor.tieneOrdenes()) {
                        System.out.println("\n=== Pedidos Registrados ===");
                        System.out.println(gestor.toString());
                    } else {
                        System.out.println("\nNo hay pedidos registrados aún");
                    }
                    break;
                case 3:
                    salir = true;
                    break;
            }
        }

        System.out.println("\n¡Gracias por usar el sistema!");
        sc.close();
    }
}