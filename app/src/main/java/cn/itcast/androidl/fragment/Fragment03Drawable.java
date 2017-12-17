package cn.itcast.androidl.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.TextView;

import cn.itcast.androidl.R;

/**
 * 03.图片和颜色
 */
public class Fragment03Drawable extends BaseFragment{

	protected View initView(){
		View view = View.inflate(getActivity(), R.layout.fragment03_drawable,null);

		View imageview = view.findViewById(R.id.im);

		final TextView v = (TextView) view.findViewById(R.id.v);
		final TextView vd = (TextView) view.findViewById(R.id.vd);
		final TextView vl = (TextView) view.findViewById(R.id.vl);
		final TextView m = (TextView) view.findViewById(R.id.m);
		final TextView md = (TextView) view.findViewById(R.id.md);
		final TextView ml = (TextView) view.findViewById(R.id.ml);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.palette);
		imageview.setBackground(new BitmapDrawable(bitmap));

		// Palette是v7包中的类
		Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
			public void onGenerated(Palette palette) {
				v.setBackgroundColor(palette.getVibrantColor(Color.BLACK));
				vd.setBackgroundColor(palette.getDarkVibrantColor(Color.BLACK));
				vl.setBackgroundColor(palette.getLightVibrantColor(Color.BLACK));
				m.setBackgroundColor(palette.getMutedColor(Color.BLACK));
				md.setBackgroundColor(palette.getDarkMutedColor(Color.BLACK));
				ml.setBackgroundColor(palette.getLightMutedColor(Color.BLACK));
			}
		});
		return view;
	}
	@Override
	public String getUrl() {
		return "file:///android_asset/drawable.html";
	}
}
