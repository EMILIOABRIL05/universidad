package comercio;

public class GestorOrden {
    private Orden[] ordenes;
    private int numOrdenes;
    private static final int MAX_ORDENES = 100;

    public GestorOrden() {
        this.ordenes = new Orden[MAX_ORDENES];
        this.numOrdenes = 0;
    }

    public void encolarOrden(Orden orden) {
        if (numOrdenes < MAX_ORDENES) {
            ordenes[numOrdenes++] = orden;
        }
    }

    public boolean tieneOrdenes() {
        return numOrdenes > 0;
    }

    @Override
    public String toString() {
        if (!tieneOrdenes()) {
            return "No hay órdenes registradas";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOrdenes; i++) {
            sb.append(String.format("%nOrden %d:%n", i + 1));
            sb.append(ordenes[i].toString());
            sb.append("\n----------------------------------------\n");
        }
        return sb.toString();
    }
}