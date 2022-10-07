package desafiobigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContaCorrente {
    private int numeroAgencia;
    private int numeroConta;
    private String nomeCliente;
    private LocalDate dataNascimento;
    private BigDecimal saldo;
    private boolean contaCancelada;
    private String justificativaCancelamento;
    private List<DadosOperacao> historicoTransacoes;

    public ContaCorrente(String nomeCliente, LocalDate dataNascimento, BigDecimal saldo) {
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

    public void sacar(BigDecimal valor) {
        if (this.getStatus())
            System.out.printf("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.%n", this.getNumeroConta(), this.getNomeCliente());
        else if (saldo.compareTo(valor) != -1 && valor.signum() != -1) {
            this.saldo = saldo.subtract(valor);
            this.historicoTransacoes.add(new DadosOperacao("saque", valor.setScale(2, RoundingMode.HALF_EVEN)));
        } else System.out.println("Saldo insuficiente para saque!");
    }

    public void depositar(BigDecimal valor) {
        try {
            if (this.getStatus())
                System.out.printf("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.%n", this.getNumeroConta(), this.getNomeCliente());
            else if (valor.signum() <= 0) {
                System.out.println("O valor do depósito deve ser superior a zero!");
            } else {
                this.saldo = saldo.add(valor);
                this.historicoTransacoes.add(new DadosOperacao("depósito", valor.setScale(2, RoundingMode.HALF_EVEN)));
            }
        } catch (NullPointerException e) {
            System.out.println("Valor do depósito não pode ser nulo.");
        }

    }

    public void transferirValor(BigDecimal valor, ContaCorrente contaDestino) {
        if (this.getStatus())
            System.out.printf("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.%n", this.getNumeroConta(), this.getNomeCliente());
        else if (saldo.compareTo(valor) != -1 && valor.signum() != -1 && !contaDestino.getStatus()) {
            this.sacar(valor);
            contaDestino.depositar(valor);
        } else System.out.println("Saldo insuficiente para transferência, valor não aceito ou contaDestino cancelada.");
    }

    public void cancelarConta(String justificativa) {
        if (justificativa == null || justificativa.isBlank()) {
            System.out.println("Uma justificativa válida deve ser informada para cancelamento.");
        } else if (!this.getStatus()) {
            this.contaCancelada = true;
            this.justificativaCancelamento = justificativa;
        } else System.out.println("Conta cancelada previamente!");
    }

    public void reabrirConta(String justificativa) {
        if (justificativa == null || justificativa.isBlank()) {
            System.out.println("Uma justificativa válida deve ser informada para reabertura.");
        } else if (this.getStatus()) {
            this.contaCancelada = false;
            this.justificativaCancelamento = "Conta Ativa";
        } else System.out.println("A conta já se encontra ativa!");
    }

    public void consultarExtrato(LocalDateTime inicio, LocalDateTime fim) {
        try {
            System.out.println("agência: " + this.getNumeroAgencia() + "\nconta: " + this.getNumeroConta());
            historicoTransacoes.stream().
                    filter(t -> t.getHoraOperacao().isAfter(inicio) && t.getHoraOperacao().isBefore(fim))
                    .forEach(System.out::println);
            System.out.println("saldo atual: " + this.getSaldo());
        } catch (NullPointerException e) {
            System.out.println("Valores de data não podem ser nulos.");
        }
    }

    public BigDecimal getSaldo() {
        return saldo.setScale(2, RoundingMode.HALF_EVEN);
    }

    public boolean getStatus() {
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
