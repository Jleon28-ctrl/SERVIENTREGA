package Servicios;

public class EnvioInternacional extends Envio {
    private double valorFoUsd;

    public EnvioInternacional(String guia, String destinatario, String ciudadDestino, double pesoKg, double valorFoUsd) {
        super(guia, destinatario, ciudadDestino, pesoKg);
        this.valorFoUsd = valorFoUsd;
    }

    public double getValorFoUsd() { return valorFoUsd; }
    public void setValorFoUsd(double valorFoUsd) { this.valorFoUsd = valorFoUsd; }

    public boolean cumple4x4() {
        return getPesoKg() <= 4.0 && this.valorFoUsd <= 400.0;
    }

    @Override
    public double calcularCosto() {
        return (kilosFacturables() * 6.00) + 20.00;
    }

    @Override
    public String describir() {
        return super.describir() + " | Internacional | Valor FOB: $" + this.valorFoUsd;
    }
}