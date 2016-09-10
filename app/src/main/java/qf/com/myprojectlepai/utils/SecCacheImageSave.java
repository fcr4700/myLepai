package qf.com.myprojectlepai.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecCacheImageSave {
	 public static void saveBitmap( String uri, Bitmap bitmap){
  	   FileOutputStream fos=null;
  	   ByteArrayOutputStream bos=null;
  	   File file=new File("data/data/qf.com.myprojectlepai/cache/image");
		 if (!file.exists()){
			 file.mkdir();
			 Log.d("fcr", "创建目录()");
		 }
  	   try {
			fos=new FileOutputStream("data/data/qf.com.myprojectlepai/cache/image"+ File.separator+uri+".png");
			   Bitmap bitmap2=bitmap;
			   bos =new ByteArrayOutputStream();
			   bitmap2.compress(CompressFormat.PNG,25,bos );
			   byte[] saveByte=bos.toByteArray();
			   Log.d("sdH", "二级缓存成功=");//+bos.toString()
			   fos.write(saveByte,0,saveByte.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos!=null){try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
     }
}
