package senac.controlefinanceiro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

import senac.controlefinanceiro.entities.ContaDbHelper;
import senac.controlefinanceiro.objects.Conta;
import senac.controlefinanceiro.objects.Despesa;
import senac.controlefinanceiro.objects.Receita;

public class ListActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;

    private ContaDbHelper contaDbHelper = new ContaDbHelper(this);

    private ListView listaContas;

    public List<Conta> contas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaContas = findViewById(R.id.lista_contas);
        contas = contaDbHelper.Consultar();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, contas);

        listaContas.setAdapter(adapter);

        try {
            rfaLayout = findViewById(R.id.activity_main_rfal);
            rfaBtn = findViewById(R.id.activity_main_rfab);

            RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(getApplicationContext());
            rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
            List<RFACLabelItem> items = new ArrayList<>();
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("Adicionar Despesa")
                    .setResId(R.drawable.ic_despesa)
                    .setIconNormalColor(Color.RED)
                    .setIconPressedColor(Color.GRAY)
                    .setWrapper(0)
            );
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("Adicionar Receita")
                    .setResId(R.mipmap.ic_launcher)
                    .setIconNormalColor(Color.WHITE)
                    .setIconPressedColor(Color.GRAY)
                    .setWrapper(1)
            );

            rfaContent
                    .setItems(items)
                    .setIconShadowColor(0xff888888)
            ;

            rfabHelper = new RapidFloatingActionHelper(
                    getApplicationContext(),
                    rfaLayout,
                    rfaBtn,
                    rfaContent
            ).build();

        } catch (Exception e){
            Log.e("main", "onCreate: " + e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        contas = contaDbHelper.Consultar();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, contas);
        listaContas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    private void chamarAtividades(int position){
        switch (position){
            case 0:
                startActivity(new Intent(this, DespesaActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ReceitaActivity.class));
                break;
        }
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        chamarAtividades(position);
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        chamarAtividades(position);
    }
}
