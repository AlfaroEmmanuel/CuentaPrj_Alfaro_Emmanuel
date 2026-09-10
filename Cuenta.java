import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase Cuenta - representa una cuenta bancaria.
 * Implementada de acuerdo con el diagrama UML provisto en la actividad.
 *
 * @author (tu nombre)
 * @version (fecha)
 */
public class Cuenta
{
    // ===== Atributos de instancia =====
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;

    // ===== Atributo de clase (static) =====
    private static int cantCuentasCreadas = 0;

    /**
     * Constructor que recibe el nombre del cuentahabiente y el saldo inicial.
     */
    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;

        cantCuentasCreadas++;
        codCuenta = "cta-" + cantCuentasCreadas;

        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        fechaCreacion = formatoFecha.format(fecha);

        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
    }

    /**
     * Constructor que recibe únicamente el saldo inicial.
     * El nombre del cuentahabiente queda pendiente de asignar
     * (ver setNombreCuentaHabiente).
     */
    public Cuenta(double pSaldo) {
        this("Sin asignar", pSaldo);
    }

    /**
     * Permite asignar (o corregir) el nombre del cuentahabiente.
     */
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        nombreCuentaHabiente = pNombreCuentaHabiente;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    /**
     * Deposita un monto en la cuenta y retorna el saldo actualizado.
     */
    public double depositar(double monto) {
        saldo += monto;
        cantDepositosRealizados++;
        return saldo;
    }

    /**
     * Retira un monto de la cuenta, únicamente si hay fondos suficientes.
     * Si no hay fondos suficientes, retorna el saldo sin alteración.
     */
    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
            return saldo;
        } else {
            return saldo;
        }
    }

    /**
     * Valida si un retiro es posible: el saldo debe ser suficiente
     * y el monto debe ser positivo.
     */
    private boolean validarRetiro(double monto) {
        return saldo >= monto && monto > 0;
    }

    /**
     * Método de clase: retorna la cantidad total de cuentas creadas.
     */
    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }

    /**
     * Representación completa del estado del objeto.
     */
    public String toString() {
        return "Código: " + codCuenta +
               " | Cuentahabiente: " + nombreCuentaHabiente +
               " | Saldo: " + saldo +
               " | Fecha creación: " + fechaCreacion +
               " | Depósitos realizados: " + cantDepositosRealizados +
               " | Retiros exitosos: " + cantRetirosExitososRealizados;
    }
}
