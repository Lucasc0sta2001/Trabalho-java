// Classe principal do programa
public class Main {

    public static void main(String[] args) {

        // Executa os três cenários solicitados no trabalho
        executarCaminhoFeliz();

        executarViolacaoTrabalhista();

        executarRollback();
    }

    // Primeiro cenário: pagamentos válidos
    public static void executarCaminhoFeliz() {

        System.out.println("\n===== CENARIO 1: CAMINHO FELIZ =====");

        Pagavel[] pagamentos = {

            new Fatura("Fatura de servicos", 2, 250.00),

            new Assalariado("Carlos", 2500.00),

            new Horista("Ana", 20.00,
                    new double[]{8, 8, 8, 8, 8})
        };

        ServicoFolha servico = new ServicoFolha();

        try {
            servico.processarFolha(pagamentos);
        } catch (FalhaProcessamentoFolhaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Segundo cenário: funcionário ultrapassa o limite diário
    public static void executarViolacaoTrabalhista() {

        System.out.println("\n===== CENARIO 2: VIOLACAO TRABALHISTA =====");

        Pagavel[] pagamentos = {

            new Assalariado("Carlos", 2500.00),

            new Horista("Pedro", 20.00,
                    new double[]{8, 8, 12, 8, 8})
        };

        ServicoFolha servico = new ServicoFolha();

        try {
            servico.processarFolha(pagamentos);

        } catch (FalhaProcessamentoFolhaException e) {

            System.out.println("\n===== ERRO CAPTURADO NO MAIN =====");
            System.out.println(e.getMessage());

            // Exibe a pilha de chamadas e a causa original
            e.printStackTrace();
        }
    }

    // Terceiro cenário: falha no quarto pagamento e rollback
    public static void executarRollback() {

        System.out.println("\n===== CENARIO 3: ROLLBACK =====");

        Pagavel[] pagamentos = {

            new Fatura("Fatura de servicos", 1, 500.00),

            new Assalariado("Carlos", 2500.00),

            new Horista("Ana", 20.00,
                    new double[]{8, 8, 8, 8, 8}),

            new Horista("Pedro", 20.00,
                    new double[]{8, 8, 12, 8, 8}),

            new Comissionado("Mariana", 10000.00, 10.0)
        };

        ServicoFolha servico = new ServicoFolha();

        try {
            servico.processarFolha(pagamentos);

        } catch (FalhaProcessamentoFolhaException e) {

            System.out.println("\nFalha final: " + e.getMessage());

            // Exibe a causa original da falha
            e.printStackTrace();
        }
    }

}