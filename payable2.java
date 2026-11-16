import java.util.Stack;//importação biblioteca para pilha

interface Payable { //interface principal
    void pay(); 
    float obterValorEstorno();
    String obterNomeItem();//métodos da interface
}

interface Invoice extends Payable { void invoice(); }//interface filha
interface Employee extends Payable { void employee(); }//interface filha
interface SalariedEmployee extends Employee { void salariedEmployee(); }//interface filha   

class LimiteHoraExtraExcedidoException extends Exception {//checked exception personalizada
    public LimiteHoraExtraExcedidoException(String mensagem) { super(mensagem); }//construtor da exceção para verificação de limite de horas extras
}

class ExcessoJornadaException extends Exception {//checked exception personalizada  
    public ExcessoJornadaException(String mensagem) { super(mensagem); }//construtor da exceção para verificação de excesso de jornada
}

class DadosInvalidosException extends IllegalArgumentException {//unchecked exception personalizada
    public DadosInvalidosException(String mensagem) { super(mensagem); }//construtor da exceção para verificação de dados inválidos
}

class FalhaProcessamentoFolhaException extends Exception {//checked exception personalizada
    public FalhaProcessamentoFolhaException(String message, Throwable causa) { //construtor da exceção para verificação de falha no processamento da folha
        super(message, causa); // Chaining: permite encadear exceções, mantendo a causa original.referencia à exceção original que causou a falha, permitindo rastrear a origem do problema.
    }
}

class TransacaoEfetuada {//classe para armazenar informações de transações bem-sucedidas
    Payable item;
    float valor;//atributos da classe

    public TransacaoEfetuada(Payable item, float valor) {//construtor da classe
        this.item = item;
        this.valor = valor;//atributos da classe instanciados com os valores passados como parâmetros
    }
}

class Fatura implements Invoice {//classe que implementa a interface Invoice
    String numeroFatura;
    String descricaoItem;
    int quantidade;
    float precoUnitario;//atributos da classe

    public Fatura(String numeroFatura, String descricaoItem, int quantidade, float precoUnitario) {//construtor da classe com parâmetros
        if (quantidade <= 0) {//laço que verifica se a quantidade é menor ou igual a zero, caso seja, lança uma exceção
            throw new DadosInvalidosException("Erro Invoice: Quantidade (" + quantidade + ") inválida.");
        }
        if (precoUnitario <= 0) {//laço que verifica se o preço unitário é menor ou igual a zero, caso seja, lança uma exceção
            throw new DadosInvalidosException("Erro Invoice: Preço unitário inválido.");
        }
        this.numeroFatura = numeroFatura;
        this.descricaoItem = descricaoItem;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;//instanciação dos atributos da classe com os valores passados como parâmetros
    }

    @Override public void pay() { System.out.println("   [AÇÃO] Fatura liquidada: " + descricaoItem); }//implementação do método pay da interface Payable
    @Override public void invoice() { System.out.println("Invoice consultada."); }//implementação do método invoice da interface Invoice
    @Override public float obterValorEstorno() { return quantidade * precoUnitario; }//implementação do método obterValorEstorno da interface Payable
    @Override public String obterNomeItem() { return "Fatura (" + descricaoItem + ")"; }//implementação do método obterNomeItem da interface Payable
}//subscrita da interface Payable, implementando os métodos obrigatórios da interface

class Empresa {//classe que representa a empresa
    int totalFuncionarios;
    float folhaTotal;
    float saldoDisponivelEmConta = 10000.00f; // Saldo inicial confortável para os testes
}//atributos da classe

class Funcionario extends Empresa implements Employee {//classe que representa o funcionário, herda da classe Empresa e implementa a interface Employee
    String nome;
    String cargo;
    int idade;
    float ultimoGanhoCalculado = 0f;//ganho calculado inicialmente zero, será atualizado após o cálculo do ganho do funcionário
    //atributos da classe

    public Funcionario(String nome, String cargo, int idade) {//construtor da classe com parâmetros
        super();//chamada ao construtor da classe pai (Empresa)
        if (nome == null || nome.trim().isEmpty()) {//verifica se uma String é nula, se está vazia ou se contém apenas espaços em branco
            throw new DadosInvalidosException("O nome do funcionário não pode ser vazio.");
        }
        if (idade < 18 || idade > 120) {//verifica se a idade do funcionário é menor que 18 ou maior que 120, caso seja, lança uma exceção
            throw new DadosInvalidosException("Idade inválida.");
        }
        this.nome = nome;
        this.cargo = cargo;
        this.idade = idade;
//instanciação dos atributos da classe com os valores passados como parâmetros
    }

