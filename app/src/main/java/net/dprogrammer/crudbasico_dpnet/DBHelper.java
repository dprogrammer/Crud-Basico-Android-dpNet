package net.dprogrammer.crudbasico_dpnet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dprogrammer on 14/12/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbcontatosprogctrl";
    private static final int DB_VERSION = 1;

    private static final String TABLE_CREATE_CONTATOS = "CREATE TABLE contatos (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nome NVARCHAR( 30 ) not null, " +
            " sobrenome NVARCHAR( 30 ) not null, " +
            " email NVARCHAR( 150 ) not null );";

    public DBHelper(Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_CONTATOS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
