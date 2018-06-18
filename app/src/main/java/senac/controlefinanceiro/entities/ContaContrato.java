package senac.controlefinanceiro.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import senac.controlefinanceiro.objects.Conta;
import senac.controlefinanceiro.objects.Despesa;
import senac.controlefinanceiro.objects.Receita;

public class ContaContrato {

    public static final String SQL_CREATE_CONTA =
            "CREATE TABLE " + TabelaConta.TABLE_NAME + " (" +
                    TabelaConta._ID + " INTEGER PRIMARY KEY," +
                    TabelaConta.COLUMN_NAME_VALOR + " REAL," +
                    TabelaConta.COLUMN_NAME_DATA + " TEXT," +
                    TabelaConta.COLUMN_NAME_DESCRICAO + " TEXT)";

    public static final String SQL_DELETE_CONTA =
            "DROP TABLE IF EXISTS " + TabelaConta.TABLE_NAME;

    private ContaContrato() {}

    public static class TabelaConta implements BaseColumns {
        public static final String TABLE_NAME = "conta";
        public static final String COLUMN_NAME_VALOR = "valor";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
    }

}

