package chen.module_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import chen.baselib.Constants;

@Route(path = Constants.LOGIN_PATH)
public class LoginActivity extends AppCompatActivity {
    private String appId;
    private String cartId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            appId = bundle.getString("appId");
            cartId = bundle.getString("cartId");
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "appId:" + appId + ",cartId:" + cartId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
