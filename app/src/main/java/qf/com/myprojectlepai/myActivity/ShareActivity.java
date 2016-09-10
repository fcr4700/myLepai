package qf.com.myprojectlepai.myActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.myprojectlepai.R;

/**
 * Created by Administrator on 16-9-7.
 */
public class ShareActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    List<Map<String,Object>> list;
    SimpleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity_share);
        listView= (ListView) findViewById(R.id.set_listviewId);
        list=new ArrayList<>();
        initData();
        adapter=new SimpleAdapter(getApplicationContext(),list,R.layout.set_item_list_share,
        new String[]{"image","name"},new int[]{R.id.imageId,R.id.nameId} );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void initData() {
        HashMap<String, Object> map;
        for (int i = 0; i < 13; i++) {
            map=new HashMap<String, Object>();
            map.put("image", images[i]);
            map.put("name", "尚未授权");
            //map.put("xx", "x"+i);
            //map.put("cb",chioce[0]);
            list.add(map);
        }
    }

    int chioce[]={
            R.drawable.cb_uncheck

    };
    int images[]={
            R.drawable.logo_qq,R.drawable.logo_qzone,R.drawable.logo_sinaweibo,
        R.drawable.logo_douban,R.drawable.logo_dropbox,R.drawable.logo_email,R.drawable.logo_evernote,
            R.drawable.logo_facebook,R.drawable.logo_flickr,R.drawable.logo_foursquare,R.drawable.logo_googleplus,
            R.drawable.logo_instagram,R.drawable.logo_kaixin,R.drawable.logo_linkedin,
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
    }
}
