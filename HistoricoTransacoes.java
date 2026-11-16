import java.util.ArrayDeque;
import java.util.Deque;

// Classe responsável pelo histórico e estorno das transações
public class HistoricoTransacoes {

    // Pilha que armazena os pagamentos realizados
    private Deque<Pagavel> historico = new ArrayDeque<>();

    private int operacoesConcluidas = 0;
    private int operacoesEstornadas = 0;

    // Processa um lote de pagamentos
    public void processarLote(Pagavel[] pagamentos)
            throws ExcessoJornadaException {

        try {

            // Percorre os pagamentos
            for (Pagavel pagamento : pagamentos) {

                // Verifica a jornada dos funcionários horistas
                if (pagamento instanceof Horista) {
                    Horista horista = (Horista) pagamento;
                    horista.verificarHorasExtras();
                }

                // Calcula o valor do pagamento
                double valor = pagamento.calcularPagamento();

                // Guarda o pagamento realizado na pilha
                historico.push(pagamento);
                operacoesConcluidas++;

                System.out.printf(
                    "Pagamento realizado: %s | R$ %.2f%n",
                    pagamento.getDescricao(), valor
                );
            }

        } catch (ExcessoJornadaException e) {

            System.out.println("\nFalha no processamento: " + e.getMessage());

            // Executa o estorno dos pagamentos anteriores
            executarRollback();

            // Propaga a exceção para o serviço
            throw e;

        } catch (RuntimeException e) {

            System.out.println("\nFalha no processamento: " + e.getMessage());

            // Executa o estorno dos pagamentos anteriores
            executarRollback();

            throw e;

        } finally {

            // Fecha o ciclo de auditoria
            System.out.println("\n===== RESUMO DA AUDITORIA =====");
            System.out.println("Operacoes concluidas: " + operacoesConcluidas);
            System.out.println("Operacoes estornadas: " + operacoesEstornadas);
            System.out.println("Ciclo de auditoria finalizado.");

        }
    }

    // Desfaz os pagamentos na ordem inversa
    private void executarRollback() {

        System.out.println("\n===== INICIANDO ROLLBACK =====");

        while (!historico.isEmpty()) {

            // Retira o último pagamento da pilha
            Pagavel pagamento = historico.pop();

            // Executa o estorno
            pagamento.estornar();

            operacoesEstornadas++;
        }

        System.out.println("Rollback finalizado.");
    }

}