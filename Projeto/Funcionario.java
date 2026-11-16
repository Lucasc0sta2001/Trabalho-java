// Classe principal que representa um funcionário
public abstract class Funcionario implements Pagavel {

    private String nome;

    public Funcionario(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException(
                "O nome do funcionario nao pode ficar vazio."
            );
        }

        this.nome = nome;
    }

    // Retorna o nome do funcionário
    public String getNome() {
        return nome;
    }

    // Retorna o nome como identificação do pagamento
    @Override
    public String getDescricao() {
        return nome;
    }

    // Cada tipo de funcionário calcula seu próprio pagamento
    @Override
    public abstract double calcularPagamento();

    // Realiza o estorno do pagamento do funcionário
    @Override
    public void estornar() {
        System.out.println("Estorno do pagamento de: " + nome);
    }

}
