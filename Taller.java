package ManejoArchivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Taller {

    public static void main(String[] args) {
contarPalabras();

    }
    // Ejercicio de escritura 1: Generar 1000 numeros aleatorios entre 5 y 500

    public static void generarNumerosAleatorios() {
        try (PrintWriter writer = new PrintWriter("C:\\ManejoArchivos\\numerosAleatorios.txt")) {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                writer.println(random.nextInt(496) + 5);
            }
        } catch (IOException e) {
            System.err.println("Error al generar numeros: " + e.getMessage());
        }
    }
    // Ejercicio de escritura 2: Solicitar nombres y guardarlos en un archivo

    public static void guardarNombres() {
        try (PrintWriter writer = new PrintWriter("C:\\ManejoArchivos\\nombres.txt")) {
            Scanner scanner = new Scanner(System.in);
            String respuesta;
            do {
                System.out.print("Ingrese un nombre: ");
                String nombre = scanner.nextLine().toLowerCase();
                writer.println(nombre);
                System.out.print("Desea ingresar otro nombre? (si/no): ");
                respuesta = scanner.nextLine().toLowerCase();
            } while (respuesta.equals("si"));
        } catch (IOException e) {
            System.err.println("Error al guardar nombres: " + e.getMessage());
        }
    }

    // Ejercicio de escritura 3: Guardar nombre, notas y promedio de alumnos
    public static void guardarAlumnos() {
        try (PrintWriter writer = new PrintWriter("C:\\ManejoArchivos\\alumnos.txt")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el numero de alumnos: ");
            int n = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Ingrese el nombre del alumno: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese la nota del primer parcial: ");
                String nota1Str = scanner.nextLine().replace(",", ".");
                double nota1 = Double.parseDouble(nota1Str);

                System.out.print("Ingrese la nota del segundo parcial: ");
                String nota2Str = scanner.nextLine().replace(",", ".");
                double nota2 = Double.parseDouble(nota2Str);

                double promedio = (nota1 + nota2) / 2;
                writer.printf("%s;%.2f;%.2f;%.2f%n", nombre, nota1, nota2, promedio);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar alumnos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de número no válido."
                    + " Asegúrese de usar números con comas o puntos correctamente.");
        }
    }

    // Ejercicio de escritura 4: Guardar detalle de una factura
    public static void guardarFactura() {
        try (PrintWriter writer = new PrintWriter("C:\\ManejoArchivos\\factura.txt")) {
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < 10; i++) {
                System.out.print("Ingrese el nombre del producto: ");
                String producto = scanner.nextLine();
                System.out.print("Ingrese la cantidad: ");
                int cantidad = scanner.nextInt();
                System.out.print("Ingrese el precio: ");
                double precio = scanner.nextDouble();
                scanner.nextLine();
                double total = cantidad * precio;
                writer.printf("%s-%.2f-%.2f%n", producto, precio, total);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar factura: " + e.getMessage());
        }
    }

    // Ejercicio de escritura 5: Guardar informacion de triangulos rectangulos
    public static void guardarTriangulos() {
        try (PrintWriter writer = new PrintWriter("C:\\ManejoArchivos\\triangulos.txt")) {
            Scanner scanner = new Scanner(System.in);
            String respuesta;
            do {
                System.out.print("Ingrese la base del triangulo: ");
                double base = scanner.nextDouble();
                System.out.print("Ingrese la altura del triangulo: ");
                double altura = scanner.nextDouble();
                scanner.nextLine();
                double area = (base * altura) / 2;
                writer.printf("%.2f|%.2f|%.2f%n", base, altura, area);
                System.out.print("Desea ingresar otro triangulo? (si/no): ");
                respuesta = scanner.nextLine().toLowerCase();
            } while (respuesta.equals("si"));
        } catch (IOException e) {
            System.err.println("Error al guardar triangulos: " + e.getMessage());
        }
    }
