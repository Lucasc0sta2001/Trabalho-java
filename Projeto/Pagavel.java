// Interface dos itens que podem ser pagos
public interface Pagavel {

    // Retorna o valor do pagamento
    double calcularPagamento();

    // Retorna a identificação do pagamento
    String getDescricao();

    // Realiza o estorno do pagamento
    void estornar();

}
