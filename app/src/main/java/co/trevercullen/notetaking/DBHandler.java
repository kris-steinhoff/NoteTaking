package co.trevercullen.notetaking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler {
    public static final String KEY_ROWID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NOTE = "note";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "NoteDB";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table if not exists assignments (id integer primary key autoincrement, "
                    + "title VARCHAR not null, duedate date, course VARCHAR, notes VARCHAR );";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBHandler(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    // open db
    public DBHandler open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // close db
    public void close()
    {
        DBHelper.close();
    }

    // insert record
    public long insertRecord(String title, String note)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_NOTE, note);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // delete record
    public boolean deleteRecord(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    // get all records
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_NOTE}, null, null, null, null, null);
    }

    // get record
    public Cursor getRecord(long rowId) throws SQLException
    {
        Cursor C =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_TITLE, KEY_NOTE},
                        KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (C != null) {
            C.moveToFirst();
        }
        return C;
    }

    // update record
    public boolean updateRecord(long rowId, String title, String note)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_NOTE, note);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}

