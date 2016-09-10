package qf.com.myprojectlepai.myActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import qf.com.myprojectlepai.R;

/**
 * Created by Administrator on 16-9-2.
 */
public class Welcome extends Activity{
    ImageView imageView;
    private static final String URL_STRING="http://shougong.fn.img-space.com/g1/M00/06/E7/Cg-4q1b56_aIQ5F_AAHXRJiUSeAAAQ2owHc2vAAAddc550.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        //imageView= (ImageView) findViewById(R.id.welcomId);

        initWelcome();


    }
//    public void finishThread(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();
//                    }
//                });
//            }
//        }).start();
//    }

    private void initWelcome() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //byte[] data = DownLoadUtils.getImage(URL_STRING);
                //final Bitmap bitmap= OptionBitmap.getOptionBitmap(data);
                //final Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }).start();
    }

}
