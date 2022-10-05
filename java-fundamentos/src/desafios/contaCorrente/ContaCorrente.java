package desafios.contaCorrente;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.TreeMap;

public class ContaCorrente {
    private int numeroAgencia;
    private int numeroConta;
    private String nomeCliente;
    private LocalDate dataNascimento;
    private BigDecimal saldo;
    private boolean contaCancelada;
    private TreeMap<LocalDateTime, String> historicoTransacoes;

    public ContaCorrente(String nomeCliente, LocalDate dataNascimento, BigDecimal saldo) {
        Random r = new Random();
        numeroAgencia = r.nextInt(1000);
        numeroConta = r.nextInt(1000);
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.saldo = saldo;
        this.contaCancelada = false;
        this.historicoTransacoes = new TreeMap<>();
    }

    public void sacar(BigDecimal valor) {
        if (this.getStatus())
            System.out.printf("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.\n", this.getNumeroConta(), this.getNomeCliente());
        else if (saldo.compareTo(valor) != -1 && valor.signum() != -1) {
            saldo = saldo.subtract(valor);
            String saque = new String("saque: " + valor.setScale(2, RoundingMode.HALF_EVEN));
            historicoTransacoes.put(LocalDateTime.now(), saque);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Operação de saque interrompida.");
            }
        } else System.out.println("Saldo insuficiente para saque!");
    }

    public void depositar(BigDecimal valor) {
        if (this.getStatus())
            System.out.printf("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.\n", this.getNumeroConta(), this.getNomeCliente());
        else if (valor.signum() <= 0) {
                System.out.println("O valor do depósito deve ser superior a zero!");
        } else {
            this.saldo = saldo.add(valor);
            String deposito = new String("deposito: " + valor.setScale(2, RoundingMode.HALF_EVEN));
            historicoTransacoes.put(LocalDateTime.now(), deposito);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Operação de saque interrompida.");
            }
        }
    }

    public void transferirValor(BigDecimal valor, ContaCorrente contaDestino) {
        if (this.getStatus())
            System.out.printf("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.\n", this.getNumeroConta(), this.getNomeCliente());
        else if (saldo.compareTo(valor) != -1 && valor.signum() != -1 && !contaDestino.getStatus()) {
            this.sacar(valor);
            contaDestino.depositar(valor);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Operação de saque interrompida.");
            }
        } else System.out.println("Saldo insuficiente para transferência, valor não aceito ou contaDestino cancelada.");
    }

    public void cancelarConta(String justificativa) {
        if (justificativa == null || justificativa.isBlank()) {
            System.out.println("Uma justificativa válida deve ser informada para cancelamento.");
        } else if (!this.getStatus()) {
            this.contaCancelada = true;
        } else System.out.println("Conta cancelada previamente!");
    }

    public void reabrirConta(String justificativa) {
        if (justificativa == null || justificativa.isBlank()) {
            System.out.println("Uma justificativa válida deve ser informada para reabertura.");
        } else if (this.getStatus()) {
            this.contaCancelada = false;
        } else System.out.println("A conta já se encontra ativa!");
    }

    public void pegarExtrato(LocalDateTime inicio,LocalDateTime fim) {
        try {
            System.out.println(historicoTransacoes.subMap(inicio, fim));
        } catch (NullPointerException | IllegalArgumentException e) {
            if ((e instanceof NullPointerException)) {
                System.out.println("Valores de data não podem ser nulos.");
            } else {
                System.out.println("A data inicial deve ser anterior a data final.");
            }
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
}
