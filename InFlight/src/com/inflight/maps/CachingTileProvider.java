package com.inflight.maps;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;

public class CachingTileProvider implements TileProvider {
	
	private DropboxTileProvider inner;
    private final Context context;
    private static String baseDir = "tile_cache";

	public CachingTileProvider(Context context) {
		inner = new DropboxTileProvider();
		this.context = context;
	}

	@Override
	public Tile getTile(int x, int y, int zoom) {
		String directory = baseDir +""+x+""+y+""+zoom+".png";
        boolean contained = false;
        for (final String file : context.fileList()) {
            if (file.contains(directory)) {
                contained = true;
                break;
            }
        }
        try {
            if (contained) {
                Log.d("Map", "Found "+directory);
                final FileInputStream fis = context.openFileInput(directory);
                byte[] imageBytes = IOUtils.toByteArray(fis);
                fis.close();
                return new Tile(256, 256, imageBytes);
            } else {
                final Tile t = inner.getTile(x, y, zoom);
                if (t != TileProvider.NO_TILE) {
	                final FileOutputStream fos = context.openFileOutput(directory,
	                        Context.MODE_PRIVATE);
	                fos.write(t.data);
	                fos.flush();
	                fos.close();
	            	Log.d("Map", "Tile "+directory+" saved "+(t==null));
                }
                return t;
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
            Log.e("Map", "Tile error " + ex.toString());
            return null;
        }
	}
	
}
