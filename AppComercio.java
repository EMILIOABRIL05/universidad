package comercio;

import java.util.Scanner;
import java.util.Locale;


public class AppComercio {

    private static Scanner sc = new Scanner(System.in);

    private static int leerEntero(String promt, int min, int max){

        int valor = 0;
        boolean salir = false;

        while(!salir){

            System.out.println(promt);
            try{

                valor = Integer.parseInt(sc.nextLine());
                if(valor < min || valor > max){

                    throw new NumberFormatException("Rango invalido");
                }


            } catch (Exception e) {

                System.out.println("Entrada invalida, intente de nuevo");
            }
            salir = true;
        }
        return valor;
    }


    private static double leerDouble(String promt, double min, double max){

        double valor = 0;
        boolean salir = false;

        while(!salir){

            System.out.println(promt);
            try{

                valor = Double.parseDouble(sc.nextLine());

                if(valor < min || valor > max){

                    throw new NumberFormatException("Rango invalido");
                }


            } catch (Exception e) {

                System.out.println("Entrada invalida, intente de nuevo");
            }

            salir = true;
        }

        return valor;
    }

    private static String leerString(String promt){

        String s = "";
        boolean salir = false;

        while(!salir){

            System.out.println(promt);
            s = sc.nextLine().trim();
            if(!s.isEmpty()){

                return s;
            } else {

                System.out.println("No se puede tener una lectura vacia");
            }
        }
        return s;
    }

    private static Cliente leerCliente(){

        System.out.println("Datos de cliente:");
        String nombre = leerString("Nombre: ");
        String email = leerString("Correo Electronico: ");
        String cedula = leerString("Cedula sin guion: ");
        String id = leerString("ID de cliente: ");
        int edad = leerEntero("Edad en anios: ",18,90);
        return new Cliente(nombre, email, edad, cedula, id);
    }
    private static Vendedor leerVendedor(){

        System.out.println("Datos del vendedor:");
        String nombre = leerString("Nombre: ");
        String email = leerString("Correo Electronico: ");
        String cedula = leerString("Cedula sin guion: ");
        String id = leerString("ID de vendedor: ");
        int edad = leerEntero("Edad en anios: ",25,65);
        return new Vendedor(nombre, email, edad, cedula, id);
    }

    private static Producto seleccionarProducto(Producto pr1, Producto pr2, Producto pr3, Producto pr4){

        System.out.printf("Catalogo de productos:%n1. %s.%n2. %s.%n3. %s.%n4. %s.%n",pr1.toString(), pr2.toString(), pr3.toString(), pr4.toString());
        int opcion = leerEntero("Opcion(1-4)",1,4);
        switch (opcion){

            case(1):
                return pr1;
                //break; este break es inalcanzable, por lo tanto no debe ir cuando la funcion del case es un return
            case(2):
                return pr2;
                //break;
            case(3):
                return pr3;
                //break;
            default:
                return pr4;
                //break;
        }
    }

    private static void crearOrden(Producto pr1, Producto pr2, Producto pr3, Producto pr4, GestorOrden gestor){

        try{

            Cliente cl1 = leerCliente();
            Vendedor vr1 = leerVendedor();
            Orden or1 = new Orden(cl1, vr1);

            int numItems = leerEntero("Ingrese los productos(1-3): ",1,3);

            for(int i = 0; i < numItems; i++){

                Producto seleccionado = seleccionarProducto(pr1, pr2, pr3, pr4);
                int cantidad = leerEntero("Unidades del producto: ",1,Integer.MAX_VALUE);//Integer.MAX_VALUE;
                 //maximo valor que puede ser almacenado como entero
                or1.agregarItem(seleccionado, cantidad);
            }

            or1.calculoTotal();
            double pago = leerDouble("Ingrese el pago: ",0,Double.MAX_VALUE);
            or1.pago(pago);

            gestor.encolarOrden(or1);
            System.out.println("Perdido creado: \n" + or1.toString());

        } catch (Exception e){

        }
    }

    public static void main(String[] args) {

        sc.useLocale(Locale.US);

        //declaracion de productos a ser generados
        Producto pr1 = new Producto("P001","Arroz",1.0,1.2,100);
        Producto pr2 = new Producto("P002","Papas",1.0,1.5,50);
        Producto pr3 = new Producto("P003","Avena",0.5,.6,40);
        Producto pr4 = new Producto("P004","Frejol",0.5,1.2,50);

        GestorOrden gestor = new GestorOrden();

        boolean salida = false;

        while (!salida){

            System.out.println("Menu de ventas\n1.Crear pedido.\n2. Ver pedidos.\n3. Salir.\nSeleccione una opcion:");
            int opcion = leerEntero("Opcion: ", 1,3);
            switch (opcion){

                case(1):
                   crearOrden(pr1, pr2, pr3, pr4, gestor);
                    break;
                case(2):
                    gestor.toString();
                    break;
                case(3):
                    salida = true;
                    break;
                default:
                    System.out.println("Ingreso incorrecto, intente nuevamente");
                    break;
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }
}
