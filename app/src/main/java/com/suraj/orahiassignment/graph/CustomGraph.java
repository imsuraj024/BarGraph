package com.suraj.orahiassignment.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.suraj.orahiassignment.R;

import java.util.ArrayList;

public class CustomGraph extends View {

    private Paint paint;
    private ArrayList<Integer> integerArrayList = new ArrayList<>();
    private ArrayList<String> stringArrayList = new ArrayList<>();
    Context context;
    Canvas canvas;

    public CustomGraph(Context context) {
        super(context);
        this.context = context;
    }

    public CustomGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public CustomGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setDither(true);


        canvas.drawLine(50, 1000, 1070, 1000, paint); //x- axis
        canvas.drawLine(1070, 1000, 1060, 990, paint); //arrow line up
        canvas.drawLine(1070, 1000, 1060, 1010, paint); //arrow line down

        canvas.drawLine(50, 1000, 50, 50, paint); //y-axis
        canvas.drawLine(50, 50, 40, 60, paint); //arrow line left
        canvas.drawLine(50, 50, 60, 60, paint); // arrow line right

        paint.setColor(Color.RED);
        paint.setTextSize(30);
        if (integerArrayList.size() != 0)
            for (int i = 0; i < integerArrayList.size(); i++) {
                RectF rectF = new RectF(130 + 83*i, 950 - integerArrayList.get(i), 70 + 83*i, 1000);
                canvas.drawRect(rectF, paint);
                canvas.drawText(integerArrayList.get(i) + "" , 83 + 83 *i, 930 - integerArrayList.get(i),paint);
                canvas.drawText(stringArrayList.get(i) + "" , 83 + 83 *i, 1050 ,paint);

            }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setHeight(ArrayList<Integer> heightList, ArrayList<String> nameList){
        integerArrayList = heightList;
        stringArrayList = nameList;
        postInvalidate();
    }



}
