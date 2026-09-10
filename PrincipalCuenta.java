import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase PrincipalCuenta - permite interactuar con múltiples objetos
 * de tipo Cuenta mediante un menú de opciones.
 *
 * @author (tu nombre)
 * @version (fecha)
 */
public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1; // índice de la cuenta seleccionada

        boolean salir = false;
        while (!salir) {
            System.out.println("\n===== MENÚ CUENTA =====");
            System.out.println("1) Crear cuenta");
            System.out.println("2) Conocer cantidad de cuentas creadas");
            System.out.println("3) Listar cuentas");
            System.out.println("4) Seleccionar cuenta actual");
            System.out.println("5) Asignar nombre del cuentahabiente (cuenta actual)");
            System.out.println("6) Depositar (cuenta actual)");
            System.out.println("7) Retirar (cuenta actual)");
            System.out.println("8) Consultar saldo (cuenta actual)");
            System.out.println("9) Consultar estado de la cuenta (actual)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1": { // Crear cuenta
                    System.out.print("¿Desea ingresar el nombre del cuentahabiente ahora? (s/n): ");
                    String resp = sc.nextLine().trim();

                    System.out.print("Saldo inicial: ");
                    double saldoInicial;
                    try {
                        saldoInicial = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido. No se creó la cuenta.");
                        break;
                    }

                    Cuenta c;
                    if (resp.equalsIgnoreCase("s")) {
                        System.out.print("Nombre del cuentahabiente: ");
                        String nombre = sc.nextLine().trim();
                        c = new Cuenta(nombre, saldoInicial);
                    } else {
                        c = new Cuenta(saldoInicial);
                    }
                    cuentas.add(c);
                    actual = cuentas.size() - 1;
                    System.out.println("Cuenta creada y seleccionada: " + c.getCodCuenta());
                    break;
                }
                case "2": { // Cantidad de cuentas creadas
                    System.out.println("Cantidad total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": { // Listar cuentas
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas.");
                    } else {
                        for (int i = 0; i < cuentas.size(); i++) {
                            System.out.println(i + ") " + cuentas.get(i).toString());
                        }
                    }
                    break;
                }
                case "4": { // Seleccionar cuenta actual
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas para seleccionar.");
                        break;
                    }
                    System.out.print("Índice de la cuenta a seleccionar (0 a " + (cuentas.size() - 1) + "): ");
                    int idx;
                    try {
                        idx = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                        break;
                    }
                    if (idx >= 0 && idx < cuentas.size()) {
                        actual = idx;
                        System.out.println("Cuenta seleccionada: " + cuentas.get(actual).getCodCuenta());
                    } else {
                        System.out.println("Índice fuera de rango.");
                    }
                    break;
                }
                case "5": { // Asignar nombre del cuentahabiente
                    if (actual < 0) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Nuevo nombre del cuentahabiente: ");
                    String nombre = sc.nextLine().trim();
                    cuentas.get(actual).setNombreCuentaHabiente(nombre);
                    System.out.println("Nombre actualizado.");
                    break;
                }
                case "6": { // Depositar
                    if (actual < 0) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    double monto;
                    try {
                        monto = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                        break;
                    }
                    double nuevoSaldo = cuentas.get(actual).depositar(monto);
                    System.out.println("Saldo actualizado: " + nuevoSaldo);
                    break;
                }
                case "7": { // Retirar
                    if (actual < 0) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    double monto;
                    try {
                        monto = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                        break;
                    }
                    double saldoAntes = cuentas.get(actual).getSaldo();
                    double saldoDespues = cuentas.get(actual).retirar(monto);
                    if (saldoDespues == saldoAntes) {
                        System.out.println("Fondos insuficientes o monto inválido. Saldo sin cambios: " + saldoDespues);
                    } else {
                        System.out.println("Retiro exitoso. Saldo actualizado: " + saldoDespues);
                    }
                    break;
                }
                case "8": { // Consultar saldo
                    if (actual < 0) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.println("Saldo actual: " + cuentas.get(actual).getSaldo());
                    break;
                }
                case "9": { // Consultar estado (toString)
                    if (actual < 0) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                case "0": { // Salir
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
