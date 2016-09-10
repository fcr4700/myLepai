package qf.com.myprojectlepai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.io.File;

import qf.com.myprojectlepai.msgFragment.BbsAllFragment;
import qf.com.myprojectlepai.msgFragment.PicFragment;
import qf.com.myprojectlepai.msgFragment.SetAllFragment;
import qf.com.myprojectlepai.msgFragment.ZiXunFragment;
import qf.com.myprojectlepai.myActivity.Welcome;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    Intent intent;
    FragmentManager manager;
    FragmentTransaction transaction;
    RadioGroup radioGroup;
    Fragment fragment1,fragment2,fragment3,fragment4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=new Intent(this, Welcome.class);
        startActivity(intent);

        setContentView(R.layout.activity_main);
        initData();
        initView();

        radioGroup.setOnCheckedChangeListener(this);
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.fragmentId,fragment1);
        transaction.commit();
    }

    private void initView() {
        radioGroup= (RadioGroup) findViewById(R.id.mainRadioTabs);
    }

    private void initData() {
        fragment1=new ZiXunFragment();
        fragment2=new PicFragment();
        fragment3=new BbsAllFragment();
        fragment4=new SetAllFragment();
    }

    @Override
    protected void onDestroy() {
        File file=new File(getCacheDir() + File.separator + "image");
        File[] files=file.listFiles();
        for (File ff:files) {
            ff.delete();
        }
        file.delete();
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_Msg:
                Fragment fragmentZx=fragment1;
                transaction=manager.beginTransaction();
                transaction.replace(R.id.fragmentId,fragmentZx);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.main_Pic:
                Fragment fragmentPic=fragment2;
                transaction=manager.beginTransaction();
                transaction.replace(R.id.fragmentId,fragmentPic);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.main_bbs:
                Fragment fragmentBbs=fragment3;
                transaction=manager.beginTransaction();
                transaction.replace(R.id.fragmentId,fragmentBbs);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.main_Set:
                Fragment fragmentSet=fragment4;
                transaction=manager.beginTransaction();
                transaction.replace(R.id.fragmentId,fragmentSet);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    public void switchContent(Fragment to) {
        if ( fragment4!= to) {
            transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(fragment4).add(R.id.fragmentId, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(fragment4).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            fragment4 = to;//将当前fragment赋予mContent
        }
    }
}
