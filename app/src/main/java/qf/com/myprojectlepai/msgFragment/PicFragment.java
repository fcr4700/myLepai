package qf.com.myprojectlepai.msgFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.adapter.TuShangViewPagerAdapter;
import qf.com.myprojectlepai.fragment.zixunFragment.PicPersonFragment;

/**
 * Created by Administrator on 16-9-6.
 */
public class PicFragment extends Fragment{

    ViewPager viewPager;
    List<Fragment> fragmentList;
    TuShangViewPagerAdapter TSadapter;
    RadioGroup radioGroup;
    TextView textView;

    public PicFragment getInstance(String name){
        PicFragment fragment=new PicFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tushang,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
        viewPager= (ViewPager) view.findViewById(R.id.viewPagerTSId);
        TSadapter=new TuShangViewPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(TSadapter);

        textView= (TextView) view.findViewById(R.id.actionTSId);
        int widthPix=getResources().getDisplayMetrics().widthPixels;
        textView.setWidth(widthPix/4);

        radioGroup= (RadioGroup) view.findViewById(R.id.radioGroupTs);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.renxiang:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.fengguang:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.shengtai:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.shuma:
                        viewPager.setCurrentItem(3);
                        break;

                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams params=
                        (LinearLayout.LayoutParams) textView.getLayoutParams();
                params.leftMargin=(int)((position+positionOffset)*textView.getWidth());
                textView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton;
                for (int i = 0; i <radioGroup.getChildCount() ; i++) {
                    radioButton= (RadioButton) radioGroup.getChildAt(i);
                    if (i==position){
                        radioButton.setTextColor(Color.WHITE);
                    }else{
                        radioButton.setTextColor(Color.parseColor("#dedede"));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        fragmentList=new ArrayList<>();
        //TODO
        fragmentList.add(new PicPersonFragment().getPicInstance("RENXIANG"));
        fragmentList.add(new PicPersonFragment().getPicInstance("FENGGUANG"));
        fragmentList.add(new PicPersonFragment().getPicInstance("SHENGTAI"));
        fragmentList.add(new PicPersonFragment().getPicInstance("SHUMA"));

        //fragmentList.add()
    }
}
