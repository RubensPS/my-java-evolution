package desafioAguaLuz.entities;

import desafioAguaLuz.enums.TipoNotificacao;
import desafioAguaLuz.enums.TipoServico;

import java.time.LocalDateTime;

public class ContratoInstalacao {
    private Integer protocolo;
    private LocalDateTime agendamento;
    private TipoServico tipoServico;
    private Double valorServico;
    private TipoNotificacao tipoNotificacao;
    private Contratante contratante;

    public ContratoInstalacao() {
    }

    public ContratoInstalacao(Integer protocolo, TipoServico tipoServico, TipoNotificacao tipoNotificacao,
                              Contratante contratante) {
        this.protocolo = protocolo;
        this.agendamento = LocalDateTime.now();
        this.tipoServico = tipoServico;
        this.valorServico = tipoServico.getValor();
        this.tipoNotificacao = tipoNotificacao;
        this.contratante = contratante;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public LocalDateTime getAgendamento() {
        return agendamento;
    }

    public void setValor(Double valor) {
        this.valorServico = valor;
    }

    public Double getValorServico() {
        return valorServico;
    }
}
