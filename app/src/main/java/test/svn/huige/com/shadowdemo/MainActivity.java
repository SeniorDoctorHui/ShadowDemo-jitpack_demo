package test.svn.huige.com.shadowdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void openMatrix(View view) {
        startActivity(new Intent(this, MatrixActivity.class));
    }

    public void openShadow(View view) {
        startActivity(new Intent(this, ShadowActivity.class));
    }

    public void openBezier(View view) {
        startActivity(new Intent(this, BezierActivity.class));
    }

    public void openBezier2(View view) {
        startActivity(new Intent(this, Bezier2Activity.class));
    }

    public void openCoroutine(View view) {startActivity(new Intent(this, CoroutineTestActivity.class));}
}
