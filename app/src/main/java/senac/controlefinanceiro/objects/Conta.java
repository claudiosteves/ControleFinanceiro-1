package senac.controlefinanceiro.objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Conta {

    private Double valor;
    private Date data;
    private String descricao;

    public Conta(Double valor, Date data, String descricao) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.data) + " - R$ " + this.valor + " | " + this.descricao;
    }
}
