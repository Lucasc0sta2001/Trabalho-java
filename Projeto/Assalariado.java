// Classe para funcionários que recebem salário fixo
public class Assalariado extends Funcionario {

    private double salarioSemanal;

    public Assalariado(String nome, double salarioSemanal) {
        super(nome);

        if (salarioSemanal < 0) {
            throw new DadosInvalidosException(
                "O salario semanal nao pode ser negativo."
            );
        }

        this.salarioSemanal = salarioSemanal;
    }

    @Override
    public double calcularPagamento() {
        return salarioSemanal;
    }

}
