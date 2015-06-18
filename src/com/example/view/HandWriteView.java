package com.example.view;

import com.example.graphtest1.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class HandWriteView extends View {

	private Paint paint = null;
	private Bitmap originalBitmap = null;
	private Bitmap new1Bitmap = null;
	// private Bitmap new2Bitmap = null;
	private float clickX = 0, clickY = 0;
	private float startX = 0, startY = 0;
	private boolean isMove = true;
	private boolean isClear = false;
	private int color = Color.GREEN;
	private float strokeWidth = 2.0f;

	public HandWriteView(Context context, AttributeSet attrs) {
		super(context, attrs);

		originalBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.img).copy(Bitmap.Config.ARGB_8888, true);

		new1Bitmap = Bitmap.createBitmap(originalBitmap);
	}

	public void clear() {
		isClear = true;
		// new2Bitmap = Bitmap.createBitmap(originalBitmap);
		invalidate();
	}

	public void setstyle(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		HandWriting();
		canvas.drawBitmap(new1Bitmap, 0, 0, null);

	}

	public Bitmap HandWriting() {
		Canvas canvas = null;

		if (isClear) {
			new1Bitmap = Bitmap.createBitmap(originalBitmap);
			isClear=false;
		}
		canvas = new Canvas(new1Bitmap);
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		paint.setColor(color);
		paint.setStrokeWidth(strokeWidth);
		if (isMove) {
			canvas.drawLine(startX, startY, clickX, clickY, paint);
		}

		startX = clickX;
		startY = clickY;
		return new1Bitmap;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		clickX = event.getX();
		clickY = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			isMove = false;
			invalidate();
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

			isMove = true;
			invalidate();
			return true;
		}

		return super.onTouchEvent(event);
	}

}
