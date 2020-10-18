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
    /** ��ʕ`��p View */
    DrawColor view;
    /** Timer �����p�̃n���h�� */
    android.os.Handler handler = new android.os.Handler();
    /** Activity���������ꂽ���ɌĂ΂�� */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�^�C�g���o�[���\���ɂ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 // View �̐ݒ�
        view = new DrawColor(getApplication());
		setContentView(view);
		
		new CountDownTimer(20000,1000){
            // �J�E���g�_�E������
            public void onTick(long millisUntilFinished) {
            }
            // �J�E���g��0�ɂȂ������̏���
            public void onFinish() {
            	stop = 1;
            	view.Result();
            	//finish();
            }
        }.start();
		
		 // Timer �̐ݒ������
        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                    	if (stop == 0) {
                        view.invalidate();//�ĕ`��
                    	}
                    }
                });
            }
        }, 0, 250);
    }
}