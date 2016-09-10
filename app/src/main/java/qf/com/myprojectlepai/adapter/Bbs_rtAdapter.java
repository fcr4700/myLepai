package qf.com.myprojectlepai.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import qf.com.myprojectlepai.Bean.zixunBean.bbs_rt.Bbs_Rt;
import qf.com.myprojectlepai.R;

/**
 * Created by Administrator on 16-9-8.
 */
public class Bbs_rtAdapter extends BaseAdapter{
    Context context;
    List<Bbs_Rt.ListBean> list;

    public Bbs_rtAdapter(Context context, List<Bbs_Rt.ListBean> list) {
        this.context = context;
        this.list = list;
        Log.d("bbs","=bbsAdapter=构造方法=");
    }

    @Override
    public int getCount() {
        Log.d("bbs","==getCount=="+list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RtViewHolder viewHolder;
        Log.d("bbs","==getView==");
        if (convertView==null){
            viewHolder=new RtViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.bbs_item_rt_list,null);
            viewHolder.topT= (TextView) convertView.findViewById(R.id.bbs_topText);
            viewHolder.leftBottomT= (TextView) convertView.findViewById(R.id.bbs_leftBottm);
            viewHolder.centerT= (TextView) convertView.findViewById(R.id.bbs_centerBottom);
            viewHolder.rightBoT= (TextView) convertView.findViewById(R.id.bbs_rightBottom);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (RtViewHolder) convertView.getTag();
        }
        viewHolder.topT.setText(list.get(position).getTitle());
        viewHolder.leftBottomT.setText(list.get(position).getAuthor());
        viewHolder.centerT.setText("帖子:"+list.get(position).getViews());
        viewHolder.rightBoT.setText("回复:"+list.get(position).getReply());

        return convertView;
    }

    class RtViewHolder{
        TextView topT,leftBottomT,centerT,rightBoT;
    }
}
