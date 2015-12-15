package net.dprogrammer.crudbasico_dpnet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dprogrammer on 14/12/2015.
 */
public class ContatosAdapter {

    private SQLiteDatabase mDb;
    private static Context context;

    private static ContatosAdapter instance = new ContatosAdapter();
    public static ContatosAdapter getInstance(Context ctx) {

        context = ctx;

        if (instance.mDb == null || !instance.mDb.isOpen()) {
            instance.mDb = new DBHelper(ctx).getWritableDatabase();
        }
        return instance;
    }

    private ContatosAdapter() { }

    public void InsertContato(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("sobrenome", contato.getSobrenome());
        values.put("email", contato.getEmail());

        mDb.insert("contatos", null, values);

    }

    public boolean UpdateContato(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("sobrenome", contato.getSobrenome());
        values.put("email", contato.getEmail());

        return mDb.update("contatos", values, "_id = " + contato.getId(), null) > 0;
    }

    public boolean DeleteGrupo(Contato contato){
        return mDb.delete("contatos", " _id = " + contato.getId(), null) > 0;
    }

    public Cursor getAllRows(){
        Cursor cursor;

        cursor = mDb.rawQuery("SELECT * FROM contatos" + " ORDER BY nome ASC" , null);

        return  cursor;
    }

    public List<Contato> getAllList(Cursor contatos) {

        ArrayList<Contato> list = new ArrayList<>();
        long rows = contatos.getCount();
        contatos.moveToFirst();

        for (int i = 0; i < rows; ++i){
            Contato contato = new Contato();
            contato.setId(contatos.getInt(contatos.getColumnIndex("_id")));
            contato.setNome(contatos.getString(contatos.getColumnIndex("nome")));
            contato.setSobrenome(contatos.getString(contatos.getColumnIndex("sobrenome")));
            contato.setEmail(contatos.getString(contatos.getColumnIndex("email")));
            list.add(contato);

            contatos.moveToNext();
        }

        return list;
    }
}