    public float calcularGanho() throws LimiteHoraExtraExcedidoException, ExcessoJornadaException { return 0.0f; }//método que calcula o ganho do funcionário, pode lançar exceções de limite de horas extras e excesso de jornada
    @Override public void pay() { System.out.println("   [AÇÃO] Pagamento efetuado para funcionário: " + nome); }//implementação do método pay da interface Payable
    @Override public void employee() { System.out.println("Registro de funcionário acessado."); }//implementação do método employee da interface Employee
    @Override public float obterValorEstorno() { return this.ultimoGanhoCalculado; }//implementação do método obterValorEstorno da interface Payable, retorna o último ganho calculado do funcionário
    @Override public String obterNomeItem() { return "Funcionário (" + nome + ")"; }//implementação do método obterNomeItem da interface Payable, retorna o nome do funcionário
}

class FuncionarioAssalariado extends Funcionario implements SalariedEmployee {//classe que representa o funcionário assalariado, herda da classe Funcionario e implementa a interface SalariedEmployee
    float salarioFixo;//atributo da classe
    public FuncionarioAssalariado(String nome, String cargo, int idade, float salarioFixo) {//construtor da classe com parâmetros
        super(nome, cargo, idade);//chamada ao construtor da classe pai (Funcionario)
        this.salarioFixo = salarioFixo;//instanciação do atributo da classe com o valor passado como parâmetro  
    }
    @Override public float calcularGanho() { //método que calcula o ganho do funcionário assalariado
        this.ultimoGanhoCalculado = this.salarioFixo;//atualiza o último ganho calculado com o salário fixo do funcionário
        return this.salarioFixo; //retorna o salário fixo do funcionário
    }
    @Override public void salariedEmployee() { System.out.println("Funcionário assalariado verificado."); }//implementação do método salariedEmployee da interface SalariedEmployee
}

class FuncionarioHorista extends Funcionario {//classe que representa o funcionário horista, herda da classe Funcionario
    float horasTrabalhadas;
    float valorHora;
    float horasDiarias; 
    //atributos da classe

    public FuncionarioHorista(String nome, String cargo, int idade, float horasTrabalhadas, float valorHora, float horasDiarias) {//construtor da classe com parâmetros
        super(nome, cargo, idade);//chamada ao construtor da classe pai (Funcionario)
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
        this.horasDiarias = horasDiarias;//instanciação dos atributos da classe com os valores passados como parâmetros
    }

    @Override //sobrescrita do método calcularGanho da classe Funcionario
    public float calcularGanho() throws LimiteHoraExtraExcedidoException, ExcessoJornadaException {//método que calcula o ganho do funcionário horista, pode lançar exceções de limite de horas extras e excesso de jornada
        if (horasDiarias > 10) {
            throw new ExcessoJornadaException("CRIME TRABALHISTA: Carga diária abusiva detectada para " + nome + " (" + horasDiarias + "h/dia). Limite é 10h.");
        }
        if (horasTrabalhadas > 50) {
            throw new LimiteHoraExtraExcedidoException("ALERTA LEGAL: O funcionário " + nome + " excedeu o limite máximo de horas extras semanais!");
        }
        
        float ganho = horasTrabalhadas * valorHora;//calcula o ganho do funcionário horista multiplicando as horas trabalhadas pelo valor da hora
        this.ultimoGanhoCalculado = ganho;//atualiza o último ganho calculado com o ganho do funcionário horista
        return ganho;//retorna o ganho do funcionário horista
    }
}

class HistoricoTransacoes {//classe que representa o histórico de transações
    private Stack<TransacaoEfetuada> pilhaTransacoes = new Stack<>();//pilha para armazenar as transações efetuadas, seguindo a ordem LIFO (Last In, First Out)
    private int operacoesConcluidas = 0;//contador de operações concluídas ,privado por segurança , inicializado em zero
    private int operacoesEstornadas = 0;//contador de operações estornadas, privado por segurança, inicializado em zero

