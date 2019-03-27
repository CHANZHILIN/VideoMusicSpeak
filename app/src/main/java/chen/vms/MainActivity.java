package chen.vms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import chen.baselib.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
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
        });
    }
}
