package senac.controlefinanceiro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import senac.controlefinanceiro.entities.ContaDbHelper;
import senac.controlefinanceiro.objects.Conta;
import senac.controlefinanceiro.objects.Despesa;
import senac.controlefinanceiro.objects.ListaPersonalizadaAdapter;
import senac.controlefinanceiro.objects.Receita;

public class ListActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;
    private SearchView buscador;

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

        listaContas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Conta objConta = contas.get(position);

                    if (objConta instanceof Receita){
                        Intent telaReceita = new Intent(getBaseContext(), ReceitaActivity.class);
                        telaReceita.putExtra("objReceita", objConta);
                        startActivity(telaReceita);
                    } else {
                        Intent telaDespesa = new Intent(getBaseContext(), DespesaActivity.class);
                        telaDespesa.putExtra("objDespesa", objConta);
                        startActivity(telaDespesa);
                    }

                } catch (Exception e){
                    Toast.makeText(getBaseContext(), "Ocorreu um erro...", Toast.LENGTH_LONG).show();
                    Log.e("Click ListViewItem", e.getMessage());
                }
            }
        });

        try {

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String ordem_list = sharedPref.getString("ordem_list", "0");

            //contas = contaDbHelper.Consultar(ordem_list);

            //ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, contas);

            //ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(contas, this);

            //listaContas.setAdapter(adapter);


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
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "onCreate: " + e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String ordem_list = sharedPref.getString("ordem_list", "0");

            contas = contaDbHelper.Consultar(ordem_list);
            //ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, contas);

            final ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(contas, this);

            listaContas.setAdapter(adapter);

//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference ref = database.getReference("conta");
//
//            ref.addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    contas.add(dataSnapshot.getValue(Conta.class));
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//                    contas.remove(dataSnapshot.getValue(Conta.class));
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

        }
        catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "onResumo: " + e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem config = menu.findItem(R.id.menu_config);
        config.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                try {
                    startActivity(new Intent(ListActivity.this, ConfigActivity.class));
                    return true;
                } catch (Exception e){
                    Toast.makeText(getBaseContext(), "Ocorreu um erro...", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "onClose: " + e.getMessage());
                    return false;
                }
            }
        });

        buscador = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        buscador.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                try {
                    ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(contas, ListActivity.this);
                    listaContas.setAdapter(adapter);

                    return false;
                } catch (Exception e){
                    Toast.makeText(getBaseContext(), "Ocorreu um erro...", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "onClose: " + e.getMessage());
                    return false;
                }
            }
        });

        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    List<Conta> filtro = new ArrayList<>();

                    for (Conta conta : contas) {
                        if (conta.getDescricao().toLowerCase().contains(query.toLowerCase())){
                            filtro.add(conta);
                        }
                    }

                    if (filtro.isEmpty()){
                        ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(contas, ListActivity.this);
                        listaContas.setAdapter(adapter);
                    } else {
                        ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(filtro, ListActivity.this);
                        listaContas.setAdapter(adapter);
                    }

                    return true;
                } catch (Exception e){
                    Toast.makeText(getBaseContext(), "Ocorreu um erro...", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "onQueryTextSubmit: " + e.getMessage());
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    List<Conta> filtro = new ArrayList<>();

                    for (Conta conta : contas) {
                        if (conta.getDescricao().toLowerCase().contains(newText.toLowerCase())){
                            filtro.add(conta);
                        }
                    }

                    if (filtro.isEmpty()){
                        ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(contas, ListActivity.this);
                        listaContas.setAdapter(adapter);
                    } else {
                        ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(filtro, ListActivity.this);
                        listaContas.setAdapter(adapter);
                    }

                    return true;
                } catch (Exception e){
                    Toast.makeText(getBaseContext(), "Ocorreu um erro...", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "onQueryTextSubmit: " + e.getMessage());
                    return false;
                }
            }
        });

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
