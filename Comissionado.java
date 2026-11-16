// Classe para funcionários que recebem por comissão
public class Comissionado extends Funcionario {

    private double vendasBrutas;
    private double percentualComissao;

    public Comissionado(String nome, double vendasBrutas, double percentualComissao) {
        super(nome);

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

        this.vendasBrutas = vendasBrutas;
        this.percentualComissao = percentualComissao;
    }

    // Calcula o pagamento com base nas vendas
    @Override
    public double calcularPagamento() {
        return vendasBrutas * (percentualComissao / 100);
    }

}