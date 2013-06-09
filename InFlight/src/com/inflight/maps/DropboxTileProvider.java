package com.inflight.maps;

import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import com.google.android.gms.maps.model.UrlTileProvider;

public class DropboxTileProvider extends UrlTileProvider {

	public DropboxTileProvider() {
		super(256, 256);
	}

	@Override
	public URL getTileUrl(int x, int y, int zoom) {
		try {
			return new URL("https://dl.dropboxusercontent.com/u/10831458/tiles2/"+zoom+"/"+x+"/"+y+".png");
		} catch (MalformedURLException e) {
			Log.d("MalformedURLException", "getTile x=" + x + ", y=" + y + ", zoomLevel=" + zoom + " raised an exception", e);
			throw new RuntimeException(e);
		}
	}

}
