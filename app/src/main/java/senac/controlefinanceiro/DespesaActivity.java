package senac.controlefinanceiro;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.Inflater;

import senac.controlefinanceiro.entities.ContaDbHelper;
import senac.controlefinanceiro.objects.Despesa;

public class DespesaActivity extends AppCompatActivity {

    EditText valorDespesa;
    EditText dataDespesa;
    EditText descricaoDespesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        valorDespesa = findViewById(R.id.valor_despesa);
        dataDespesa = findViewById(R.id.data_despesa);
        descricaoDespesa = findViewById(R.id.descricao_despesa);
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

                            dataDespesa.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } catch (Exception e){
            Log.e("Despesa", e.getMessage());
        }
    }

    public void salvar(View view) {
        try {
            if (valorDespesa.getText().toString().isEmpty()){
                valorDespesa.setError("Entre com um valor!");

                return;
            }

            Despesa despesa = new Despesa(
                    Double.parseDouble(valorDespesa.getText().toString()),
                    new SimpleDateFormat("dd-MM-yyyy").parse(dataDespesa.getText().toString()),
                    descricaoDespesa.getText().toString()
            );

            ContaDbHelper contaDbHelper = new ContaDbHelper(this);
            contaDbHelper.Salvar(despesa);

            //ListActivity.contas.add(despesa);

            finish();

        } catch (Exception e){
            Log.e("Despesa", e.getMessage());
        }
    }
}
