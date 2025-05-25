package comercio;

public interface Pagos {

    double calculoTotal();

    void pago(double montoPago) throws ExPagos;
}
