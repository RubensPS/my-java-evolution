package desafio_arquivo_bancos;

public class DadosBanco {
    private String codigoBancario;
    private String nomeInstituicao;
    private String isbp;

    public void setCodigoBancario(String codigoBancario) {
        this.codigoBancario = codigoBancario;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public void setIsbp(String isbp) {
        this.isbp = isbp;
    }

    @Override
    public String toString() {
        return "DadosBanco{" +
                "codigoBancario='" + codigoBancario + '\'' +
                ", nomeInstituicao='" + nomeInstituicao + '\'' +
                ", isbp='" + isbp + '\'' +
                '}';
    }
}
