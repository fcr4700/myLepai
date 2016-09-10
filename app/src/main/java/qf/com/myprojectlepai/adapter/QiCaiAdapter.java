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

import qf.com.myprojectlepai.Bean.zixunBean.qicai.QiCaiContent;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.WebViewActivity;
import qf.com.myprojectlepai.utils.DownloadUtils;
import qf.com.myprojectlepai.utils.LruUtils;
import qf.com.myprojectlepai.utils.SecCacheImageRead;
import qf.com.myprojectlepai.utils.SecCacheImageSave;

/**
 * Created by Administrator on 16-9-6.
 */
public class QiCaiAdapter extends BaseAdapter{

    File file;
    File[] files;
    List<QiCaiContent> list;
    Context context;
    LruUtils lruUtils;
    Handler handler=new Handler(){};
    Intent intent;

    ExecutorService executor= Executors.newFixedThreadPool(20);
    public QiCaiAdapter(List<QiCaiContent> list, Context context) {
        this.list = list;
        this.context = context;
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
        final ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_grid_list,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imgId);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.textId);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final String imageUrl=list.get(position).getPic_url();
        //TODO webUrl
        final String webUrl=list.get(position).getPic_url();
        Uri uri=Uri.parse(imageUrl);

        final String picurlString=uri.getLastPathSegment();
        viewHolder.imageView.setTag(picurlString);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.textView.setText(list.get(position).getTitle());
        //设置webview的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.imageView.getTag()!=null&&viewHolder.imageView.getTag().equals(picurlString)){
                    Uri webUri=Uri.parse(webUrl);
                    //intent=new Intent(Intent.ACTION_VIEW,webUri);
                    //context.startActivity(intent);
                    intent=new Intent(context, WebViewActivity.class);
                    intent.putExtra("qicai_url",webUrl);
                    context.startActivity(intent);
                }
            }
        });

        file=new File("data/data/qf.com.myprojectlepai/cache/image");//TODO
        if (file.exists()){
            files=file.listFiles();
        }

        if (lruUtils.getImage(imageUrl)!=null&& viewHolder.imageView.getTag().equals(imageUrl)){
            viewHolder.imageView.setImageBitmap(lruUtils.getImage(imageUrl));
        }else if(files!=null&& DownloadUtils.fileList(files,picurlString)){
            Bitmap bitmap= SecCacheImageRead.readBitmap(context,picurlString);
            viewHolder.imageView.setImageBitmap(bitmap);

        }else{
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    final Bitmap bitmap=DownloadUtils.getImage(imageUrl);
                    LruUtils.saveImage(imageUrl,bitmap);
                    SecCacheImageSave.saveBitmap(picurlString, bitmap);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (viewHolder.imageView.getTag()!=null&&viewHolder.imageView.getTag().equals(picurlString)){
                                viewHolder.imageView.setImageBitmap(bitmap);
                            }

                        }
                    });
                }
            });
        }


        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
