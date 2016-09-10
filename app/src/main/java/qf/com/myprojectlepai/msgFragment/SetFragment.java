package qf.com.myprojectlepai.msgFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.adapter.SetViewPagerAdapter;
import qf.com.myprojectlepai.fragment.zixunFragment.SetContentFragment;

/**
 * Created by Administrator on 16-9-6.
 */
public class SetFragment extends Fragment{
    SetViewPagerAdapter adapter;
    List<Fragment> list;
    //SetContentFragment fragment;
    ViewPager viewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_viewpager,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager= (ViewPager) view.findViewById(R.id.viewPagerSetId);
        //fragment=new SetContentFragment();
        list=new ArrayList<>();
        list.add(new SetContentFragment().getInstance("name"));
        adapter=new SetViewPagerAdapter(getActivity().getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
    }
}
