package comercio;

public class GestorOrden {

    private Orden orden1, orden2;

    public void encolarOrden(Orden orden){

        if(this.orden1 == null){

            this.orden1 = orden;
        } else if(this.orden2 == null){

            this.orden2 = orden;
        }
        else {

            System.out.println("No se puede gestionar mas ordenes");
        }
    }

    @Override
    public String toString() {

        String s1 = "Pedidos registrados: \n";
        StringBuilder sb = new StringBuilder();
        if(orden1 != null){

            sb.append(this.orden1.toString()).append("\n");
        }
        if(orden2 != null){
            sb.append(this.orden2.toString()).append("\n");
        }
        if(orden1 == null && orden2 == null){

            return "No hay pedidos registrados";
        }
        String s2 = sb.toString();
        return s1+s2;
    }
}
