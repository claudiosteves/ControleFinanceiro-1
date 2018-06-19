package senac.controlefinanceiro.objects;

import java.util.Date;

public class Receita extends Conta {
    public Receita(int id, Double valor, Date data, String descricao) {
        super(id, valor, data, descricao);
    }

    public Receita(Double valor, Date data, String descricao) {
        super(valor, data, descricao);
    }
}
