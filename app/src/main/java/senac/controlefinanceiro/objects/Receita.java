package senac.controlefinanceiro.objects;

import java.util.Date;

public class Receita extends Conta {
    public Receita(Double valor, Date data, String descricao) {
        super(valor, data, descricao);
    }
}
