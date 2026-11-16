// Classe responsável pelas regras de processamento da folha
public class ServicoFolha {

    private HistoricoTransacoes historico;

    public ServicoFolha() {
        this.historico = new HistoricoTransacoes();
    }

    // Processa os pagamentos e trata falhas na folha
    public void processarFolha(Pagavel[] pagamentos)
            throws FalhaProcessamentoFolhaException {

        try {

            historico.processarLote(pagamentos);

        } catch (ExcessoJornadaException e) {

            // Registra a ocorrência para auditoria trabalhista
            System.out.println("\n===== AUDITORIA TRABALHISTA =====");
            System.out.println("Colaborador: " + e.getNomeColaborador());
            System.out.println("Dia: " + e.getDiaRegistro());
            System.out.println("Horas lancadas: " + e.getHorasLancadas());
            System.out.println("Limite permitido: " + e.getLimitePermitido());
            System.out.println("Pendencia registrada na auditoria.");

            // Encapsula a exceção original
            throw new FalhaProcessamentoFolhaException(
                "A folha nao pode ser processada devido a uma jornada irregular.",
                e
            );
        }
    }

}