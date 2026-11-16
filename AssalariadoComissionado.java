// Classe para funcionários que recebem salário fixo e comissão
public class AssalariadoComissionado extends Funcionario {

    private double salarioBase;
    private double vendasBrutas;
    private double percentualComissao;

    public AssalariadoComissionado(String nome, double salarioBase,
                                   double vendasBrutas, double percentualComissao) {
        super(nome);

        if (salarioBase < 0) {
            throw new DadosInvalidosException(
                "O salario base nao pode ser negativo."
            );
        }

        if (vendasBrutas < 0) {
            throw new DadosInvalidosException(
                "As vendas nao podem ser negativas."
            );
        }

        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new DadosInvalidosException(
                "A comissao deve estar entre 0 e 100."
            );
        }

        this.salarioBase = salarioBase;
        this.vendasBrutas = vendasBrutas;
        this.percentualComissao = percentualComissao;
    }

    // Calcula o salário com aumento e adiciona a comissão
    @Override
    public double calcularPagamento() {

        double salarioComAumento = salarioBase * 1.10;

        double comissao = vendasBrutas * (percentualComissao / 100);

        return salarioComAumento + comissao;
    }

}