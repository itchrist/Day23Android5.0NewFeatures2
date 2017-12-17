package cn.itcast.androidl.fragment;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.itcast.androidl.R;

/**
 * 02.阴影,轮廓和裁剪
 */
public class Fragment02Shadow extends BaseFragment {

    TextView tvElevation, tvElevation02, tvCircle, tvCut1, tvCut2, tvCut3, tvCut4, tvCut5;

    EditText et1, et2;
    Button bt;

    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment02_shadow, null);

        changeZ(view);  // 改变阴影

        outLine(view);  // 轮廓

        cut(view);      // 裁剪

        return view;
    }

    private void changeZ(View view) {
        tvElevation = (TextView) view.findViewById(R.id.tv_01);
        tvElevation02 = (TextView) view.findViewById(R.id.tv_02);

        et1 = (EditText) view.findViewById(R.id.et_01);
        et2 = (EditText) view.findViewById(R.id.et_02);

        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String str1 = et1.getText().toString();
                String str2 = et2.getText().toString();

                if (TextUtils.isEmpty(str1)) {
                    str1 = "0";
                }

                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }

                int z1 = Integer.valueOf(str1);
                int z2 = Integer.valueOf(str2);

                tvElevation.setText("阴影：" + z1 + "dp");
                tvElevation02.setText("阴影：" + z2 + "dp");

                tvElevation.setElevation(z1);
                tvElevation02.setElevation(z2);
            }
        });
    }

    /**
     * Part05 (2) 代码设置轮廓
     *
     * @param view
     */
    private void outLine(View view) {
        tvCircle = (TextView) view.findViewById(R.id.tv_circle_1);

        // 代码设置轮廓
        tvCircle.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                // 可以指定圆形，矩形，圆角矩形，path
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
    }


    /**
     * Part06:  裁剪
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void cut(View view) {
        tvCut1 = (TextView) view.findViewById(R.id.cut1);
        tvCut2 = (TextView) view.findViewById(R.id.cut2);
        tvCut3 = (TextView) view.findViewById(R.id.cut3);
        tvCut4 = (TextView) view.findViewById(R.id.cut4);
        tvCut5 = (TextView) view.findViewById(R.id.cut5);

        // ============图形2=========================
        tvCut2.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                // 三角形轮廓
                Path p = new Path();
                p.moveTo(view.getWidth() / 2, 0);
                p.lineTo(0, view.getHeight());
                p.lineTo(view.getWidth(), view.getHeight());
                p.close();

                outline.setConvexPath(p); // 裁剪成三角形
                tvCut2.setText("矩形剪裁成三角形: " + outline.canClip());
            }
        });
        tvCut2.setClipToOutline(true);


        // ============图形3=========================
        tvCut3.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                // 裁剪成圆形
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
                tvCut3.setText("矩形剪裁成圆形: " + outline.canClip());
            }
        });
        tvCut3.setClipToOutline(true);


        // ============图形4=========================
        tvCut4.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                // 裁剪成圆角
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 15);
                tvCut4.setText("剪裁成圆角矩形: " + outline.canClip());
            }
        });
        tvCut4.setClipToOutline(true);

        // ============图形5=========================
        tvCut5.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                int padding = 15;
                // 指定缩小的轮廓
                outline.setRect(padding, padding,
                        view.getWidth() - padding,
                        view.getHeight() - padding);

                tvCut5.setText("减小矩形轮廓后裁剪: " + outline.canClip());
            }
        });
        tvCut5.setClipToOutline(true);    // 裁剪
    }


    @Override
    public String getUrl() {
        return "file:///android_asset/shadow.html";
    }
}
