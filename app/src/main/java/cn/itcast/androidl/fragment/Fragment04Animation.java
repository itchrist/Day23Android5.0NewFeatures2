package cn.itcast.androidl.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import cn.itcast.androidl.R;
import cn.itcast.androidl.taransitions.TransitionsActivity;

/**
 * 03.全新动画
 */
public class Fragment04Animation extends BaseFragment implements View.OnClickListener {

	Button bt1,bt2,transitions;
	View curvedView, vectorView;

	protected View initView() {
		View view = View.inflate(getActivity(), R.layout.fragment04_animation,null);
		bt1 = (Button) view.findViewById(R.id.bt1);
		bt2 = (Button) view.findViewById(R.id.bt2);
		curvedView = view.findViewById(R.id.curved);
		vectorView = view.findViewById(R.id.vector_anim);
		transitions = (Button) view.findViewById(R.id.transitions);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		curvedView.setOnClickListener(this);
		vectorView.setOnClickListener(this);
		transitions.setOnClickListener(this);
		return view;
	}

	@Override
	public String getUrl() {
		return "file:///android_asset/animation.html";
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt1:		// 揭示动画
				Animator animator1 = ViewAnimationUtils.createCircularReveal(bt1,
						bt1.getWidth() / 2, bt1.getHeight() / 2,
						bt1.getWidth(), 0);
				animator1.setInterpolator(new LinearInterpolator());
				animator1.setDuration(3000);
				animator1.start();
				break;

			case R.id.bt2:
				Animator animator2 = ViewAnimationUtils.createCircularReveal(bt2,
						0, bt2.getHeight(), 0, (float) Math.hypot(bt2.getWidth(), bt2.getHeight()));
				animator2.setDuration(3000);
				animator2.start();
				break;

			case R.id.curved:
				curved();
				break;

			case R.id.vector_anim:
				vector();
				break;
			case R.id.transitions:
				transitions();
				break;
		}
	}


	private int dp2px(int dp) {
		return (int) (getResources().getDisplayMetrics().density * dp + 0.5f);
	}

	// 路径动画
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void curved(){
		Path path = new Path();	// 创建路径
		path.moveTo(curvedView.getX(), curvedView.getY());

		path.lineTo(dp2px(100), dp2px(100)); // a点
		path.lineTo(dp2px(200), dp2px(100)); // b点
		path.lineTo(dp2px(200), dp2px(200)); // c点
		path.lineTo(dp2px(100), dp2px(200)); // d点
		path.lineTo(dp2px(100), dp2px(100)); // a点

		path.quadTo(dp2px(150), dp2px(150), dp2px(200), dp2px(100)); // a点
		path.quadTo(dp2px(150), dp2px(150), dp2px(200), dp2px(200)); // c点
		path.quadTo(dp2px(150), dp2px(150), dp2px(100), dp2px(200)); // d点
		path.quadTo(dp2px(150), dp2px(150), dp2px(100), dp2px(100)); // a点

		path.lineTo(curvedView.getX(), curvedView.getY());

		ObjectAnimator mAnimator = ObjectAnimator.ofFloat(curvedView, View.X, View.Y, path);
		mAnimator.setDuration(8000);
		mAnimator.start();
	}

	// 矢量图动画
	public void vector(){
		Drawable drawable = vectorView.getBackground();
		if (drawable instanceof Animatable) {
			((Animatable) drawable).start();
		}
	}

	// 转场动画
	public void transitions(){
		Intent intent = new Intent(getActivity(),TransitionsActivity.class);
		getActivity().startActivity(intent);
	}
}
