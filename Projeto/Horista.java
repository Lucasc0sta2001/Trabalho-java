// Classe para funcionários que recebem por hora trabalhada
public class Horista extends Funcionario {

    private double valorHora;
    private double[] horasPorDia;

    private String[] diasDaSemana = {
        "Segunda-feira",
        "Terca-feira",
        "Quarta-feira",
        "Quinta-feira",
        "Sexta-feira"
    };

    // Recebe o nome, valor da hora e horas trabalhadas por dia
    public Horista(String nome, double valorHora, double[] horasPorDia) {
        super(nome);

        if (valorHora < 0) {
            throw new DadosInvalidosException(
                "O valor da hora nao pode ser negativo."
            );
        }

        if (horasPorDia == null || horasPorDia.length != 5) {
            throw new DadosInvalidosException(
                "Informe as horas dos cinco dias da semana."
            );
        }

        for (double horas : horasPorDia) {
            if (horas < 0) {
                throw new DadosInvalidosException(
                    "As horas trabalhadas nao podem ser negativas."
                );
            }
        }

        this.valorHora = valorHora;
        this.horasPorDia = horasPorDia.clone();
    }

    // Soma as horas trabalhadas durante a semana
    public double calcularHorasSemana() {
        double total = 0;

        for (double horas : horasPorDia) {
            total += horas;
        }

        return total;
    }

    // Verifica se o funcionário ultrapassou o limite diário
    public void verificarHorasExtras() throws ExcessoJornadaException {

        for (int i = 0; i < horasPorDia.length; i++) {

            if (horasPorDia[i] > 10) {

                throw new ExcessoJornadaException(
                    getNome(),
                    diasDaSemana[i],
                    horasPorDia[i],
                    10
                );
            }
        }
    }

    // Calcula o pagamento semanal
    @Override
    public double calcularPagamento() {

        double horasTrabalhadas = calcularHorasSemana();

        if (horasTrabalhadas <= 40) {
            return valorHora * horasTrabalhadas;
        } else {

            double horasNormais = 40;
            double horasExtras = horasTrabalhadas - 40;

            return (horasNormais * valorHora)
                    + (horasExtras * valorHora * 1.5);
        }
    }

}
