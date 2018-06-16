package senac.controlefinanceiro.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import senac.controlefinanceiro.objects.Conta;
import senac.controlefinanceiro.objects.Receita;

public class ContaContrato {

    private static final String SQL_CREATE_CONTA =
            "CREATE TABLE " + TabelaConta.TABLE_NAME + " (" +
                    TabelaConta._ID + " INTEGER PRIMARY KEY," +
                    TabelaConta.COLUMN_NAME_VALOR + " REAL," +
                    TabelaConta.COLUMN_NAME_DATA + " TEXT," +
                    TabelaConta.COLUMN_NAME_DESCRICAO + " TEXT)";

    private static final String SQL_DELETE_CONTA =
            "DROP TABLE IF EXISTS " + TabelaConta.TABLE_NAME;

    private ContaContrato() {}

    public static class TabelaConta implements BaseColumns {
        public static final String TABLE_NAME = "conta";
        public static final String COLUMN_NAME_VALOR = "valor";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
    }

    public class ContaDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "financeiro.db";

        public ContaDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_CONTA);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_CONTA);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public boolean Salvar(Conta conta){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TabelaConta.COLUMN_NAME_VALOR, conta.getValor());
            values.put(TabelaConta.COLUMN_NAME_DATA, conta.getData().toString());
            values.put(TabelaConta.COLUMN_NAME_DESCRICAO, conta.getDescricao());

            return db.insert(TabelaConta.TABLE_NAME, null, values) > 0;
        }

        public List<Conta> Consultar(){
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TabelaConta.TABLE_NAME, null, null, null, null, null, null);

            List<Conta> contas = new ArrayList<>();
            while(cursor.moveToNext()) {
                if (cursor.getDouble(cursor.getColumnIndex(TabelaConta.COLUMN_NAME_VALOR)) > 0){
                    contas.add(new Receita(
                            cursor.getDouble(cursor.getColumnIndex(TabelaConta.COLUMN_NAME_VALOR)),
                            cursor.getString(cursor.getColumnIndex(TabelaConta.COLUMN_NAME_DATA)),
                            cursor.getString(cursor.getColumnIndex(TabelaConta.COLUMN_NAME_DESCRICAO))
                    ));
                } else {

                }
                contas.add(new Conta());
                long itemId = cursor.getLong(
                        cursor.getColumnIndexOrThrow(FeedEntry._ID));
                itemIds.add(itemId);
            }
            cursor.close();
        }
    }

}
