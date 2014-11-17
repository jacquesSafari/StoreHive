package com.store.hive.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.store.hive.model.products.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinashe.
 */
public class CategoriesDB extends SQLiteOpenHelper{

    private static final String TAG = CategoriesDB.class.getName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "categories_db" ;
    private static final String TABLE = "categories";

    private static final String KEY_ID = "catId";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "desc";

    public CategoriesDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + " ("
                + "_id INTEGER PRIMARY KEY, "
                 + KEY_ID + " TEXT, "
                + KEY_NAME + " TEXT, "
                + KEY_DESC + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        onCreate(db);
    }

    public void saveCategories(List<Category> categories){
        SQLiteDatabase db = this.getWritableDatabase();

        for(Category category: categories){
            Log.d(TAG, category.getCategoryName()+ " \n" + category.getCategoryDescription());

            ContentValues values = new ContentValues();
            values.put(KEY_ID, category.getId());
            values.put(KEY_NAME, category.getCategoryName());
            values.put(KEY_DESC, category.getCategoryDescription());

            db.insert(TABLE, null, values);
        }

        db.close();
    }

    public List<Category> getSavedCategories(){
        List<Category> categories = new ArrayList<Category>();

        String query = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        System.out.println("Query returned: "+cursor.getCount() + " records");
        System.out.println("Columns: "+ cursor.getColumnCount());

        if(cursor.moveToFirst()){
            do{

                Category cat = new Category();
                cat.setId(cursor.getString(1));
                cat.setCategoryName(cursor.getString(2));
                cat.setCategoryDescription(cursor.getString(3));

                System.out.println(cat.getCategoryName() +"\n"+cat.getCategoryDescription());

                categories.add(cat);
            }while (cursor.moveToNext());
        }

        return categories;
    }
}
