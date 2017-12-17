package cn.itcast.androidl.fragment;


import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {

	public static final int FRAGEMENT_STYLE = 0;
	public static final int FRAGEMENT_SHADOW = 1;
	public static final int FRAGEMENT_DRAWABLE = 2;
	public static final int FRAGEMENT_ANIMATION = 3;
	public static final int FRAGEMENT_WIDGET = 4;

	private static Map<Integer, BaseFragment> mFragmentCache = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int position){
		BaseFragment fragment = mFragmentCache.get(position);
		if (fragment == null) {
			switch (position) {
				case FRAGEMENT_STYLE:
					fragment = new Fragment01Style();
					break;
				case FRAGEMENT_SHADOW:
					fragment = new Fragment02Shadow();
					break;
				case FRAGEMENT_DRAWABLE:
					fragment = new Fragment03Drawable();
					break;
				case FRAGEMENT_ANIMATION:
					fragment = new Fragment04Animation();
					break;
				case FRAGEMENT_WIDGET:
					fragment = new Fragment05Widget();
					break;
			}
			mFragmentCache.put(position,fragment);
		}
		return fragment;
	}

	public static void clear(){
		mFragmentCache.clear();
	}
}
