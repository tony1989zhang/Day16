package org.zcn.day16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String TAG = "MainActivity";
    /**
     * Hello World!
     */
    private TextView mTextView;
    /**
     * 请求网络
     */
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
    }
    public void getNet(View view) {

        post();
//        get();
    }

    private void post() {

        OkHttpClient client = new OkHttpClient();
      // RequestBody body = new RequestBody.create(JSON, "{'username':'zhangsan','password':'123456'}");
        FormBody formBody = new FormBody
                .Builder()
                .add("username","zhangsan")
                .add("password","123456")
                .build();
        Request request = new Request.Builder().url("http://59.57.63.118:8103/OkHttpDemo/servlet/OkHttpServlet").post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.e(TAG, "onResponse: " +string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("response:" + string);
                    }
                });

            }
        });
    }

    private void get() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url("http://59.57.63.118:8103/OkHttpDemo/servlet/OkHttpServlet")
                    .get()
                    .build();

        client.newCall(request).enqueue(new Callback() {
            //请求失败调用
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //请求成功调用
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.e(TAG, "onResponse: " +string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            mTextView.setText("response:" + string);
                    }
                });

            }
        });
    }


}
