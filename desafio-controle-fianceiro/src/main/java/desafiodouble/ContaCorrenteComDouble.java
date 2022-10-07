package desafiodouble;

import desafiodouble.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContaCorrenteComDouble {
    private final int numeroAgencia;
    private final int numeroConta;
    private String nomeCliente;
    private LocalDate dataNascimento;
    private Double saldo;
    private boolean contaCancelada;
    private String justificativaCancelamento;
    private List<RegistroOperacaoComDouble> historicoTransacoes;

    public ContaCorrenteComDouble(String nomeCliente, LocalDate dataNascimento, Double saldo) {
        Random r = new Random();
        numeroAgencia = r.nextInt(1000);
        numeroConta = r.nextInt(1000);
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.saldo = saldo;
        this.contaCancelada = false;
        this.justificativaCancelamento = "Conta Ativa";
        this.historicoTransacoes = new ArrayList<>();
    }

    public void sacar(TiposdeOperacao operacao, Double valor) throws ContaCanceladaException, SaldoInsuficienteException {
        if (this.getContaCancelada())
            throw new ContaCanceladaException(this.getNumeroConta(), this.getNomeCliente());
        else if (this.getSaldo()>=valor && valor > 0) {
            this.saldo -= valor;
            this.historicoTransacoes.add(new RegistroOperacaoComDouble(operacao, valor));
        } else throw new SaldoInsuficienteException(this.getNumeroConta());
    }

    public void depositar(TiposdeOperacao operacao, Double valor) throws ContaCanceladaException, DepositoInvalidoException {
            if (this.getContaCancelada())
                throw new ContaCanceladaException(this.getNumeroConta(), this.getNomeCliente());
            else if (valor <= 0) {
                throw new DepositoInvalidoException(valor);
            } else {
                this.saldo += valor;
                this.historicoTransacoes.add(new RegistroOperacaoComDouble(operacao, valor));
            }
    }

    public void transferirValor(Double valor, ContaCorrenteComDouble contaDestino) throws SaldoInsuficienteException, ContaCanceladaException, DepositoInvalidoException {
        if (this.getContaCancelada())
            throw new ContaCanceladaException(this.getNumeroConta(), this.getNomeCliente());
        else if (contaDestino.getContaCancelada())
            throw new ContaCanceladaException(contaDestino.getNumeroConta(), contaDestino.getNomeCliente());
        this.sacar(TiposdeOperacao.TRANSFERENCIA, valor);
        contaDestino.depositar(TiposdeOperacao.TRANSFERENCIA, valor);
    }

    public void cancelarConta(String justificativa) throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        if (justificativa == null || justificativa.isBlank())
            throw new JustificativaInvalidaException();
        else if (this.getContaCancelada())
            throw new ContaPreviamenteCanceladaException(this.getNumeroConta());
        else {
            this.contaCancelada = true;
            this.justificativaCancelamento = justificativa;
        }
    }

    public void reabrirConta(String justificativa) throws JustificativaInvalidaException, ContaAtivaException {
        if (justificativa == null || justificativa.isBlank())
            throw new JustificativaInvalidaException();
        else if (!this.getContaCancelada())
            throw new ContaAtivaException(this.getNumeroConta());
        else {
        this.contaCancelada = false;
        this.justificativaCancelamento = "Conta Ativa";
        }
    }

    public void consultarExtrato(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim ==null)
            throw new NullPointerException("As datas de início e fim não podem ser nulas.");
        else if (inicio.isAfter(fim))
            throw new IllegalArgumentException("A data inicial precisa ser inferior à data final.");
        else {
            System.out.println("agência: " + this.getNumeroAgencia() +
                                "\nconta: " + this.getNumeroConta());
            historicoTransacoes.stream().
                    filter(t -> t.getHoraOperacao().isAfter(inicio) && t.getHoraOperacao().isBefore((fim)))
                    .forEach(System.out::println);
            System.out.println("saldo atual: " + this.getSaldo());
        }
    }


    public Double getSaldo() {
        return saldo;
    }

    public boolean getContaCancelada() {
        return this.contaCancelada;
    }

    public int getNumeroConta() {
        return this.numeroConta;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public String getJustificativaCancelamento(){return this.justificativaCancelamento; }

    public int getNumeroAgencia() {return this.numeroAgencia; }

}
