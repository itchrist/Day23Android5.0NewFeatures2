package cn.itcast.androidl.taransitions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.Window;

import cn.itcast.androidl.R;

public class ExplodeActivity extends Activity {

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

		Transition transition = new Explode();
		transition.setDuration(1000);
		getWindow().setEnterTransition(transition);
		getWindow().setExitTransition(transition);
		getWindow().setReenterTransition(transition);
		getWindow().setReturnTransition(transition);

		setContentView(R.layout.activity_animation);
		setTitle("转场动画");
	}

	public void onFinish(View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			finishAfterTransition();
		} else {
			finish();
		}
	}
}
