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
import qf.com.myprojectlepai.adapter.BbsViewPagerAdapter;
import qf.com.myprojectlepai.fragment.zixunFragment.BbsContentFragment;

/**
 * Created by Administrator on 16-9-7.
 */
public class BbsFragment extends Fragment{

    ViewPager viewPager;

    List<Fragment> list;
    BbsViewPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bbs_viewpager,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager= (ViewPager) view.findViewById(R.id.bbs_viewPagerId);
        list=new ArrayList<>();
        list.add(new BbsContentFragment().getInstace("bbs"));


        adapter=new BbsViewPagerAdapter(getActivity().getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);


    }
}
