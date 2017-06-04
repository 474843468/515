package com.psi.psieasymanager.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.gprinter.command.EscCommand;
import com.gprinter.command.GpUtils;
import com.mylhyl.zxing.scanner.encode.QREncode;
import com.psi.psieasymanager.App;
import java.util.Vector;

/**
 * 作者：${ylw} on 2017-05-15 14:03
 * 二维码图片utils 打印用
 */
public class BitmapUtils {

  /**
   * 生成二维码图片
   *
   * @param content 内容
   */
  public static Bitmap createQRBitmap(String content) {
    return QREncode.encodeQR(new QREncode.Builder(App.getInstance()).setContents(content).build());
  }

  /**
   * 根据给定的宽和高进行拉伸
   *
   * @param origin 原图
   * @param newWidth 新图的宽
   * @param newHeight 新图的高
   * @return new Bitmap
   */
  public static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
    int height = origin.getHeight();
    int width = origin.getWidth();
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
    Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
    if (!origin.isRecycled()) {
      origin.recycle();
    }
    return newBM;
  }

  /**
   * 生成二维码图片 字节数组
   *
   * @param content 二维码内容
   */
  public static byte[] createQRBitmapByte(String content) {
    Bitmap bitmap = scaleBitmap(createQRBitmap(content), 200, 200);
    return createBitmapByte(bitmap);
  }

  /**
   * 图片字节数据组
   */
  public static byte[] createBitmapByte(Bitmap bitmap) {
    EscCommand command = new EscCommand();
    command.addInitializePrinter();
    command.addRastBitImage(bitmap, bitmap.getWidth(), 0); // 打印图片
    Vector<Byte> vector = command.getCommand();
    return GpUtils.ByteTo_byte(vector);
  }
}
