// Exceção para quando o funcionário ultrapassa o limite diário de horas
public class ExcessoJornadaException extends Exception {

    private String nomeColaborador;
    private String diaRegistro;
    private double horasLancadas;
    private double limitePermitido;

    public ExcessoJornadaException(String nomeColaborador, String diaRegistro,
                                   double horasLancadas, double limitePermitido) {

        super("Excesso de jornada identificado para " + nomeColaborador);

        this.nomeColaborador = nomeColaborador;
        this.diaRegistro = diaRegistro;
        this.horasLancadas = horasLancadas;
        this.limitePermitido = limitePermitido;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public String getDiaRegistro() {
        return diaRegistro;
    }

    public double getHorasLancadas() {
        return horasLancadas;
    }

    public double getLimitePermitido() {
        return limitePermitido;
    }

}