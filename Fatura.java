
// Classe que representa uma fatura
public class Fatura implements Pagavel {

    private String descricao;
    private int quantidade;
    private double valorUnitario;

    // Recebe os dados da fatura
    public Fatura(String descricao, int quantidade, double valorUnitario) {

        if (quantidade <= 0) {
            throw new DadosInvalidosException(
                "A quantidade da fatura deve ser maior que zero."
            );
        }

        if (valorUnitario < 0) {
            throw new DadosInvalidosException(
                "O valor unitario nao pode ser negativo."
            );
        }

        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    // Altera a quantidade da fatura
    public void setQuantidade(int quantidade) {

        if (quantidade <= 0) {
            throw new DadosInvalidosException(
                "A quantidade da fatura deve ser maior que zero."
            );
        }

        this.quantidade = quantidade;
    }

    // Altera o valor unitario da fatura
    public void setValorUnitario(double valorUnitario) {

        if (valorUnitario < 0) {
            throw new DadosInvalidosException(
                "O valor unitario nao pode ser negativo."
            );
        }

        this.valorUnitario = valorUnitario;
    }

    // Calcula o valor total da fatura
    @Override
    public double calcularPagamento() {
        return quantidade * valorUnitario;
    }

    // Retorna a descrição da fatura
    @Override
    public String getDescricao() {
        return descricao;
    }

    // Realiza o estorno da fatura
    @Override
    public void estornar() {
        System.out.println("Estorno da fatura: " + descricao);
    }

}
