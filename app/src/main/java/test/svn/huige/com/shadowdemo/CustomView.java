package test.svn.huige.com.shadowdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 所属系统: ShadowDemo-master-master
 * 所属模块:  ${TODO}
 * 功能描述:  ${TODO}
 * 创建时间: 2019/5/6 15:59
 * 维护人: 李主辉
 * Copyright @ Jerrisoft 2019. All rights reserved.
 * ┌─────────────────────────────────────────────────────┐
 * │ 此技术信息为本公司机密信息，未经本公司书面同意禁止向第三方披露．│
 * │ 版权所有：杰人软件(深圳)有限公司                          │
 * └─────────────────────────────────────────────────────┘
 */
public class CustomView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
        //相对于上一个参考点移动
        canvas.translate(100,100);

        Path path = new Path();                     // 创建Path

//        path.lineTo(200, 200);                      // lineTo
//        path.lineTo(200,0);

//        path.close();                               // close
//
//        canvas.drawPath(path, mPaint);              // 绘制Path


        path.addRect(-200,-200,200,200, Path.Direction.CW);  //CW顺时针,CCW逆时针
//        path.addRect(-200,-200,200,200, Path.Direction.CCW);  //CW顺时针,CCW逆时针

        path.setLastPoint(-300,300);                // <-- 重置最后一个点的位置  顺时针矩形四个点顺序为A-B-C-D 逆时针顺序为A-D-C-B 因此顺时针最后一个点为D,逆时针最后一个点为B

        canvas.drawPath(path,mPaint);
        
    }
}
