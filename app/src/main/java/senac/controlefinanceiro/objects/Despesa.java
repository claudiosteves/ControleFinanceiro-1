package senac.controlefinanceiro.objects;

import java.util.Date;

public class Despesa extends Conta {

    public Despesa(Double valor, Date data, String descricao) {
        super(valor, data, descricao);
    }
}
