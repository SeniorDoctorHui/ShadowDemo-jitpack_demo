package test.svn.huige.com.shadowdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Shinelon on 2019/5/2.
 */

public class SetPolyToPoly extends View {
    private static final String TAG = "SetPolyToPoly";

    private int testPoint = 0;
    private int triggerRadius = 180;    // 触发半径为180px

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mPolyMatrix;         // 测试setPolyToPoly用的Matrix

    private float[] src = new float[8];
    private float[] dst = new float[8];

    private Paint pointPaint;

    public SetPolyToPoly(Context context) {
        this(context, null);
    }

    public SetPolyToPoly(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SetPolyToPoly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.black_line);

        float[] temp = {0, 0,                                    // 左上
                mBitmap.getWidth(), 0,                          // 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight()};                        // 左下
        src = temp.clone();
        dst = temp.clone();

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(50);
        pointPaint.setColor(0xffd19165);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        mPolyMatrix = new Matrix();
        mPolyMatrix.setPolyToPoly(src, 0, src, 0, 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float tempX = event.getX();
                float tempY = event.getY();

                // 根据触控位置改变dst
                for (int i = 0; i < testPoint * 2; i += 2) {
                    if (Math.abs(tempX - dst[i]) <= triggerRadius && Math.abs(tempY - dst[i + 1]) <= triggerRadius) {
                        dst[i] = tempX - 100;
                        dst[i + 1] = tempY - 100;
                        break;  // 防止两个点的位置重合
                    }
                }

                resetPolyMatrix(testPoint);
                invalidate();
                break;
        }

        return true;
    }

    public void resetPolyMatrix(int pointCount) {
        mPolyMatrix.reset();
        // 核心要点  dst 目标数组 dst [x,y]，存储内容为一组点
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, pointCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(100, 100);

        // 绘制坐标系
//        CanvasAidUtils.set2DAxisLength(300, 0, 1200, 0);
//        CanvasAidUtils.draw2DCoordinateSpace(canvas);

        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);

        float[] dst = new float[8];
        mPolyMatrix.mapPoints(dst, src);

        // 绘制触控点
        for (int i = 0; i < testPoint * 2; i += 2) {
            canvas.drawPoint(dst[i], dst[i + 1], pointPaint);
        }
//        Paint paint = new Paint();
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.BLACK);
//        Path path_dash = new Path();
//        path_dash.lineTo(0, 1020);
//
//        canvas.save();
//        canvas.translate(880, 100);
//        paint.setPathEffect(new DashPathEffect(new float[]{200, 200}, 0));
//        canvas.drawPath(path_dash, paint);
//        canvas.restore();

        // 画笔初始设置
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, 50, 50);

        // 方形
        Path rectPath = new Path();
        rectPath.addRect(rectF, Path.Direction.CW);

        // 圆形 椭圆
        Path ovalPath = new Path();
        ovalPath.addOval(rectF, Path.Direction.CW);

        // 子弹形状
        Path bulletPath = new Path();
        bulletPath.lineTo(rectF.centerX(), rectF.top);
        bulletPath.addArc(rectF, -90, 180);
        bulletPath.lineTo(rectF.left, rectF.bottom);
        bulletPath.lineTo(rectF.left, rectF.top);

        // 星星形状
        PathMeasure pathMeasure = new PathMeasure(ovalPath, false);
        float length = pathMeasure.getLength();
        float split = length / 5;
        float[] starPos = new float[10];
        float[] pos = new float[2];
        float[] tan = new float[2];
        for (int i = 0; i < 5; i++) {
            pathMeasure.getPosTan(split * i, pos, tan);
            starPos[i * 2 + 0] = pos[0];
            starPos[i * 2 + 1] = pos[1];
        }
        Path starPath = new Path();
        starPath.moveTo(starPos[0], starPos[1]);
        starPath.lineTo(starPos[4], starPos[5]);
        starPath.lineTo(starPos[8], starPos[9]);
        starPath.lineTo(starPos[2], starPos[3]);
        starPath.lineTo(starPos[6], starPos[7]);
        starPath.lineTo(starPos[0], starPos[1]);
        Matrix matrix = new Matrix();
        matrix.postRotate(-90, rectF.centerX(), rectF.centerY());
        starPath.transform(matrix);


        canvas.translate(0, 350);
        // 绘制分割线 - 方形
        canvas.translate(0, 100);
        paint.setPathEffect(new PathDashPathEffect(rectPath, rectF.width() * 1.5f, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawLine(0, 0, 1200, 0, paint);

        // 绘制分割线 - 圆形
        canvas.translate(0, 100);
        paint.setPathEffect(new PathDashPathEffect(ovalPath, rectF.width() * 1.5f, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawLine(0, 0, 1200, 0, paint);

        // 绘制分割线 - 子弹型
        canvas.translate(0, 100);
        paint.setPathEffect(new PathDashPathEffect(bulletPath, rectF.width() * 1.5f, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawLine(0, 0, 1200, 0, paint);

        // 绘制分割线 - 星型
        canvas.translate(0, 100);
        paint.setPathEffect(new PathDashPathEffect(starPath, rectF.width() * 0.5f, 0, PathDashPathEffect.Style.ROTATE));
        canvas.drawLine(0, 0, 120, 0, paint);


    }

    public void setTestPoint(int testPoint) {
        this.testPoint = testPoint > 4 || testPoint < 0 ? 4 : testPoint;
        dst = src.clone();
        resetPolyMatrix(this.testPoint);
        invalidate();
    }
}
