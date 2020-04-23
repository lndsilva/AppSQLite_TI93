package br.com.local.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Criando o esquema de banco de dados. Criando o banco de dados
            SQLiteDatabase bd = openOrCreateDatabase("dbPessoas", MODE_PRIVATE, null);

            //Criar a tabela do banco de dados
            bd.execSQL("CREATE TABLE IF NOT EXISTS tbPessoas (nome VARCHAR,idade INT(3))");

            //Inserindo dados na tabela
            bd.execSQL("INSERT INTO tbPessoas(nome,idade)VALUES('Maria',50)");
            bd.execSQL("INSERT INTO tbPessoas(nome,idade)VALUES('João',60)");

            //Buscando os dados na tabela
            Cursor cursor = bd.rawQuery("SELECT nome, idade FROM tbPessoas", null);

            //Qual a coluna que estou pegando os valores
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            //Vai guiar o cursor para frente da tabela inteira
            cursor.moveToFirst();

            while (cursor != null) {
                Log.i("Resultado - nome", cursor.getString(indiceNome));
                Log.i("Resultado - idade", cursor.getString(indiceIdade));
                //Guiar o cursor para frente de cada registro
                cursor.moveToNext();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
