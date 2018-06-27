package senac.controlefinanceiro;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import senac.controlefinanceiro.entities.ContaContrato;
import senac.controlefinanceiro.entities.ContaDbHelper;
import senac.controlefinanceiro.objects.Conta;
import senac.controlefinanceiro.objects.Despesa;
import senac.controlefinanceiro.objects.Receita;

public class ReceitaActivity extends AppCompatActivity {

    EditText valorReceita;
    EditText dataReceita;
    EditText descricaoReceita;
    FloatingActionButton BtnRemover;
    Receita objReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataReceita = findViewById(R.id.data_receita);
        valorReceita = findViewById(R.id.valor_receita);
        descricaoReceita = findViewById(R.id.descricao_receita);
        BtnRemover = findViewById(R.id.remove);

        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                objReceita = (Receita) getIntent().getExtras().getSerializable("objReceita");
                dataReceita.setText(new SimpleDateFormat("dd-MM-yyyy").format(objReceita.getData()));
                valorReceita.setText(objReceita.getValor().toString());
                descricaoReceita.setText(objReceita.getDescricao());
            } else {
                BtnRemover.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Receita", "Erro OnCreate, " + e.getMessage());
        }
    }

    public void calendario(View view) {
        try {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dataReceita.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Receita", e.getMessage());
        }
    }

    public void salvar(View view) {
        try {
            int id = 0;

            if (objReceita != null){
                id = objReceita.getId();
            }

            objReceita = new Receita(
                    id,
                    Double.parseDouble(valorReceita.getText().toString()),
                    new SimpleDateFormat("dd-MM-yyyy").parse(dataReceita.getText().toString()),
                    descricaoReceita.getText().toString()
            );

            ContaDbHelper contaDbHelper = new ContaDbHelper(this);
            contaDbHelper.Salvar(objReceita);

            Conta.salvar(objReceita);

            //ListActivity.contas.add(despesa);

            finish();

        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Receita", e.getMessage());
        }
    }

    public void remover(View view) {
        try {
            int id = 0;

            if (objReceita != null){
                id = objReceita.getId();
            }

            objReceita = new Receita(
                    id,
                    Double.parseDouble(valorReceita.getText().toString()),
                    new SimpleDateFormat("dd-MM-yyyy").parse(dataReceita.getText().toString()),
                    descricaoReceita.getText().toString()
            );

            final ContaDbHelper contaDbHelper = new ContaDbHelper(this);

            new AlertDialog.Builder(this)
                    .setTitle("Removendo Receita")
                    .setMessage("Tem certeza que deseja remover essa Receita?")
                    .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            contaDbHelper.Remover(objReceita);
                            finish();
                        }
                    })
                    .setNegativeButton("não", null)
                    .show();
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Receita", e.getMessage());
        }
    }
}
