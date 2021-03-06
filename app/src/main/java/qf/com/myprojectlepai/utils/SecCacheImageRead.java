package qf.com.myprojectlepai.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecCacheImageRead {

	public static Bitmap readBitmap(Context context, String uri) {
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		File file=new File("data/data/qf.com.myprojectlepai/cache/image");
		byte[] br = null;
			try {
				fis = new FileInputStream("data/data/qf.com.myprojectlepai/cache/image" + File.separator + uri + ".png");
				bos = new ByteArrayOutputStream();
				byte[] b = new byte[512];
				int len = 0;
				while ((len = fis.read(b)) != -1) {
					bos.write(b, 0, len);
				}
				Log.d("sdD","==在二级缓存中找图片==");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (bos == null) {
				return null;
			} else {
				br = bos.toByteArray();
				return BitmapFactory.decodeByteArray(br, 0, br.length);
			}

	}
}



