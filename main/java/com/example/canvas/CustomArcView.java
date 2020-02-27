package com.example.canvas;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.math.BigDecimal;

import java.math.RoundingMode;


public class CustomArcView extends View {

    private  int width,height;
    private int[] prices,colors;

    private Paint paint;

    private RectF rectF;

    private float startAngle=270,sweepAngle=0;

    public CustomArcView(Context context) {
        super(context);

        DisplayMetrics dm = getResources().getDisplayMetrics() ;

        width=dm.widthPixels;
        height=dm.heightPixels;

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);

        int dpSize =  30;
        float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dm);
        paint.setStrokeWidth(strokeWidth);

        rectF=new RectF((float) width * 5 / 100,(float)height * 5 /100,(float)width * 95 / 100,(float)width * 95 /100);

    }

    public void setPrices(int[] prices, int [] colors){
        this.prices=prices;
        this.colors=colors;
        invalidate();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(prices  != null) {
            progress(canvas);

        }else {
            canvas.drawArc(rectF, 270, 360, false, paint);
        }
    }

    //
    private void progress(Canvas canvas){
        BigDecimal sum,temp;

        sum=new BigDecimal("0");

        for (int i = 0; i <prices.length ; i++) {

            temp=new BigDecimal(prices[i]+"");
            sum=sum.add(temp);

        }

        temp=new BigDecimal("100");
        sum=sum.divide(temp,2,RoundingMode.HALF_UP);

        for (int i = 0; i < prices.length; i++) {

            float percent;

            temp=new BigDecimal((prices[i])+"");

            percent = temp.divide(sum,2,RoundingMode.HALF_UP).floatValue();

            startAngle = sweepAngle == 0 ? 270 : sweepAngle + startAngle - 360;
            sweepAngle =  (360 * percent / 100);

            paint.setColor(colors[i]);

            canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);
        }
    }

}
