package com.justsafe.libarch.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {

    private static final String TAG = "BitmapUtils";

    public static final double KB = 1024.0;
    public static final double MB = KB * KB;
    public static final double GB = KB * KB * KB;


    /**
     * 获取本地视频的第一帧
     *
     * @param localPath
     * @return
     */
    public static Bitmap getLocalVideoBitmap(String localPath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(localPath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
            Log.i("BitmapUtils", localPath);
            String thumbnailPath = localPath.substring(0, localPath.lastIndexOf(".")) + ".jpg";
            Log.i("BitmapUtils", thumbnailPath);

            saveBitmap(bitmap, new File(thumbnailPath));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.i("BitmapUtils", e.toString());

        } finally {
            retriever.release();
        }
        return bitmap;
    }

    /**
     * get Local video duration
     *
     * @return
     */
    public static int getLocalVideoDuration(String videoPath) {
        int duration;
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            duration = Integer.parseInt(mmr.extractMetadata
                    (MediaMetadataRetriever.METADATA_KEY_DURATION));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return duration;
    }


    /**
     * 保存Bitmap到文件
     *
     * @param bitmap
     * @param target
     */

    public static void saveBitmap(Bitmap bitmap, File target) {
        if (target.exists()) {
            target.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(target);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 改变图片的尺寸
     *
     * @param bitmap
     * @return boolean
     */

    public static boolean changeSize(Bitmap bitmap, String savePath) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i(TAG, "width: " + width + "  height: " + height);
        if (((float) height) / ((float) width) == 0.75) {
            Log.i(TAG, " 已经是3/4了");
            saveBitmap(bitmap, new File(savePath));
            return true;
        } else {
            //高度不变，宽度变
            width = (int) ((height / 3f) * 4);
        }
        Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        Log.i(TAG, "change width: " + mBitmap.getWidth() + "  height: " + mBitmap.getHeight());
        saveBitmap(mBitmap, new File(savePath));
        return true;
    }

    public static boolean changeSize(String path, String savePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return changeSize(bitmap, savePath);
    }


    public static boolean changeSize(byte[] data, String savePath) {
        //Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        return changeSize(bitmap, savePath);
    }


    /**
     * 压缩bitmp到目标大小（质量压缩）
     *
     * @param bitmap
     * @param needRecycle
     * @param maxSize
     * @return
     */
    public static Bitmap compressBitmap(Bitmap bitmap, boolean needRecycle, long maxSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length > maxSize) {
            baos.reset();//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bm = BitmapFactory.decodeStream(isBm, null, null);
        if (needRecycle) {
            bitmap.recycle();
        }
        bitmap = bm;
        return bitmap;
    }

    /**
     * 等比压缩（宽高等比缩放）
     *
     * @param bitmap
     * @param needRecycle
     * @param targetWidth
     * @param targeHeight
     * @return
     */
    public static Bitmap compressBitmap(Bitmap bitmap, boolean needRecycle, int targetWidth, int targeHeight) {
        float sourceWidth = bitmap.getWidth();
        float sourceHeight = bitmap.getHeight();

        float scaleWidth = targetWidth / sourceWidth;
        float scaleHeight = targeHeight / sourceHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight); //长和宽放大缩小的比例
        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (needRecycle) {
            bitmap.recycle();
        }
        bitmap = bm;
        return bitmap;
    }

    public static Bitmap compressBitmap(String imageFile, boolean qualityCompress, long maxSize, int targetWidth, int targeHeight) {
        return compress(imageFile, null, false, qualityCompress, maxSize, targetWidth, targeHeight);
    }

    private static Bitmap compress(String imageFile, String targetFile, boolean isSave, boolean qualityCompress, long maxSize, int targetWidth, int targeHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options); //加载图片信息
        int sourceWidth = options.outWidth;
        int sourceHeight = options.outHeight;
        options.inJustDecodeBounds = false;
        //计算inSampleSize
        int inSampleSize = 1;
        //先根据宽度进行缩小
        while (sourceWidth / inSampleSize > targetWidth) {
            inSampleSize++;
        }
        //然后根据高度进行缩小
        while (sourceHeight / inSampleSize > targeHeight) {
            inSampleSize++;
        }

        if (inSampleSize <= 0) {
            inSampleSize = 1;
        }
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile, options);//加载真正bitmap

        bitmap = compressBitmap(bitmap, false, targetWidth, targeHeight); //等比缩放
        if (qualityCompress) {
            bitmap = compressBitmap(bitmap, true, maxSize); //压缩质量
        }

        if (isSave) {
            String savePath = imageFile;
            if ((targetFile != null)) {
                savePath = targetFile;
            }

            saveBitmap(bitmap, new File(savePath));//保存图片
        }

        return bitmap;
    }

    /**
     * 压缩某张图片(执行步骤sampleSize压缩->等比压缩->质量压缩)
     *
     * @param imageFile
     * @param targetFile      保存目标，为空表示源地址保存
     * @param qualityCompress 是否做质量压缩
     * @param maxSize         目标图片大小
     * @param targetWidth
     * @param targeHeight
     */
    public static void compressImage(String imageFile, String targetFile, boolean qualityCompress, long maxSize, int targetWidth, int targeHeight) {
        Bitmap bitmap = compress(imageFile, targetFile, true, qualityCompress, maxSize, targetWidth, targeHeight);
        bitmap.recycle();
    }

    public static void compressImage(String imageFile, boolean qualityCompress, long maxSize, int targetWidth, int targeHeight) {
        compressImage(imageFile, null, qualityCompress, maxSize, targetWidth, targeHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile
     * @param targetWidth
     * @param targeHeight
     */
    public static void compressImage(String imageFile, int targetWidth, int targeHeight) {
        compressImage(imageFile, null, false, 0L, targetWidth, targeHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile
     * @param targetWidth
     * @param targeHeight
     * @return
     */
    public static Bitmap compressBitmap(String imageFile, int targetWidth, int targeHeight) {
        return compressBitmap(imageFile, false, 0L, targetWidth, targeHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile
     * @param scale     图片缩小倍速
     */
    public static void compressImageSmall(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / scale;
        int targeHeight = options.outHeight / scale;
        compressImage(imageFile, targetWidth, targeHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile
     * @param scale     图片缩小倍速
     * @return
     */
    public static Bitmap compressBitmapSmall(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / scale;
        int targeHeight = options.outHeight / scale;
        return compressBitmap(imageFile, targetWidth, targeHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile
     * @param scale     图片放大倍速
     */
    public static void compressImageBig(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth * scale;
        int targeHeight = options.outHeight * scale;
        compressImage(imageFile, targetWidth, targeHeight);

    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile
     * @param scale     图片放大倍速
     * @return
     */
    public static Bitmap compressBitmapBig(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth * scale;
        int targeHeight = options.outHeight * scale;
        return compressBitmap(imageFile, targetWidth, targeHeight);
    }

    /**
     * 质量压缩图片
     *
     * @param imageFile
     * @param targetFile
     * @param qualityCompress
     * @param maxSize
     */
    public static void compressImage(String imageFile, String targetFile, boolean qualityCompress, long maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / 2;
        int targeHeight = options.outHeight / 2;
        compressImage(imageFile, targetFile, qualityCompress, maxSize, targetWidth, targeHeight);
    }

    /**
     * 质量压缩图片
     *
     * @param imageFile
     * @param qualityCompress
     * @param maxSize
     */
    public static void compressImage(String imageFile, boolean qualityCompress, long maxSize) {
        compressImage(imageFile, null, qualityCompress, maxSize);
    }

    /**
     * 质量压缩图片
     *
     * @param imageFile
     * @param qualityCompress
     * @param maxSize
     * @return
     */
    public static Bitmap compressBitmap(String imageFile, boolean qualityCompress, long maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / 2;
        int targeHeight = options.outHeight / 2;
        return compressBitmap(imageFile, qualityCompress, maxSize, targetWidth, targeHeight);
    }

    /**
     * 质量压缩图片-压缩在maxSize以内
     *
     * @param imageFile
     * @param maxSize
     */
    public static void compressImage(String imageFile, long maxSize) {
        compressImage(imageFile, true, maxSize);
    }

    /**
     * 质量压缩图片-压缩在maxSize以内
     *
     * @param imageFile
     * @param maxSize
     * @return
     */
    public static Bitmap compressBimap(String imageFile, long maxSize) {
        return compressBitmap(imageFile, true, maxSize);
    }

    /**
     * 质量压缩图片-压缩在1M以内
     *
     * @param imageFile
     */
    public static void compressImage(String imageFile) {
        compressImage(imageFile, true, (long) (1 * MB));
    }

    /**
     * 质量压缩图片-压缩在1M以内
     *
     * @param imageFile
     * @return
     */
    public static Bitmap compressBitmap(String imageFile) {
        return compressBitmap(imageFile, true, (long) (1 * MB));
    }

    public static void compress(String path, int quality) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        saveBitmap(bm, new File(path));
    }


    /**
     * 压缩并处理尺寸
     *
     * @param imageFile
     * @param targetPath
     */
    public static void compressAndScaleBitmap(String imageFile, String targetPath) {
        // 配置压缩的参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //获取当前图片的边界大小，而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeFile(imageFile, options);
        options.inJustDecodeBounds = false;
        ////inSampleSize的作用就是可以把图片的长短缩小inSampleSize倍，所占内存缩小inSampleSize的平方
        options.inSampleSize = caculateSampleSize(options, 500, 500);
        File file=new File(imageFile);
        int i = imageFile.lastIndexOf("/");
        int length = imageFile.length();

        boolean fileExit = FileUtils.isFileExit(imageFile.substring(0, i + 1), imageFile.substring(i + 1, length));

        Log.e("compressAndScaleBitmap",fileExit+"----------"+imageFile+"----:" +file.exists());

        FileInputStream is = null;
        try {
            is = new FileInputStream(imageFile);
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(is.getFD(), null, options);
            saveBitmap(bitmap, new File(targetPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("compressAndScaleBitmap", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("compressAndScaleBitmap",e.toString());

        }

        //Bitmap bm = BitmapFactory.decodeFile(imageFile, options); // 解码文件
        //BitmapFactory.decodeFileDescriptor()

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 计算出所需要压缩的大小
     *
     * @param options
     * @param reqWidth  我们期望的图片的宽，单位px
     * @param reqHeight 我们期望的图片的高，单位px
     * @return
     */
    private static int caculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int sampleSize = 1;
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        if (picWidth > reqWidth || picHeight > reqHeight) {
            int halfPicWidth = picWidth / 2;
            int halfPicHeight = picHeight / 2;
            while (halfPicWidth / sampleSize > reqWidth || halfPicHeight / sampleSize > reqHeight) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }

    public static File compressImage2(String imagePath, int displayWidth, int displayHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只测量image 不加载到内存
        BitmapFactory.decodeFile(imagePath, options);//测量image

        options.inPreferredConfig = Bitmap.Config.RGB_565;//设置565编码格式 省内存
        options.inSampleSize = calculateInSampleSize(options, displayWidth, displayHeight);//获取压缩比 根据当前屏幕宽高去压缩图片

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);//按照Options配置去加载图片到内存
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();//字节流输出
        if (bitmap==null){

            Log.i("TYTYYYYYYYYYYYYY","ppppppppppppppppp");
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);//压缩成JPEG格式 压缩像素质量为50%

        String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.lastIndexOf("."));//获取文件名称
        File outFile = new File("/storage/emulated/0/PhotoPickTemp", fileName + "_temp.jpeg");//创建压缩后的image文件
        try {
            if (!outFile.exists()) {//判断新文件是否存在
                if (outFile.createNewFile()) {//判断创建新文件是否成功
                    FileOutputStream fos = new FileOutputStream(outFile);//创建一个文件输出流
                    byte[] bytes = out.toByteArray();//字节数组
                    int count = bytes.length;//字节数组的长度
                    fos.write(bytes, 0, count);//写到文件中
                    fos.close();//关闭流
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {//计算图片的压缩比
        final int height = options.outHeight;//图片的高度
        final int width = options.outWidth;//图片的宽度 单位1px 即像素点

        int inSampleSize = 1;//压缩比

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}

