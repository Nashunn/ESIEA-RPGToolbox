package fr.nashunn.rpg_toolbox.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawDice extends View {
    Path path;
    Paint paint;
    float length;

    public DrawDice(Context context) {
        super(context);
        init();
    }

    public DrawDice(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawDice(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        List color = MainActivity.randomColor();

        paint = new Paint();
        paint.setColor((Integer) color.get(0));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        // get middle of screen
        Display disp = ((WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int middleWidth = disp.getWidth()/2;
        int middleHeight = disp.getHeight()/2;

        path = new Path();

        path.moveTo(middleWidth-100, middleHeight-150);
        path.lineTo(middleWidth-100, middleHeight+50);
        path.lineTo(middleWidth+100, middleHeight+50);
        path.lineTo(middleWidth+100, middleHeight-150);
        path.lineTo(middleWidth-100, middleHeight-150);

        // Measure the path
        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();

        float[] intervals = new float[]{length, length};

        ObjectAnimator animator = ObjectAnimator.ofFloat(DrawDice.this, "phase", 1.0f, 0.0f);
        animator.setDuration(2000);
        animator.start();
    }

    //is called by animator object
    public void setPhase(float phase) {
        Log.d("pathview","setPhase called with:" + String.valueOf(phase));
        paint.setPathEffect(createPathEffect(length, phase, 0.0f));
        invalidate();//will call onDraw
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                Math.max(phase * pathLength, offset));
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawPath(path, paint);
    }
}
