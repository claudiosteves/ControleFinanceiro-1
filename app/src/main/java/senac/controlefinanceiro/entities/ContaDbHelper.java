package senac.controlefinanceiro.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import senac.controlefinanceiro.objects.Conta;
import senac.controlefinanceiro.objects.Despesa;
import senac.controlefinanceiro.objects.Receita;

public class ContaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "financeiro.db";

    public ContaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContaContrato.SQL_CREATE_CONTA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContaContrato.SQL_DELETE_CONTA);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean Salvar(Conta conta){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContaContrato.TabelaConta.COLUMN_NAME_VALOR, conta.getValor());
        values.put(ContaContrato.TabelaConta.COLUMN_NAME_DATA, new SimpleDateFormat("dd-MM-yyyy").format(conta.getData()));
        values.put(ContaContrato.TabelaConta.COLUMN_NAME_DESCRICAO, conta.getDescricao());

        return db.insert(ContaContrato.TabelaConta.TABLE_NAME, null, values) > 0;
    }

    public List<Conta> Consultar(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(ContaContrato.TabelaConta.TABLE_NAME, null, null, null, null, null, null);

            List<Conta> contas = new ArrayList<>();
            while(cursor.moveToNext()) {
                if (cursor.getDouble(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_VALOR)) > 0){
                    contas.add(new Receita(
                            cursor.getDouble(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_VALOR)),
                            new SimpleDateFormat("dd-MM-yyyy").parse(cursor.getString(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_DATA))),
                            cursor.getString(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_DESCRICAO))
                    ));
                } else {
                    contas.add(new Despesa(
                            cursor.getDouble(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_VALOR)),
                            new SimpleDateFormat("dd-MM-yyyy").parse(cursor.getString(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_DATA))),
                            cursor.getString(cursor.getColumnIndex(ContaContrato.TabelaConta.COLUMN_NAME_DESCRICAO))
                    ));
                }
            }
            cursor.close();

            return contas;
        }
        catch (Exception e){
            Log.e("ContaContrato", e.getMessage());
            return null;
        }
    }
}