    public void registrarSucesso(Payable item, float valor) {//método para registrar uma transação bem-sucedida, recebe o item e o valor da transação como parâmetros
        pilhaTransacoes.push(new TransacaoEfetuada(item, valor));//adiciona a transação à pilha de transações
        operacoesConcluidas++;//incrementa o contador de operações concluídas
    }

    public void executarRollback(Empresa contaEmpresa) {//método para executar o rollback das transações, recebe a conta da empresa como parâmetro
        System.out.println("\n\t [ROLLBACK CRÍTICO ACTUADO - ORDEM REVERSA LIFO] ");
        while (!pilhaTransacoes.isEmpty()) {//enquanto a pilha de transações não estiver vazia, executa o rollback
            TransacaoEfetuada estorno = pilhaTransacoes.pop();//remove a transação do topo da pilha(.POP) e armazena na variável estorno
            contaEmpresa.saldoDisponivelEmConta += estorno.valor;//adiciona o valor da transação estornada ao saldo disponível da conta da empresa
            operacoesEstornadas++;//incrementa o contador de operações estornadas
            operacoesConcluidas--;//decrementa o contador de operações concluídas
            System.out.printf("\t>> [ESTORNO REVERSO] R$ %.2f devolvidos. Item cancelado: %s\n", 
                    estorno.valor, estorno.item.obterNomeItem());//exibe o valor estornado e o nome do item cancelado. (O .obterNomeItem() é um método da interface Payable que retorna o nome do item)
        }
    }

    public void fecharCicloAuditoria() {//método para fechar o ciclo de auditoria, exibe um relatório final das transações
        System.out.println("\t------------------------------------------------");
        System.out.println("\t [FINALLY] RELATÓRIO DO CICLO DE AUDITORIA");
        System.out.printf("\t>> Transações Confirmadas (Finalizadas): %d\n", operacoesConcluidas);
        System.out.printf("\t>> Transações Desfeitas / Estornadas: %d\n", operacoesEstornadas);
        System.out.println("\t>> Buffer e streams de logs consolidados.");
        System.out.println("\t------------------------------------------------");
    }
}

class PayrollService { //serviço de processamento de folha de pagamento
    public static void processarLote(Payable[] lote, Empresa contaEmpresa, HistoricoTransacoes transacoes) //método para processar um lote de pagamentos, recebe o lote, a conta da empresa e o histórico de transações como parâmetros(O payable[] lote é um array de objetos que implementam a interface Payable, permitindo que tanto faturas quanto funcionários sejam processados de forma polimórfica.)
            throws FalhaProcessamentoFolhaException {//declara que o método pode lançar a exceção FalhaProcessamentoFolhaException, que é uma exceção verificada (checked exception) personalizada para indicar falhas no processamento da folha de pagamento.
        
        for (Payable item : lote) {//laço for-each que percorre cada item do lote de pagamentos, onde cada item é do tipo Payable (polimorfismo)
            try {//bloco try para capturar exceções durante o processamento de cada item do lote
                float valorALiquidar = 0f;
                
                if (item instanceof Funcionario f) {//verifica se o item é uma instância de Funcionario, se sim, faz o cast para Funcionario
                    valorALiquidar = f.calcularGanho(); 
                } else if (item instanceof Fatura fat) {//verifica se o item é uma instância de Fatura, se sim, faz o cast para Fatura
                    valorALiquidar = fat.obterValorEstorno();
                }

                if (contaEmpresa.saldoDisponivelEmConta < valorALiquidar) {//verifica se o saldo disponível na conta da empresa é insuficiente para liquidar o valor do item, se sim, lança a exceção FalhaProcessamentoFolhaException com uma mensagem detalhada
                    throw new FalhaProcessamentoFolhaException("Erro Financeiro: Saldo em conta insuficiente para " + item.obterNomeItem(), null);
                }

                contaEmpresa.saldoDisponivelEmConta -= valorALiquidar;//deduz o valor a liquidar do saldo disponível na conta da empresa
                transacoes.registrarSucesso(item, valorALiquidar);//registra a transação bem-sucedida no histórico de transações
                System.out.printf("[APROVADO] %s processado com sucesso. Valor: R$ %.2f\n", item.obterNomeItem(), valorALiquidar);
                item.pay();

            } catch (ExcessoJornadaException e) {//captura a exceção de excesso de jornada, que é uma checked exception personalizada
                System.out.println("\n[REGISTRO AUDITORIA] Apontamento gerado: Infrações de horas arquivadas para sanção legal.");
                // Relança convertendo para a Checked de serviço com a causa aninhada (Exception Chaining)
                throw new FalhaProcessamentoFolhaException("Processamento cancelado por inconformidade com a CLT.", e);//lança a exceção FalhaProcessamentoFolhaException com uma mensagem detalhada e a causa original da exceção (e) para manter o rastreamento da origem do problema
                
            } catch (LimiteHoraExtraExcedidoException e) {//captura a exceção de limite de horas extras excedido, que é uma checked exception personalizada
                System.out.println("[AUDITORIA LOCAL] Ajuste automático de horas extras feito para: " + item.obterNomeItem());
            } //finally não é necessário aqui, pois o rollback será tratado no nível superior (main) em caso de falha no processamento do lote
        }
    }
}

