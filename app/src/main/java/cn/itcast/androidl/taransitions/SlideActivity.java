package cn.itcast.androidl.taransitions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;

import cn.itcast.androidl.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SlideActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		Slide slide = new Slide();
		slide.setDuration(1000);
		getWindow().setEnterTransition(slide);
		getWindow().setExitTransition(slide);

		setContentView(R.layout.activity_animation);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public void onFinish(View view) {
		finishAfterTransition();
	}
}
