package cn.itcast.androidl.taransitions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

import cn.itcast.androidl.R;


public class FadeActivity extends Activity{

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		Fade fade = new Fade();
		fade.setDuration(1000);
		getWindow().setEnterTransition(fade);
		getWindow().setExitTransition(fade);

		setContentView(R.layout.activity_animation);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public void onFinish(View view) {
		finishAfterTransition();
	}


}
