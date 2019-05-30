package com.rd.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.rd.animation.data.Value;
import com.rd.animation.data.type.WormAnimationValue;
import com.rd.draw.data.Indicator;
import com.rd.draw.data.Orientation;

public class WormDrawer extends BaseDrawer {

    public RectF rect;
    private Paint strokePaint;

    public WormDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
        rect = new RectF();

        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setAntiAlias(true);
    }

    public void draw(
            @NonNull Canvas canvas,
            @NonNull Value value,
            int coordinateX,
            int coordinateY) {

        if (!(value instanceof WormAnimationValue)) {
            return;
        }

        WormAnimationValue v = (WormAnimationValue) value;
        int rectStart = v.getRectStart();
        int rectEnd = v.getRectEnd();

        int radius = indicator.getRadius();
        int unselectedColor = indicator.getUnselectedColor();
        int selectedColor = indicator.getSelectedColor();

        if (indicator.getOrientation() == Orientation.HORIZONTAL) {
            rect.left = rectStart;
            rect.right = rectEnd;
            rect.top = coordinateY - radius;
            rect.bottom = coordinateY + radius;

        } else {
            rect.left = coordinateX - radius;
            rect.right = coordinateX + radius;
            rect.top = rectStart;
            rect.bottom = rectEnd;
        }

        strokePaint.setColor(unselectedColor);
        strokePaint.setStrokeWidth(3);
        canvas.drawCircle(coordinateX, coordinateY, radius - 3, strokePaint);

        paint.setColor(selectedColor);
        canvas.drawRoundRect(rect, radius, radius, paint);
    }
}
