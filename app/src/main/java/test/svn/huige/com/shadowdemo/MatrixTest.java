package test.svn.huige.com.shadowdemo;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Shinelon on 2019/5/2.
 */

public class MatrixTest {

    public static void mapRectTest(){

        RectF rect = new RectF(400, 400, 1000, 800);
        // 构造一个matrix
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        Log.i("TAG", "mapRadius: "+rect.toString());

        boolean result = matrix.mapRect(rect);

        Log.i("TAG", "mapRadius: "+rect.toString());
        Log.e("TAG", "isRect: "+ result);
    }
}
