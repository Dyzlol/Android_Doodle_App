package com.example.tony_macbook.doodleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
//import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


/**
 * Created by Tony-MacBook on 3/7/16.
 */
public class DoodleView extends View {

    private Paint paint = new Paint();
    private Paint old_paint = new Paint();
    private Paint bit_paint = new Paint();
    private Path path = new Path();
    private ArrayList<Path> paths = new ArrayList<Path>();
    private Map<Path, Integer> colorsMap = new HashMap<Path, Integer>();
    private Map<Path, Float> widthMap = new HashMap<Path, Float>();
    private Bitmap can_bit = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);;
    //private Canvas canvas = new Canvas(can_bit);

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float) 5.0);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //old_paint.setStrokeWidth((float) 5.0);
        //old_paint.setStrokeWidth((float) 5.0);
    }

    public void setColor(String newColor) {
        invalidate();
        //int paintColor = Color.parseColor(newColor);
        //old_paint = paint;
        paint.setColor(Color.parseColor(newColor));
    }

    public void setStroke(float width) {
        paint.setStrokeWidth(width);
        //old_paint.setStrokeWidth(width);
    }

    public void reset_canvas() {
        Canvas canvas1 = new Canvas(can_bit);
        paths.clear();
        colorsMap.clear();
        draw(canvas1);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(can_bit, 0, 0, bit_paint);
        for (Path p : paths) {
            old_paint.setColor(colorsMap.get(p));
            old_paint.setStyle(Paint.Style.STROKE);
            old_paint.setStrokeWidth(widthMap.get(p));
            canvas.drawPath(p, old_paint);
        }
        canvas.drawPath(path, paint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(touchX, touchY);
                paths.add(path);
                Integer paint3 = paint.getColor();
                Float paint3_w = paint.getStrokeWidth();
                widthMap.put(path,paint3_w);
                colorsMap.put(path,paint3);
                path = new Path();
                invalidate();
                break;
        }

        invalidate();
        return true;
    }

    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bmp;
    }

}
