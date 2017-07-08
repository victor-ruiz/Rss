package mx.gob.cdmx.om.cgma.rssdemo;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by victor on 4/04/17.
 */

public class CustomVolleyRequestQueue {

    private static  CustomVolleyRequestQueue customVolleyRequestQueue;
    private static Context myContext;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private CustomVolleyRequestQueue(Context context){
        myContext = context;

        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        })



    }


    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
