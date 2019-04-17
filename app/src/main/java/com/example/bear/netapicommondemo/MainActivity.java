package com.example.bear.netapicommondemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bear.netapicommondemo.adapter.SpinnerAdapter;
import com.example.bear.netapicommondemo.listener.SpinnerSelectListener;
import com.example.bear.netapicommondemo.netapi.HttpCallback;
import com.example.bear.netapicommondemo.netapi.HttpHelper;
import com.example.bear.netapicommondemo.view.CustomSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String url = "http://ip.taobao.com/service/getIpInfo.php";
    private static final String[] permissionS ={Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.INTERNET};
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @BindView(R.id.ip_et)
    EditText ipEditText;
    @BindView(R.id.show_pop)
    TextView show_pop;

    private SpinnerAdapter listAdapter;
    private CustomSpinner listPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listPopupWindow = null;
        requestPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        show_pop.post(new Runnable() {
            @Override
            public void run() {
                bindPopupWindow();
            }
        });

    }

    private void bindPopupWindow(){
        if (listPopupWindow == null) {
            String[] datas = {"条目0","条目1","条目2","条目3","条目4","条目0","条目1","条目2",
                    "条目3","条目4","条目1","条目2","条目3","条目4"};
            List list =Arrays.asList(datas);
            List arrayList = new ArrayList(list);
            listAdapter = new SpinnerAdapter(null,this);
            listPopupWindow = new CustomSpinner(this);
            listPopupWindow.bindAnchorView(show_pop, new SpinnerSelectListener() {
                @Override
                public void onSelect(Object o) {
                    show_pop.setText(o.toString());
                }
            });
            listPopupWindow.setAdapter(listAdapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,permissionS[0])!= PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionS,1);
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        listPopupWindow.removeCustomSpinner();
    }

    //    @OnClick(R.id.show_pop)
//    public void showListPopupwindow(View view){
//        String[] datas = {/*"条目0","条目1","条目2","条目3","条目4","条目0","条目1","条目2",
//                "条目3","条目4","条目1","条目2","条目3","条目4"*/};
//        ListAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,datas);
//        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
//        int left = view.getLeft();
//        int top = view.getTop();
//        listPopupWindow.setHeight(500);
//        listPopupWindow.setAdapter(adapter);
//        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                listPopupWindow.dismiss();
//                show_pop.setText(item);
//            }
//        });
//        listPopupWindow.setAnchorView(view);
//
//        listPopupWindow.show();
//    }

    void postDemo(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("ip",ipEditText.getText().toString());
        HttpHelper.getInstance().post(url, params, new HttpCallback<String>() {
            @Override
            public void onSuccess(String s) {
                mHandler.post(() -> Toast.makeText(MainActivity.this,"code " +s,Toast.LENGTH_LONG).show());

            }
        });
    }
    @OnClick(R.id.btn)
    void updateData(){
        String[] datas = {"条目0","条目1","条目2","条目3","条目4","条目0"};
        List list =Arrays.asList(datas);
        List arrayList = new ArrayList(list);
        listAdapter.updateAll(arrayList);
    }
}
