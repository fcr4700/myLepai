package qf.com.myprojectlepai.myActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import qf.com.myprojectlepai.R;

/**
 * Created by Administrator on 16-9-7.
 */
public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    Button regisBtn,loginBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText nameEditText,passwordEditText;
    String name,password;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity_login);

        initView();
        preferences=getSharedPreferences("user_info",MODE_PRIVATE);
        editor=preferences.edit();

    }

    private void initView() {
        regisBtn= (Button) findViewById(R.id.regisId);
        loginBtn= (Button) findViewById(R.id.loginId);
        nameEditText= (EditText) findViewById(R.id.edit_name);
        passwordEditText= (EditText) findViewById(R.id.edit_pass);

        regisBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regisId:
                intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.loginId:
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    if (name.equals(preferences.getString("name","fcr123"))&&password.equals(preferences.getString("password","1234567"))){
                        //TODO

                        Toast.makeText(getApplicationContext(),"bingo",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"fialed",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}
