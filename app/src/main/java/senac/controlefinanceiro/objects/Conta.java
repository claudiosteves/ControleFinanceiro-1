package senac.controlefinanceiro.objects;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import senac.controlefinanceiro.entities.ContaContrato;

public class Conta implements Serializable {

    private String doc;
    private int id;
    private Double valor;
    private Date data;
    private String descricao;

    public Conta(){

    }

    public Conta(int id, Double valor, Date data, String descricao) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public Conta(Double valor, Date data, String descricao) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public int getId() { return id; }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.data) + " - R$ " + this.valor + " | " + this.descricao;
    }

    public static boolean salvar(Conta conta){
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("conta");

            if (conta.doc == null){
                conta.doc = UUID.randomUUID().toString();
            }

            ref.child(conta.doc).setValue(conta);

            return true;
        } catch (Exception e){
            Log.e("Conta", "Salvar: " + e.getMessage());
            throw e;
        }
    }

}
