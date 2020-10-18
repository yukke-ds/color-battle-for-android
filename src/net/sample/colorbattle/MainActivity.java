package net.sample.colorbattle;

import java.util.Timer;
import java.util.TimerTask;

import net.sample.colorbattle.DrawColor;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {
	int stop = 0;
    /** 画面描画用 View */
    DrawColor view;
    /** Timer 処理用のハンドラ */
    android.os.Handler handler = new android.os.Handler();
    /** Activityが生成された時に呼ばれる */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//タイトルバーを非表示にする
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 // View の設定
        view = new DrawColor(getApplication());
		setContentView(view);
		
		new CountDownTimer(20000,1000){
            // カウントダウン処理
            public void onTick(long millisUntilFinished) {
            }
            // カウントが0になった時の処理
            public void onFinish() {
            	stop = 1;
            	view.Result();
            	//finish();
            }
        }.start();
		
		 // Timer の設定をする
        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                    	if (stop == 0) {
                        view.invalidate();//再描画
                    	}
                    }
                });
            }
        }, 0, 250);
    }
}