package net.spectral.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * This class echoes a string called from JavaScript.
 */
public class Spectral extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        // Test
		if (action.equals("echo")) {
			String phrase = args.getString(0);
			Toast.makeText(webView.getContext(),phrase,Toast.LENGTH_LONG).show();
            return true;
		
		// Media-Scanner
        } else if (action.equals("scanPhoto")) {
			String fileUri = args.optString(0);
			if(fileUri!=null && !fileUri.equals("")) {
				Uri contentUri = Uri.parse(fileUri);

				Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
				mediaScanIntent.setData(contentUri);
				this.cordova.getActivity().sendBroadcast(mediaScanIntent);

				callbackContext.success("OK");
				return true;
			} else {
				callbackContext.error("No action param provided: "+action);
				return false;
			}
			
		//Datum
		} else if (action.equals("getDate")) {
			// An example of returning data back to the web layer
			final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
			callbackContext.sendPluginResult(result);
		}
		
        return false;
    }

}