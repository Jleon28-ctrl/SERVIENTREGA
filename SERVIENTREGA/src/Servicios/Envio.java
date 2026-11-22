package Servicios;

public class Envio {
    private String guia;
    private String destinatario;
    private String ciudadDestino;
    private double pesoKg;

    public Envio(String guia, String destinatario, String ciudadDestino, double pesoKg) {
        this.guia = guia;
        this.destinatario = destinatario;
        this.ciudadDestino = ciudadDestino;
        setPesoKg(pesoKg);
    }

    public String getGuia() { return guia; }
    public void setGuia(String guia) { this.guia = guia; }
    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    public String getCiudadDestino() { return ciudadDestino; }
    public void setCiudadDestino(String ciudadDestino) { this.ciudadDestino = ciudadDestino; }
    public double getPesoKg() { return pesoKg; }

    public void setPesoKg(double peso) {
        if (peso > 0 && Double.isFinite(peso)) {
            this.pesoKg = peso;
        }
    }

    public int kilosFacturables() {
        int completo = (int) pesoKg;
        if (pesoKg > completo) {
            completo += 1;
        }
        return completo;
    }

    public double calcularCosto() {
        return kilosFacturables() * 2.00;
    }

    public String describir() {
        return String.format("Guia: %s | Destinatario: %s | Ciudad: %s | Peso: %.2f",
                guia, destinatario, ciudadDestino, pesoKg);
    }
}