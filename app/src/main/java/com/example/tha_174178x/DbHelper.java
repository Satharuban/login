package com.example.tha_174178x;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "THA_DB.db";
    //user table name
    private static final String USER_INFOR = "User_Infor";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_info_Table = "CREATE TABLE " + USER_INFOR + "("
                + "name" + " TEXT,"
                + "index_no" + " TEXT ,"
                + "email" + " TEXT,"
                + "mobile" + " TEXT,"
                + "GPA" + " TEXT,"
                + "password" + " TEXT" + ")";



        db.execSQL(user_info_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_INFOR);
        onCreate(db);

    }

    public boolean insertUserData(String name,String index_no,String email,String mobile_no,String current_gpa,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put("name", name);
        cValues.put("index_no", index_no);
        cValues.put("email", email);
        cValues.put("mobile", mobile_no);
        cValues.put("GPA", current_gpa);
        cValues.put("password", password);

        long result = db.insert(USER_INFOR,null, cValues);
        db.close();

        if(result == -1) {
            Log.d("error message", "error in user Data insert");
            return false;
        }
        else{
            return true;
        }

    }


    //login
    public boolean loginUser(String indexNo, String password){
        String[] columns = {"index_no"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "index_no=? and password = ?";
        String[] selectionArgs = {indexNo, password};

        Cursor cursor = db.query(USER_INFOR, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }


    //get user information
    public Cursor getUserDetails(){
        String selectQuery = "SELECT  name,index_no,email,mobile,GPA,password FROM  User_Infor WHERE index_no='"+ login.user_index+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result_Detail = db.rawQuery(selectQuery, null);

        return result_Detail;
    }
}