public class payable2 {//classe principal que contém o método main para executar os testes
    public static void main(String[] args) {//método main que executa os testes. arrays de objetos que implementam a interface Payable, permitindo que tanto faturas quanto funcionários sejam processados de forma polimórfica.
        Empresa bancoEmpresa = new Empresa();//instanciação da classe Empresa, representando a empresa que possui a conta bancária para processar os pagamentos. insere nova instância da classe Empresa, representando a empresa que possui a conta bancária para processar os pagamentos.


        System.out.println("=================================================================");
        System.out.println(" TESTE 1: CAMINHO FELIZ (TUDO VÁLIDO)");
        System.out.println("=================================================================");
        bancoEmpresa.saldoDisponivelEmConta = 10000.00f; // Reset do saldo
        HistoricoTransacoes auditoriaFeliz = new HistoricoTransacoes();//teste de auditoria para o cenário 1, instanciando a classe HistoricoTransacoes para registrar as transações bem-sucedidas e permitir rollback em caso de falha

        Payable[] loteFeliz = new Payable[] {//array de objetos que implementam a interface Payable, representando um lote de pagamentos a serem processados, incluindo faturas e funcionários
            new Fatura("INV-101", "Licenças JetBrains", 5, 200.00f), // R$ 1000
new FuncionarioAssalariado("Carlos Silva", "Gerente", 45, 1200.00f),
new FuncionarioHorista("Ana Souza", "Designer", 28, 40f, 20.00f, 8f) // Dentro da lei: 8h/dia
};//teste de auditoria para o cenário 1, instanciando a classe HistoricoTransacoes para registrar as transações bem-sucedidas e permitir rollback em caso de falha
try {//bloco try para capturar exceções durante o processamento do lote de pagamentos
PayrollService.processarLote(loteFeliz, bancoEmpresa, auditoriaFeliz);//chamada ao método processarLote da classe PayrollService, passando o lote de pagamentos, a conta da empresa e o histórico de transações como parâmetros
System.out.println("\n>>> FIM DO CENÁRIO 1: Lote liquidado integralmente sem erros.");
} catch (FalhaProcessamentoFolhaException e) {//captura a exceção FalhaProcessamentoFolhaException lançada pelo método processarLote, caso ocorra algum erro durante o processamento do lote de pagamentos
auditoriaFeliz.executarRollback(bancoEmpresa);//executa o rollback das transações bem-sucedidas registradas no histórico de transações, caso ocorra algum erro durante o processamento do lote de pagamentos
} finally {//bloco finally que será executado independentemente de ocorrer ou não uma exceção, garantindo que o ciclo de auditoria seja fechado
auditoriaFeliz.fecharCicloAuditoria();

System.out.println("\n=================================================================");
System.out.println(" TESTE 2: VIOLAÇÃO TRABALHISTA (HourlyEmployee com 12h/dia)");
System.out.println("=================================================================");
bancoEmpresa.saldoDisponivelEmConta = 10000.00f; // Reset do saldo
//teste de auditoria para o cenário 2, instanciando a classe HistoricoTransacoes para registrar as transações bem-sucedidas e permitir rollback em caso de falha
HistoricoTransacoes auditoriaTrabalhista = new HistoricoTransacoes();//array de objetos que implementam a interface Payable, representando um lote de pagamentos a serem processados, incluindo faturas e funcionários
Payable[] loteInfracao = new Payable[] {//array de objetos que implementam a interface Payable, representando um lote de pagamentos a serem processados, incluindo faturas e funcionários
new FuncionarioAssalariado("Carlos Silva", "Gerente", 45, 1200.00f),
new FuncionarioHorista("Ana Souza", "Designer", 28, 40f, 20.00f, 12f) // DISPARA EXCESSOJORNADAEXCEPTION (12h)
};//teste de auditoria para o cenário 2, instanciando a classe HistoricoTransacoes para registrar as transações bem-sucedidas e permitir rollback em caso de falha
try {//bloco try para capturar exceções durante o processamento do lote de pagamentos
PayrollService.processarLote(loteInfracao, bancoEmpresa, auditoriaTrabalhista);
} catch (FalhaProcessamentoFolhaException e) {//captura a exceção FalhaProcessamentoFolhaException lançada pelo método processarLote, caso ocorra algum erro durante o processamento do lote de pagamentos
System.out.println("\n[VISÃO - CAMADA 3] Captura de Falha convertida pelo serviço:");
System.out.println("Mensagem Superior: " + e.getMessage());
System.out.println("Causa Original (Análise de Causa Raiz): " + e.getCause());
auditoriaTrabalhista.executarRollback(bancoEmpresa);
System.out.println("\n=== EXIBINDO A PILHA DE CHAMADAS (STACK TRACE) ===");
e.printStackTrace(); // Exibe a pilha completa de propagação
//permite rastrear a origem de uma exceção em uma pilha de chamadas, mostrando a sequência de métodos que foram chamados até o ponto em que a exceção foi lançada.
} finally {//bloco finally que será executado independentemente de ocorrer ou não uma exceção, garantindo que o ciclo de auditoria seja fechado
auditoriaTrabalhista.fecharCicloAuditoria();
}
//rollback do teste 2
System.out.println("\n=================================================================");
System.out.println(" TESTE 3: FALHA NO 4º ITEM DO LOTE (PROCESSAMENTO DE 5 ITENS)");
System.out.println("=================================================================");
// Forçamos o caixa a ficar baixo (R$ 3.000,00) para que o 4º item estoure a conta de propósito
bancoEmpresa.saldoDisponivelEmConta = 3000.00f;
HistoricoTransacoes auditoriaRollback = new HistoricoTransacoes();
Payable[] loteCincoItens = new Payable[] {//processamento de 5 itens, sendo que o 4º item vai falhar por insuficiência de saldo(polimorfismo)
new FuncionarioAssalariado("Carlos Silva", "Gerente", 45, 1000.00f),       // 1º: OK (Pilha recebe)
new Fatura("INV-501", "Mouse e Teclados", 2, 250.00f),                     // 2º: OK (R$ 500)
new FuncionarioHorista("Ana Souza", "Designer", 28, 40f, 20.00f, 8f),      // 3º: OK (R$ 800)
new FuncionarioAssalariado("Roberto Costa", "Vendedor", 34, 2500.00f),     // 4º: VAI FALHAR! Exige R$ 2500, mas o caixa só tem R$ 700 restantes.
new Fatura("INV-502", "Cabos HDMI", 10, 15.00f)                            // 5º: Nem chega a processar
};
try {
System.out.printf("Saldo inicial alocado para este teste: R$ %.2f\n\n", bancoEmpresa.saldoDisponivelEmConta);
PayrollService.processarLote(loteCincoItens, bancoEmpresa, auditoriaRollback);
} catch (FalhaProcessamentoFolhaException e) {
System.out.println("\n[VISÃO - CAMADA 3] Captura da Falha no lote de pagamentos.");
System.out.println("Motivo do travamento: " + e.getMessage());
// Ativação da rotina de reversão LIFO
auditoriaRollback.executarRollback(bancoEmpresa);
} finally {
// Execução obrigatória do bloco finally de fechamento
auditoriaRollback.fecharCicloAuditoria();
System.out.printf("Estado líquido de segurança em conta: R$ %.2f\n", bancoEmpresa.saldoDisponivelEmConta);
System.out.println("=================================================================");
}
}
}
}
class payableInsuficiente extends Exception {
public payableInsuficiente(String mensagem){ super(mensagem);   
}
}

