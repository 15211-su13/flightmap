package com.inflight.plan.db;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

public class CursorIdTransformer implements Cursor {
	
	private Cursor input;

	public CursorIdTransformer(Cursor input) {
		this.input = input;
	}

	@Override
	public void close() {
		input.close();
		
	}

	@Override
	public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
		input.copyStringToBuffer(columnIndex, buffer);
	}

	@Override
	public void deactivate() {
		input.deactivate();
	}

	@Override
	public byte[] getBlob(int arg0) {
		return input.getBlob(arg0);
	}

	@Override
	public int getColumnCount() {
		return input.getColumnCount();
	}

	@Override
	public int getColumnIndex(String arg0) {
		if (arg0.equals("_id"))
			return input.getColumnIndex("id");
		return input.getColumnIndex(arg0);
	}

	@Override
	public int getColumnIndexOrThrow(String arg0)
			throws IllegalArgumentException {
		if (arg0.equals("_id"))
			return input.getColumnIndexOrThrow("id");
		return input.getColumnIndex(arg0);
	}

	@Override
	public String getColumnName(int arg0) {
		String innerName = input.getColumnName(arg0);
		if (innerName.equals("_id"))
			innerName = "id";
		return innerName;
	}

	@Override
	public String[] getColumnNames() {
		String[] innerNames = input.getColumnNames();
		for (int i = 0; i < innerNames.length; i++)
			if (innerNames[i].equals("_id"))
				innerNames[i] = "id";
		return innerNames;
	}

	@Override
	public int getCount() {
		return input.getCount();
	}

	@Override
	public double getDouble(int arg0) {
		return input.getDouble(arg0);
	}

	@Override
	public Bundle getExtras() {
		return input.getExtras();
	}

	@Override
	public float getFloat(int arg0) {
		return input.getFloat(arg0);
	}

	@Override
	public int getInt(int arg0) {
		return input.getInt(arg0);
	}

	@Override
	public long getLong(int arg0) {
		return input.getLong(arg0);
	}

	@Override
	public int getPosition() {
		return input.getPosition();
	}

	@Override
	public short getShort(int arg0) {
		return input.getShort(arg0);
	}

	@Override
	public String getString(int arg0) {
		return input.getString(arg0);
	}

	@Override
	public int getType(int arg0) {
		return input.getType(arg0);
	}

	@Override
	public boolean getWantsAllOnMoveCalls() {
		return input.getWantsAllOnMoveCalls();
	}

	@Override
	public boolean isAfterLast() {
		return input.isAfterLast();
	}

	@Override
	public boolean isBeforeFirst() {
		return input.isBeforeFirst();
	}

	@Override
	public boolean isClosed() {
		return input.isClosed();
	}

	@Override
	public boolean isFirst() {
		return input.isFirst();
	}

	@Override
	public boolean isLast() {
		return input.isLast();
	}

	@Override
	public boolean isNull(int arg0) {
		return input.isNull(arg0);
	}

	@Override
	public boolean move(int arg0) {
		return input.move(arg0);
	}

	@Override
	public boolean moveToFirst() {
		return input.moveToFirst();
	}

	@Override
	public boolean moveToLast() {
		return input.moveToLast();
	}

	@Override
	public boolean moveToNext() {
		return input.moveToNext();
	}

	@Override
	public boolean moveToPosition(int arg0) {
		return input.moveToPosition(arg0);
	}

	@Override
	public boolean moveToPrevious() {
		return input.moveToPrevious();
	}

	@Override
	public void registerContentObserver(ContentObserver arg0) {
		input.registerContentObserver(arg0);
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		input.registerDataSetObserver(arg0);
	}

	@Override
	@Deprecated
	public boolean requery() {
		return input.requery();
	}

	@Override
	public Bundle respond(Bundle arg0) {
		return input.respond(arg0);
	}

	@Override
	public void setNotificationUri(ContentResolver arg0, Uri arg1) {
		input.setNotificationUri(arg0, arg1);
	}

	@Override
	public void unregisterContentObserver(ContentObserver arg0) {
		input.unregisterContentObserver(arg0);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		input.unregisterDataSetObserver(arg0);
	}

}
