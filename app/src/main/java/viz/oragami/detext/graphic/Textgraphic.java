package viz.oragami.detext.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class Textgraphic extends GraphicOverlay.Graphic{

    private static final int Tect_clour= Color.BLUE;
    private static final float Text_size=54.0f;

    private static final float Stroke_width=4.0f;

    private  final Paint rectPaint, textPaint;
    private  final FirebaseVisionText.Element text;



    public Textgraphic(GraphicOverlay overlay, FirebaseVisionText.Element text) {
        super(overlay);
        this.text=text;

        rectPaint= new Paint();
        rectPaint.setColor(Tect_clour);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(Stroke_width);


        textPaint= new Paint();
        textPaint.setColor(Tect_clour);
        textPaint.setTextSize(Text_size);
        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {

        if(text==null){
            throw new IllegalStateException("Attemting to scan empty text");

        }
        RectF rect = new RectF(text.getBoundingBox());

        rect.top=translateY(rect.top);
        rect.bottom=translateY(rect.bottom);
        rect.left=translateX(rect.left);
        rect.right=translateY(rect.right);

        canvas.drawRect(rect,rectPaint);
        canvas.drawText(text.getText(),rect.left,rect.bottom,textPaint);
    }
}
