package loran.senai.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {

    public static String nomeUsuario = null;

    //Comando create para montar a tabela
    private final String criaTabelaUsuario = "CREATE TABLE usuario (" +
            "usuario VARCHAR(20) PRIMARY KEY, " +
            "nome VARCHAR(40) NOT NULL, " +
            "senha VARCHAR(30) NOT NULL); ";

    public BancoDeDados (Context context, int version){
        super(context, "BD Aplicativo", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(criaTabelaUsuario); //Executa o comando SQL
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        //Habilitar a chave estrangeira
        db.execSQL("PRAGMA foreign_keys = ON; ");
    }

    public boolean cadastraUsuario(String nome, String usuario, String senha){
        //CRiar um objeto da classe SQLiteDataBase e abre a conexão
        SQLiteDatabase banco = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("usuario", usuario);
        valores.put("senha", senha);

        if(banco.insert("usuario", null, valores) != -1){
            banco.close();
            return true;
        }else{
            banco.close();
            return false;
        }
    }

    public boolean verificaAcesso(String usuario, String senha){
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery("SELECT senha, nome FROM usuario WHERE usuario = ?",
                new String[] {usuario});

        if(c.moveToFirst()){
            do{
                String senhaBanco = c.getString(0);
                String nomeBanco = c.getString(1);

                if(senhaBanco.equals(senha)){
                    nomeUsuario = nomeBanco;
                    return true;
                }
            }while(c.moveToNext());
        }

        db.close();
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
