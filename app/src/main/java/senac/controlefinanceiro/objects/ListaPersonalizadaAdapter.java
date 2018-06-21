package senac.controlefinanceiro.objects;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import senac.controlefinanceiro.R;

public class ListaPersonalizadaAdapter extends BaseAdapter {

    private final List<Conta> contas;
    private final Activity act;

    public ListaPersonalizadaAdapter(List<Conta> contas, Activity act) {
        this.contas = contas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return contas.size();
    }

    @Override
    public Object getItem(int position) {
        return contas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.lista_personalizada, parent, false);

        Conta conta = contas.get(position);

        TextView valor = (TextView)
                view.findViewById(R.id.txtValor);
        TextView descricao = (TextView)
                view.findViewById(R.id.txtDescricao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.icone);

        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        valor.setText("R$ " + nf.format(conta.getValor()));
        descricao.setText(conta.getDescricao());

        if (conta instanceof Receita){
            imagem.setImageResource(R.drawable.ic_launcher_foreground);
            valor.setTextColor(Color.BLUE);
        } else {
            valor.setTextColor(Color.RED);
        }

        return view;
    }
}
