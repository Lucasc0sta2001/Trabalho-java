// Exceção para falhas durante o processamento da folha
public class FalhaProcessamentoFolhaException extends Exception {

    public FalhaProcessamentoFolhaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
