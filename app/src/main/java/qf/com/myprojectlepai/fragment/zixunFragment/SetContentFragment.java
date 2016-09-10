package qf.com.myprojectlepai.fragment.zixunFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.myActivity.AboutUsActivity;
import qf.com.myprojectlepai.myActivity.LogActivity;
import qf.com.myprojectlepai.myActivity.ShareActivity;

/**
 * Created by Administrator on 16-9-6.
 */
public class SetContentFragment extends Fragment implements View.OnClickListener {

    RelativeLayout r1,r2,r3,r4,r5,r6,r7,r8;
    Intent intent;
    public SetContentFragment getInstance(String name){
        SetContentFragment fragment=new SetContentFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        r1= (RelativeLayout) view.findViewById(R.id.set_ralatveZ1Id);
        r2= (RelativeLayout) view.findViewById(R.id.set_ralatveZ2Id);
        r3= (RelativeLayout) view.findViewById(R.id.set_ralatveZ3Id);
        r4= (RelativeLayout) view.findViewById(R.id.set_ralatveZ4Id);
        r5= (RelativeLayout) view.findViewById(R.id.set_ralatveZ5Id);
        r6= (RelativeLayout) view.findViewById(R.id.set_ralatveZ6Id);
        r7= (RelativeLayout) view.findViewById(R.id.set_ralatveZ7Id);
        r8= (RelativeLayout) view.findViewById(R.id.set_ralatveZ8Id);

        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        r5.setOnClickListener(this);
        r6.setOnClickListener(this);
        r7.setOnClickListener(this);
        r8.setOnClickListener(this);

        if (getArguments()!=null){
            String name=getArguments().getString("name");
            Log.d("fcr","==");
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set_ralatveZ1Id:
                intent=new Intent(getContext(), LogActivity.class);
                startActivity(intent);
                break;
            case R.id.set_ralatveZ2Id:
                //TODO
                intent=new Intent(getContext(), ShareActivity.class);
                startActivity(intent);
                break;
            case R.id.set_ralatveZ3Id:

                break;
            case R.id.set_ralatveZ4Id:

                break;
            case R.id.set_ralatveZ5Id:

                break;
            case R.id.set_ralatveZ6Id:

                break;
            case R.id.set_ralatveZ7Id:

                break;
            case R.id.set_ralatveZ8Id:
                intent=new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent);
                break;


        }
    }
}
