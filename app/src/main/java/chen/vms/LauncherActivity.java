package chen.vms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;

import chen.baselib.Constants;
import chen.vms.widget.ClockView;
import chen.vms.widget.SpiderWebView;


public class LauncherActivity extends AppCompatActivity implements Animation.AnimationListener {

    private static final int ANIM_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);
        FrameLayout frameLayout = findViewById(R.id.fl_main);
        SpiderWebView spiderWebView = findViewById(R.id.spider);
        ClockView clockView = findViewById(R.id.clockview);
        clockView.setTime(ANIM_TIME / 250);
        clockView.start();
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(ANIM_TIME * 4);
        aa.setAnimationListener(this);
        frameLayout.startAnimation(aa);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIM_TIME);
        spiderWebView.startAnimation(sa);
        clockView.setOnSkipClick(new ClockView.OnSkipClickListener() {
            @Override
            public void onSkip() {
                ARouter.getInstance().build(Constants.MAIN_ACTIVITY_PATH)
                        .navigation();  //跳过
                LauncherActivity.this.finish();
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        ARouter.getInstance().build(Constants.MAIN_ACTIVITY_PATH)
                .navigation();  //跳过
        LauncherActivity.this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
