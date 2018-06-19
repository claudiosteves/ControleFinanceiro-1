package senac.controlefinanceiro.objects;

import java.util.Date;

public class Despesa extends Conta {

    public Despesa(int id, Double valor, Date data, String descricao) {
        super(id, valor, data, descricao);
    }

    public Despesa(Double valor, Date data, String descricao) {
        super(valor, data, descricao);
    }
}
