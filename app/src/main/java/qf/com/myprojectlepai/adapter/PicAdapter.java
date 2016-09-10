package qf.com.myprojectlepai.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import qf.com.myprojectlepai.Bean.zixunBean.picBean.TuShangdata;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.myActivity.PicShowActivity;
import qf.com.myprojectlepai.utils.DownloadUtils;
import qf.com.myprojectlepai.utils.LruUtils;
import qf.com.myprojectlepai.utils.SecCacheImageRead;
import qf.com.myprojectlepai.utils.SecCacheImageSave;

/**
 * Created by Administrator on 16-9-6.
 */
public class PicAdapter extends BaseAdapter{

    Context context;
    List<TuShangdata> list;
    File file;
    File[] files;
    LruUtils lruUtils;
    Handler handler=new Handler(){};
    Intent intent;
    ExecutorService executor= Executors.newFixedThreadPool(20);
    public PicAdapter(Context context, List<TuShangdata> list) {
        this.context = context;
        this.list = list;
        lruUtils=new LruUtils();
        lruUtils.initLru();
    }

    @Override
    public int getCount() {
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
        final PicViewHolder viewHolder;

       if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_grid_list,null);
            viewHolder=new PicViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imgId);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.textId);
            convertView.setTag(viewHolder);
       }else{
           viewHolder= (PicViewHolder) convertView.getTag();
       }
        viewHolder.textView.setText(list.get(position).getTitle());
        final String imageUrl=list.get(position).getPic_url();
        final String weburl=list.get(position).getWeb_url();

        final String detalUrl=list.get(position).getDetail_url();
        //Log.d("fcr","weburl=="+weburl);
        Uri uri=Uri.parse(imageUrl);




        final String picUrlString=uri.getLastPathSegment();
        viewHolder.imageView.setTag(picUrlString);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (viewHolder.imageView.getTag()!=null&&viewHolder.imageView.getTag().equals(picUrlString)){
//                    Uri webUri=Uri.parse(weburl);
//                    intent=new Intent(context, WebViewActivity.class);
//                    //intent=new Intent(Intent.ACTION_VIEW,webUri);
//                    intent.putExtra("web_url",weburl);
//                    Log.d("web","==webUri="+weburl);
//                    context.startActivity(intent);
//                    //intent=new Intent(Intent.ACTION_VIEW,webUri);
//                    //context.startActivity(intent);
//                }
                    if (viewHolder.imageView.getTag()!=null&&viewHolder.imageView.getTag().equals(picUrlString)){
                        intent=new Intent(context, PicShowActivity.class);
                        intent.putExtra("pic_show_url",detalUrl);
                        context.startActivity(intent);
                    }

            }
        });



        file=new File("data/data/qf.com.myprojectlepai/cache/image");
        if (file.exists()){
            files=file.listFiles();
            //Log.d("fcr","==picadapter=="+files.length);
        }
        if (lruUtils.getImage(imageUrl)!=null&&viewHolder.imageView.getTag().equals(imageUrl)){
            Bitmap bitmap=lruUtils.getImage(imageUrl);
            viewHolder.imageView.setImageBitmap(bitmap);
        }else if(files!=null&&DownloadUtils.fileList(files,picUrlString)){
            Bitmap bitmap=SecCacheImageRead.readBitmap(context,picUrlString);
            viewHolder.imageView.setImageBitmap(bitmap);
            //Log.d("fcr","==在二级缓存中找数据==");
        }else{
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    final Bitmap bitmap=DownloadUtils.getImage(imageUrl);
                    lruUtils.saveImage(imageUrl,bitmap);
                    SecCacheImageSave.saveBitmap(picUrlString,bitmap);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (viewHolder.imageView.getTag()!=null && viewHolder.imageView.getTag().equals(picUrlString)){
                                viewHolder.imageView.setImageBitmap(bitmap);
                            }
                        }
                    });
                }
            });
        }

        return convertView;
    }
    class PicViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
