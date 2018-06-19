package senac.controlefinanceiro.objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import senac.controlefinanceiro.entities.ContaContrato;

public abstract class Conta implements Serializable {

    private int id;
    private Double valor;
    private Date data;
    private String descricao;

    public Conta(int id, Double valor, Date data, String descricao) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public Conta(Double valor, Date data, String descricao) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public int getId() { return id; }

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
