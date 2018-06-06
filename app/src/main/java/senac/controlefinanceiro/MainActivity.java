package senac.controlefinanceiro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Somar(View view) {
        try {
            EditText numero1 = findViewById(R.id.numero1);
            EditText numero2 = findViewById(R.id.numero2);

            int valor1 = Integer.parseInt(numero1.getText().toString());
            int valor2 = Integer.parseInt(numero2.getText().toString());
            int soma = valor1 + valor2;

            numero1.setText("");
            numero2.setText("");

            Toast.makeText(getApplicationContext(),
                    "A soma é " + soma, Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.e("mainActivity", error.getMessage());
        }
    }
}
