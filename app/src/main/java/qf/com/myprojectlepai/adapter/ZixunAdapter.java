package qf.com.myprojectlepai.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
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

import qf.com.myprojectlepai.Bean.zixunBean.GridData;
import qf.com.myprojectlepai.Bean.zixunBean.ListData;
import qf.com.myprojectlepai.R;
import qf.com.myprojectlepai.utils.DownloadUtils;
import qf.com.myprojectlepai.utils.LruUtils;
import qf.com.myprojectlepai.utils.SecCacheImageRead;
import qf.com.myprojectlepai.utils.SecCacheImageSave;

/**
 * Created by Administrator on 16-9-6.
 */
public class ZixunAdapter extends BaseAdapter{

    List<ListData> dataList;
    List<GridData> dataGrid;
    Context context;
    LruUtils lruUtils;
    File file ;
    File[] files;
    Handler handler=new Handler(){};
    ExecutorService executor= Executors.newFixedThreadPool(10);
    public ZixunAdapter(List<ListData> dataList, List<GridData> dataGrid, Context context) {
        this.dataList = dataList;
        this.context = context;
        this.dataGrid = dataGrid;
        lruUtils = new LruUtils();
        lruUtils.initLru();

    }

    @Override
    public int getItemViewType(int position) {
        if ((position)%5==0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return dataList.size()+(dataGrid.size()/2);
    }

    @Override
    public Object getItem(int position) {
        if ((position)%5==0){
            return dataGrid.get(position);
        }else{
            return dataList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final ViewHolder1 viewHolder1;
        if (getItemViewType(position)==1) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.tem_listview, null);
                viewHolder.textViewb = (TextView) convertView.findViewById(R.id.listTextB);
                viewHolder.textViews = (TextView) convertView.findViewById(R.id.listTextS);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.listImage);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final String imagUrl = dataList.get(position-(position/5+1)).getPic_url();
            Uri uri = Uri.parse(imagUrl);
            final String picString = uri.getLastPathSegment();
            viewHolder.imageView.setTag(picString);
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.textViewb.setText(dataList.get(position-(position/5+1)).getTitle());
            viewHolder.textViews.setText(dataList.get(position-(position/5+1)).getDate());
            file = new File("data/data/qf.com.myprojectlepai/cache/image");
            if (file.exists()) {
                files = file.listFiles();
            }
            //一级缓存
            if (lruUtils.getImage(imagUrl) != null && viewHolder.imageView.getTag().equals(imagUrl)) {
                viewHolder.imageView.setImageBitmap(lruUtils.getImage(imagUrl));
                //二级缓存
            } else if (files != null && DownloadUtils.fileList(files, picString)) {

                Bitmap bitmap = SecCacheImageRead.readBitmap(context, picString);
                viewHolder.imageView.setImageBitmap(bitmap);
            } else {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = DownloadUtils.getImage(imagUrl);
                        LruUtils.saveImage(imagUrl, bitmap);
                        SecCacheImageSave.saveBitmap(picString, bitmap);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (viewHolder.imageView.getTag() != null && viewHolder.imageView.getTag().equals(picString)) {
                                    viewHolder.imageView.setImageBitmap(bitmap);
                                }
                            }
                        });
                    }
                });

            }
        }else if (getItemViewType(position)==0) {
            if (convertView == null) {
                viewHolder1 = new ViewHolder1();
                convertView = LayoutInflater.from(context).inflate(R.layout.tem_listview2, null);
                viewHolder1.textViewLeft = (TextView) convertView.findViewById(R.id.listTextleft);
                viewHolder1.textViewRight = (TextView) convertView.findViewById(R.id.listTextright);
                viewHolder1.imageViewLeft = (ImageView) convertView.findViewById(R.id.listImageleft);
                viewHolder1.imageViewRight= (ImageView) convertView.findViewById(R.id.listImageright);
                convertView.setTag(viewHolder1);
            } else {
                viewHolder1 = (ViewHolder1) convertView.getTag();
            }
            Log.d("fcr","dataGrid.size======"+dataGrid.size());
            final String imagUrl = dataGrid.get((position/5)*2).getPic_url();
            final String imagUrl2=dataGrid.get((position/5)*2+1).getPic_url();
            Uri uri = Uri.parse(imagUrl);
            Uri uri2=Uri.parse(imagUrl2);
            final String picString = uri.getLastPathSegment();//获取最后一节
            final String picString2=uri2.getLastPathSegment();
            viewHolder1.imageViewLeft.setTag(picString);
            viewHolder1.imageViewRight.setTag(picString2);
            viewHolder1.imageViewLeft.setImageResource(R.mipmap.ic_launcher);
            viewHolder1.imageViewRight.setImageResource(R.mipmap.ic_launcher);
            viewHolder1.textViewLeft.setText(dataGrid.get((position/5)*2).getTitle());
            viewHolder1.textViewRight.setText(dataGrid.get((position/5)*2+1).getTitle());
            file = new File("data/data/qf.com.myprojectlepai/cache/image");
            if (file.exists()) {
                files = file.listFiles();
            }
            //一级缓存
            if (lruUtils.getImage(imagUrl) != null && viewHolder1.imageViewLeft.getTag().equals(imagUrl)
                    && lruUtils.getImage(imagUrl2) != null  && viewHolder1.imageViewRight.getTag().equals(imagUrl2)) {
                viewHolder1.imageViewLeft.setImageBitmap(lruUtils.getImage(imagUrl));
                viewHolder1.imageViewRight.setImageBitmap(lruUtils.getImage(imagUrl2));
                //二级缓存
            } else if (files != null && DownloadUtils.fileList(files, picString)
                    && DownloadUtils.fileList(files,picString2)) {

                Bitmap bitmap = SecCacheImageRead.readBitmap(context, picString);
                Bitmap bitmap1=SecCacheImageRead.readBitmap(context,picString2);
                viewHolder1.imageViewLeft.setImageBitmap(bitmap);
                viewHolder1.imageViewRight.setImageBitmap(bitmap1);
            } else {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap= DownloadUtils.getImage(imagUrl);
                        final Bitmap bitmap2  = DownloadUtils.getImage(imagUrl2);
                        LruUtils.saveImage(imagUrl, bitmap);
                        LruUtils.saveImage(imagUrl2,bitmap2);
                        SecCacheImageSave.saveBitmap(picString, bitmap);
                        SecCacheImageSave.saveBitmap(picString2, bitmap2);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (viewHolder1.imageViewLeft.getTag() != null && viewHolder1.imageViewLeft.getTag().equals(picString)
                                        && viewHolder1.imageViewRight.getTag() != null && viewHolder1.imageViewRight.getTag().equals(picString2)    ) {
                                    viewHolder1.imageViewLeft.setImageBitmap(bitmap);
                                    viewHolder1.imageViewRight.setImageBitmap(bitmap2);
                                }
                            }
                        });
                    }
                });

            }
        }

        return convertView;
    }

    class  ViewHolder{
        ImageView imageView;
        TextView textViewb,textViews;
    }
    class ViewHolder1{
        ImageView imageViewLeft,imageViewRight;
        TextView textViewLeft,textViewRight;
    }
}
