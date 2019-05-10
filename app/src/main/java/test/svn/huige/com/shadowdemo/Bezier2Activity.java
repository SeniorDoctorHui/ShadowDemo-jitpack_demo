package test.svn.huige.com.shadowdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class Bezier2Activity extends AppCompatActivity {
    Bezier2 mBezier2;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier2);
        mBezier2=findViewById(R.id.bezier);
        rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb1){
                    mBezier2.setMode(true);
                }else{
                    mBezier2.setMode(false);
                }
            }
        });
    }

}
