package com.inflight.plan.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DAO extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.inflight/databases/";
 
    private static String DB_NAME = "fdat2.sqlite";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DAO(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
    	if(!checkDatabase()){
        	this.getReadableDatabase();
        	try {
    			copyDatabase();
    			openDatabase();
        	} catch (IOException e) {
        		throw new RuntimeException(e);
        	}
    	}
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDatabase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
    		return false;
    	}
 
    	if(checkDB == null)
    		return false;
    	myDataBase = checkDB;
    	return true;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDatabase() throws IOException{
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
    	String outFileName = DB_PATH + DB_NAME;
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDatabase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
	
	public LatLng getPosition(String name) {
		try {
		if (name.length() == 3) {
			Cursor ret = this.myDataBase.rawQuery("SELECT `lat`, `lon` FROM `vor` WHERE ident=?;", new String[] { name });
			return getLatLng(ret);
		} else if (name.length() == 4) {
			Cursor ret = this.myDataBase.rawQuery("SELECT `lat`, `lon` FROM `airports` WHERE ident=?;", new String[] { name });
			return getLatLng(ret);
		} else if (name.length() == 5) {
			Cursor ret = this.myDataBase.rawQuery("SELECT `lat`, `lon` FROM `fixes` WHERE ident=?;", new String[] { name });
			return getLatLng(ret);
		}
		} catch (Exception e) {
			throw new RuntimeException(e.toString()+" at "+name);
		}
		throw new RuntimeException("Invalid route");
	}

	private LatLng getLatLng(Cursor ret) {
		if(ret != null)
		{
			ret.moveToFirst();
		    int i = 0;
		    if(ret.isAfterLast())
		    	throw new RuntimeException("Invalid point");
		    
		    float lat = ret.getFloat(ret.getColumnIndex("lat"));
		    float lon = ret.getFloat(ret.getColumnIndex("lon"));
		    ret.close();
		    return new LatLng(lat, lon);
		    
		}
		throw new RuntimeException("Invalid point");
	}
 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
 
}