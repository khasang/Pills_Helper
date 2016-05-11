package com.khasang.pillshelper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.khasang.pillshelper.db.PillsDBContract.*;

public class PillsDBHelper extends SQLiteOpenHelper{

    private static volatile PillsDBHelper instance = null;

    private PillsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static PillsDBHelper getInstance(Context context){
        if(instance == null){
            synchronized (PillsDBHelper.class){
                if(instance == null){
                    return new PillsDBHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PillsDB.db";

    private static final String SQL_CREATE_TABLE_ATTR =
            "CREATE TABLE " + Attr.TABLE_NAME + " (" +
                    Attr._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Attr.COLUMN_NAME + " TEXT NOT NULL" +
            " )";

    private static final String SQL_CREATE_TABLE_TYPE =
            "CREATE TABLE " + Type.TABLE_NAME + " (" +
                    Type._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Type.COLUMN_NAME + " TEXT NOT NULL" +
            " )";

    private static final String SQL_CREATE_TABLE_ENTITY =
            "CREATE TABLE " + Entity.TABLE_NAME + " (" +
                    Entity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entity.COLUMN_TYPE_ID + " INTEGER REFERENCES " + Type.TABLE_NAME + " (" + Type._ID + ") ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +
                    Entity.COLUMN_NAME + " TEXT NOT NULL" +
            " )";

    private static final String SQL_CREATE_TABLE_VAL =
            "CREATE TABLE " + Val.TABLE_NAME + " (" +
                    Val._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Val.COLUMN_ATTR_ID + " INTEGER REFERENCES " + Attr.TABLE_NAME + " (" + Attr._ID + ") ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +
                    Val.COLUMN_ENTITY_ID + " INTEGER REFERENCES " + Entity.TABLE_NAME + " (" + Entity._ID + ") ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +
                    Val.COLUMN_VALUE + " TEXT" +
            " )";

    private static final String SQL_CREATE_TABLE_REF =
            "CREATE TABLE " + Ref.TABLE_NAME + " (" +
                    Ref._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Ref.COLUMN_ATTR_ID + " INTEGER REFERENCES " + Attr.TABLE_NAME + " (" + Attr._ID + ") ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +
                    Ref.COLUMN_ENTITY_ID + " INTEGER REFERENCES " + Entity.TABLE_NAME + " (" + Entity._ID + ") ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +
                    Ref.REF_ID + " INTEGER REFERENCES " + Entity.TABLE_NAME + " (" + Entity._ID + ")  ON DELETE CASCADE ON UPDATE CASCADE NOT NULL" +
            " )";

    private static final String SQL_CREATE_INDEX_ENTITY_TYPE_ID_NAME =
            "CREATE INDEX entity_type_id_name ON " + Entity.TABLE_NAME + " (" + Entity.COLUMN_TYPE_ID + ", " + Entity.COLUMN_NAME + ")";

    private static final String SQL_CREATE_INDEX_REF_ENTITY_ID_ATTR_ID =
            "CREATE INDEX ref_entity_id_attr_id ON " + Ref.TABLE_NAME +" (" + Ref.COLUMN_ENTITY_ID + ", " + Ref.COLUMN_ATTR_ID + ")";

    private static final String SQL_CREATE_INDEX_VAL_ENTITY_ID_ATTR_ID =
            "CREATE INDEX val_entity_id_attr_id ON " + Val.TABLE_NAME +" (" + Val.COLUMN_ENTITY_ID + ", " + Val.COLUMN_ATTR_ID + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ATTR);
        db.execSQL(SQL_CREATE_TABLE_TYPE);
        db.execSQL(SQL_CREATE_TABLE_ENTITY);
        db.execSQL(SQL_CREATE_TABLE_VAL);
        db.execSQL(SQL_CREATE_TABLE_REF);
        db.execSQL(SQL_CREATE_INDEX_ENTITY_TYPE_ID_NAME);
        db.execSQL(SQL_CREATE_INDEX_REF_ENTITY_ID_ATTR_ID);
        db.execSQL(SQL_CREATE_INDEX_VAL_ENTITY_ID_ATTR_ID);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}