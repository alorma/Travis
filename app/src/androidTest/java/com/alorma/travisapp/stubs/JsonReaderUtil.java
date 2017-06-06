package com.alorma.travisapp.stubs;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper to transform input file in assets as String to be used in Tests
 */
public class JsonReaderUtil {

    public static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    public static String getStringFromFile(Context context, String filePath) {
        try {
            InputStream stream = context.getResources().getAssets().open(filePath);
            String ret = convertStreamToString(stream);
            stream.close();
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}