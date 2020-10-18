package net.sample.colorbattle;

import java.util.*;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.*;
import android.graphics.*;

public class DrawColor extends View {
	private Bitmap bitmap;
	private Canvas canvasR;
	private Paint paint;
	private int n = 0, result = 0, blue = 0, orange = 0, white = 0, width, height;
	private int pixel, picColor, pixels[];
	private float x, y, ranX, ranY;
	private ArrayList<Float> circleX = new ArrayList<Float>();
	private ArrayList<Float> circleY = new ArrayList<Float>(); 
	private ArrayList<Integer> order = new ArrayList<Integer>();
	private ArrayList<Integer> checkP = new ArrayList<Integer>();
	//�R���X�g���N�^
	public DrawColor(Context context) {
		//View�̏�����
		super(context);
		setFocusable(true);
		x = -50;
		y = -50;
	}

	//��ʂ��^�b�`�������ɌĂяo�����
	public boolean onTouchEvent(MotionEvent e) {
		switch(e.getAction()){
		case MotionEvent.ACTION_DOWN:
			x = e.getX();
			y = e.getY();
			circleX.add(x);
			circleY.add(y);
			order.add(n+1);
			checkP.add(0);
			//onDraw()���Ăяo��
			this.invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	//�`�惁�\�b�h
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (bitmap == null) {
			bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
			canvasR = new Canvas(bitmap); //���ʌv�Z�p
			paint = new Paint();
			paint.setAntiAlias(true);
		}
		if (result != 1) {

			//�F�����`
			paint.setColor(Color.rgb(67, 135, 233));
			canvasR.drawRect(0, 0, getWidth(), getHeight()/2, paint);
			//�I�����W�����`
			paint.setColor(Color.rgb(255, 183, 76));
			canvasR.drawRect(0, getHeight()/2, getWidth(), getHeight(), paint);

			//Random�N���X�̃C���X�^���X��
			Random randX = new Random();
			Random randY = new Random();
			ranX = randX.nextInt(getWidth()+1);
			ranY = randY.nextInt(getHeight()+1);
			circleX.add(ranX);
			circleY.add(ranY);
			order.add(n+1);
			checkP.add(1);

			//�~��`��
			for (int i = 0;i < order.size(); i++) {
				if (checkP.get(i) == 0) {
					paint.setColor(Color.rgb(255, 183, 76));
				} else {
					paint.setColor(Color.rgb(67, 135, 233));
				}
				canvasR.drawCircle(circleX.get(i), circleY.get(i), 40, paint);
			}
			canvas.drawBitmap(bitmap, 0, 0, paint);
		} else {
			/*���ʌv�Z����*/
			canvas.drawBitmap(bitmap, 0, 0, paint);
			width = bitmap.getWidth();
			height = bitmap.getHeight();
			pixels = new int [width * height];
			bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					pixel = pixels[x + y * width];
					if (Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel))
							== Color.rgb(67, 135, 233)) {
						blue += 1;
					} else  if (Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel))
							== Color.rgb(255, 183, 76)){
						orange += 1;
					} else {
						white += 1;//�����Ȃ���������
					}
				}
			}
			paint.setColor(Color.BLACK);
			canvas.drawText("blue+orange+white:"+ (blue+orange+white) +
					" blue:"+ blue +" orange:" + orange +" white:"+ white, 50, 50, paint);

			paint.setColor(Color.RED);
			if (blue > orange) {
				canvas.drawText("�̏����I", 50, 100, paint);
			} else if (blue < orange) {
				canvas.drawText("�I�����W�̏����I", 50, 100, paint);
			} else if (blue == orange) {
				canvas.drawText("���������I", 50, 100, paint);
			}
		}
	}

	public void Result () {
		try {
			result = 1;
			this.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}