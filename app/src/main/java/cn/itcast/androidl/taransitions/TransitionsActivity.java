package cn.itcast.androidl.taransitions;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.*;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.itcast.androidl.R;

public class TransitionsActivity extends Activity implements View.OnClickListener {

	private Button bt1,bt2,bt3,bt4;
	private ImageView ivImage;
	private TextView tvText;

	private Activity activity;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = TransitionsActivity.this;

		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

		setContentView(R.layout.activity_transitions);

		bt1 = (Button) findViewById(R.id.bt1);
		bt2 = (Button) findViewById(R.id.bt2);
		bt3 = (Button) findViewById(R.id.bt3);
		bt4 = (Button) findViewById(R.id.bt4);

		ivImage = (ImageView) findViewById(R.id.iv_image);
		tvText = (TextView) findViewById(R.id.tv_text);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt1:		// 爆炸
				setWindowTransition(new Explode());
				startAcitivityWithBundle(ExplodeActivity.class);
				return;

			case R.id.bt2:		// 淡入淡出
				setWindowTransition(new Fade());
				startAcitivityWithBundle(FadeActivity.class);
				return;

			case R.id.bt3:		// 滑动
				setWindowTransition(new Slide());
				startAcitivityWithBundle(SlideActivity.class);
				return;

			case R.id.bt4:		// 共享元素
				startActivityShareTransition();
				return;
		}
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void startAcitivityWithBundle(Class cls) {
		Intent intent = new Intent(activity, cls);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity);
		startActivity(intent, options.toBundle());
	}


	private void setWindowTransition(Transition transition) {
		transition.setDuration(1000);
		getWindow().setEnterTransition(transition);    // 启动时的动画
		getWindow().setExitTransition(transition);     // 进入其它界面时的动画
		getWindow().setReenterTransition(transition);  // 从其它界面返回时的动画
		getWindow().setReturnTransition(transition);   // 退出时的动画
	}

	@SuppressLint("NewApi")
	private void startActivityShareTransition() {
		getWindow().setEnterTransition(null);
		getWindow().setExitTransition(null);
		getWindow().setReturnTransition(null);
		getWindow().setReenterTransition(null);

		Intent intent = new Intent(this, ShareActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, ivImage, "share_01");
		startActivity(intent, options.toBundle());
	}

}
