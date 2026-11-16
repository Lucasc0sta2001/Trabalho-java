// Exceção para dados inválidos no sistema
public class DadosInvalidosException extends IllegalArgumentException {

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }

}