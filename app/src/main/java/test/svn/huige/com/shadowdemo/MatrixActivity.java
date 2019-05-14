package test.svn.huige.com.shadowdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MatrixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        MatrixTest.mapRectTest();
        SetPolyToPoly poly = (SetPolyToPoly) findViewById(R.id.poly);
        poly.setTestPoint(4);
        SharedPreferences sp = this.getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("testInt",3).commit();
        String data=sp.getString("testInt","");
        Log.i("TAG","data="+data);
    }
}
