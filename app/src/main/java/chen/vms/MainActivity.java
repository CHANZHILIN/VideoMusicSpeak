package chen.vms;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import chen.baselib.widget.NoScrollViewPager;
import chen.module_mine.MineFragment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private SparseArray<Fragment> mFragments = new SparseArray<>();
    private NoScrollViewPager mNoScrollViewPager;

    private RadioGroup rgMainGroup;

    private CheckBox image;
    private int[] rbIdList = {R.id.main_tab, R.id.video_tab, R.id.voice_tab, R.id.mine_tab};
    private long mFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoScrollViewPager = (NoScrollViewPager) findViewById(R.id.scrollViewPager);
        rgMainGroup = (RadioGroup) findViewById(R.id.tabs_rg);
        image = (CheckBox) findViewById(R.id.iv_try);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f, 0f);
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                objectAnimator.start();
            }
        });

        setFragments();

//        BottomNavigationView navigation = findViewById(R.id.navigationview);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

      /*  findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Bundle bundle = new Bundle();
                bundle.putString("appId","123456");
                bundle.putString("cartId","654321");
                ARouter.getInstance().build(Constants.LOGIN_PATH)
                        .withBundle("bundle",bundle)
                        .navigation();
            }
        });*/
    }

    private void setFragments() {
        mFragments.put(0, MineFragment.newInstance("main"));
        mFragments.put(1, MineFragment.newInstance("video"));
        mFragments.put(2, MineFragment.newInstance("voice"));
        mFragments.put(3, MineFragment.newInstance("mine"));
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }
        };

        rgMainGroup.setOnCheckedChangeListener(this);

        mNoScrollViewPager.setAdapter(adapter);
        mNoScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                for (int id : rbIdList) {
                    if (position == i) {
                        rgMainGroup.check(id);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mNoScrollViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int i = 0;
        for (int id : rbIdList) {
            if (id == checkedId) {
                mNoScrollViewPager.setCurrentItem(i, true);
            }
            i++;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mFirstTime > 2000) {
                mFirstTime = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再点一次退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