// Ejercicio de escritura 6: Guardar informacion de registro civil

    public static void guardarRegistroCivil() {
        try (PrintWriter writer = new PrintWriter("C:\\ManejoArchivos\\registroCivil.txt")) {
            Scanner scanner = new Scanner(System.in);
            String respuesta;
            do {
                System.out.print("Ingrese la cedula: ");
                String cedula = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese el nombre: ");
                String nombre = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese el apellido: ");
                String apellido = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese el estado civil: ");
                String estadoCivil = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese el telefono: ");
                String telefono = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese el genero: ");
                String genero = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese el tipo de sangre: ");
                String tipoSangre = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese la fecha de nacimiento: ");
                String fechaNacimiento = scanner.nextLine().toUpperCase();
                System.out.print("Ingrese la edad: ");
                int edad = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de linea

                writer.printf("%s;%s;%s;%s;%s;%s;%s;%s;%d%n", cedula, nombre, apellido,
                        estadoCivil, telefono, genero, tipoSangre, fechaNacimiento, edad);
                System.out.print("Desea ingresar otra persona? (si/no): ");
                respuesta = scanner.nextLine().toLowerCase();
            } while (respuesta.equals("si"));
        } catch (IOException e) {
            System.err.println("Error al guardar registro civil: " + e.getMessage());
        }
    }
    // Ejercicio de lectura 1: Contar numeros multiplos de 21, 32, 41, 52

    public static void contarMultiplos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\ManejoArchivos\\numerosAleatorios.txt"))) {
            int[] multiplos = {21, 32, 41, 52};
            int[] conteo = new int[multiplos.length];
            String linea;
            while ((linea = reader.readLine()) != null) {
                int numero = Integer.parseInt(linea);
                for (int i = 0; i < multiplos.length; i++) {
                    if (numero % multiplos[i] == 0) {
                        conteo[i]++;
                    }
                }
            }
            for (int i = 0; i < multiplos.length; i++) {
                System.out.printf("Multiplos de %d: %d%n", multiplos[i], conteo[i]);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    // Ejercicio de lectura 2: Sumar multiplos de 4 en posiciones pares

    public static void sumarMultiplosEnPosicionesPares() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\ManejoArchivos\\numerosAleatorios.txt"))) {
            int suma = 0;
            int posicion = 0;
            String linea;
            while ((linea = reader.readLine()) != null) {
                int numero = Integer.parseInt(linea);
                if (posicion % 2 == 1 && numero % 4 == 0) {
                    suma += numero;
                }
                posicion++;
            }
            System.out.printf("Suma de multiplos de 4 en posiciones pares: %d%n", suma);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Ejercicio de lectura 3: Nombres con longitud mayor a 3 que comiencen con 'b'
    public static void nombresConLetraB() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\ManejoArchivos\\nombres.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.length() > 3 && linea.toLowerCase().startsWith("b")) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Ejercicio de lectura 4: Nombres con longitud impar o que comiencen con 'a'
    public static void nombresConLongitudImparOConA() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\ManejoArchivos\\nombres.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.length() % 2 != 0 || linea.toLowerCase().startsWith("a")) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Ejercicio de lectura 5: Alumnos con promedio mayor a 8 y nombre que comience con 'c' o 'e'
    public static void alumnosConPromedioYNombre() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\ManejoArchivos\\alumnos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                String nombre = datos[0];

                String promedioStr = datos[3].replace(",", ".");
                double promedio;

                try {
                    promedio = Double.parseDouble(promedioStr);
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir promedio: " + promedioStr);
                    continue;
                }

                if (promedio > 8 && (nombre.toLowerCase().startsWith("c")
                        || nombre.toLowerCase().startsWith("e"))) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Ejercicio de lectura 6: Productos con precio entre 10 y 20 dolares
    public static void productosConPrecioEnRango() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\ManejoArchivos\\factura.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("-");
                String producto = datos[0];
                double precio = Double.parseDouble(datos[1].replace(",", "."));
                if (precio >= 10 && precio <= 20) {
                    System.out.println(producto + ", Total: " + datos[2]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    // Ejercicio de lectura 7: Triangulos donde la base es mayor
    //que la altura o el area es menor que la altura
    public static void triangulosConCondiciones() {
        try (BufferedReader reader = new BufferedReader
        (new FileReader("C:\\ManejoArchivos\\triangulos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                double base = Double.parseDouble(datos[0].replace(",", ".")); 
                double altura = Double.parseDouble(datos[1].replace(",", ".")); 
                double area = Double.parseDouble(datos[2].replace(",", ".")); 
                if (base > altura || area < altura) {
                    System.out.println("Base: " + base + ", Altura: " + altura + ", Area: " + area);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

// Ejercicio de lectura 8: Productos cuyo nombre empieza con una vocal
public static void productosConNombreVocal() {
    try (BufferedReader reader = new BufferedReader
        (new FileReader("C:\\ManejoArchivos\\factura.txt"))) {
        String linea;
        char[] vocales = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split("-");
            String producto = datos[0].trim();
            if (!producto.isEmpty()) {
                char primerCaracter = producto.charAt(0);
                for (char vocal : vocales) {
                    if (primerCaracter == vocal) {
                        System.out.println(producto);
                        break;
                    }
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
}

    // Ejercicio de lectura 9: Contar palabras con condiciones especificas
 
          public static void contarPalabras() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\listado-general.txt"))) {
            String linea;
            int[] resultados = new int[4];

            while ((linea = br.readLine()) != null) {
                if (linea.length() >= 4 && esVocalBasico(linea.charAt(3))) {
                    resultados[0]++;
                }
                if (linea.length() >= 5 && linea.charAt(4) == 'M') {
                    resultados[1]++;
                }
                if (linea.length() >= 4 && linea.charAt(0) == 'a' && linea.charAt(3) == 'f') {
                    resultados[2]++;
                }
                if (linea.length() >= 6 && linea.charAt(0) == 'g' && linea.charAt(2) == 's' && linea.charAt(5) == 'o') {
                    resultados[3]++;
                }
            }

            System.out.println("Condicion a: Vocal en la cuarta posicion: " + resultados[0]);
            System.out.println("Condicion b: 'M' en la quinta posicion: " + resultados[1]);
            System.out.println("Condicion c: 'a' en la primera y 'f' en la cuarta posicion: " + resultados[2]);
            System.out.println("Condicion d: 'g' en la primera, 's' en la tercera y 'o' en la sexta posicion: " + resultados[3]);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static boolean esVocalBasico(char c) {
        char[] vocales = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char vocal : vocales) {
            if (c == vocal) {
                return true;
            }
        }
        return false;
    }
}





