import Servicios.Envio;
import Servicios.EnvioNacional;
import Servicios.EnvioExpress;
import Servicios.EnvioInternacional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Envio> enTransito = new HashMap<>();
    private final List<Envio> entregados = new ArrayList<>();
    private int contadorGuia = 1001;

    public static void main(String[] args) { new Main().iniciar(); }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE ENVÍOS ===");
            System.out.println("1. Registrar envío");
            System.out.println("2. Consultar envío");
            System.out.println("3. Entregar envío");
            System.out.println("4. Listar entregados");
            System.out.println("5. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1: registrar(); break;
                case 2: consultar(); break;
                case 3: entregar(); break;
                case 4: listarEntregados(); break;
                case 5: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    // --- MÉTODOS DE LECTURA ---
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un entero válido."); }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(leerTexto(mensaje));
                if (Double.isFinite(valor)) return valor;
            } catch (NumberFormatException ex) { }
            System.out.println("Ingrese un número decimal válido (use punto).");
        }
    }

    private boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje).toUpperCase();
            if (respuesta.equals("S")) return true;
            if (respuesta.equals("N")) return false;
            System.out.println("Responda S o N.");
        }
    }

    // --- LÓGICA DEL NEGOCIO ---
    private void registrar() {
        System.out.println("1. Nacional | 2. Express | 3. Internacional");
        int tipo = leerEntero("Tipo de servicio: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de servicio inválido.");
            return;
        }

        String destinatario = leerTexto("Nombre del destinatario: ");
        String ciudad = leerTexto("Ciudad de destino: ");
        double peso = leerDouble("Peso en kilos: ");

        if (peso <= 0) {
            System.out.println("El peso debe ser mayor a cero.");
            return;
        }

        String prefijo = "";
        if (tipo == 1) prefijo = "NAC";
        else if (tipo == 2) prefijo = "EXP";
        else if (tipo == 3) prefijo = "INT";

        Envio envio = null;
        boolean rechazado = false;

        if (tipo == 1 || tipo == 2) {
            boolean domicilio = leerSiNo("¿Entrega a domicilio? (S/N): ");

            if (tipo == 1) {
                String guia = prefijo + String.valueOf(contadorGuia);
                envio = new EnvioNacional(guia, destinatario, ciudad, peso, domicilio);
            } else {
                boolean es24h = leerSiNo("¿Cliente eligió entrega en 24 horas? (S/N): ");
                String guia = prefijo + String.valueOf(contadorGuia);
                if (es24h) {
                    envio = new EnvioExpress(guia, destinatario, ciudad, peso, domicilio, 24);
                } else {
                    envio = new EnvioExpress(guia, destinatario, ciudad, peso, domicilio);
                }
            }
        } else if (tipo == 3) {
            double valorCompra = leerDouble("Valor de la compra en dólares: ");
            String guia = prefijo + String.valueOf(contadorGuia);
            EnvioInternacional envioInt = new EnvioInternacional(guia, destinatario, ciudad, peso, valorCompra);

            if (!envioInt.cumple4x4()) {
                System.out.println("Envío rechazado por la regla 4x4. No se asigna guía.");
                rechazado = true;
            } else {
                envio = envioInt;
            }
        }

        // Si no fue rechazado, se guarda en el HashMap
        if (!rechazado) {
            enTransito.put(envio.getGuia(), envio);
            System.out.println("¡Envío registrado exitosamente!");
            System.out.println("Guía asignada: " + envio.getGuia());
            System.out.printf("Costo: %.2f%n", envio.calcularCosto());
            contadorGuia++; // Solo avanza si se registra
        }
    }

    private void consultar() {
        String guia = leerTexto("Ingrese la guía a consultar: ").toUpperCase();
        if (!enTransito.containsKey(guia)) {
            System.out.println("No existe un envío en tránsito con esa guía.");
            return;
        }
        Envio e = enTransito.get(guia);
        System.out.println("--- Datos del envío ---");
        System.out.println(e.describir());
        System.out.printf("Costo: %.2f%n", e.calcularCosto());
    }

    private void entregar() {
        String guia = leerTexto("Ingrese la guía a entregar: ").toUpperCase();
        if (!enTransito.containsKey(guia)) {
            System.out.println("No existe un envío en tránsito con esa guía.");
            return;
        }
        Envio e = enTransito.remove(guia);
        entregados.add(e);
        System.out.println("Envío " + guia + " entregado correctamente.");
    }

    private void listarEntregados() {
        if (entregados.isEmpty()) {
            System.out.println("Aún no hay envíos entregados.");
            return;
        }
        System.out.println("--- LISTA DE ENTREGADOS ---");
        double total = 0;
        for (Envio e : entregados) {
            System.out.println(e.describir());
            System.out.printf("Costo: %.2f%n", e.calcularCosto());
            total += e.calcularCosto();
        }
        System.out.printf("TOTAL RECAUDADO: %.2f%n", total);
    }
}