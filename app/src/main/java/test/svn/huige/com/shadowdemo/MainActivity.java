package test.svn.huige.com.shadowdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openMatrix(View view) {
        startActivity(new Intent(this,MatrixActivity.class));
    }

    public void openShadow(View view) {
        startActivity(new Intent(this,ShadowActivity.class));
    }

    public void openBezier(View view) {
        startActivity(new Intent(this,BezierActivity.class));
    }
}
