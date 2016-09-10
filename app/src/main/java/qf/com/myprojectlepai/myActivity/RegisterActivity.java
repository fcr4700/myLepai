package qf.com.myprojectlepai.myActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import qf.com.myprojectlepai.R;

/**
 * Created by Administrator on 16-9-7.
 */
public class RegisterActivity extends AppCompatActivity{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText nameEdit,passEdit;
    String name,password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_register_activity);
        nameEdit= (EditText) findViewById(R.id.edit_name);
        passEdit= (EditText) findViewById(R.id.edit_pass);
        preferences=getSharedPreferences("user_info",MODE_PRIVATE);
        editor=preferences.edit();

    }

    public void onClick(View view){
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
            editor.putString("name",name);
            editor.putString("password",password);
            editor.commit();
            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();

        }
        Intent intent=new Intent(this,LogActivity.class);
        startActivity(intent);
    }
}
