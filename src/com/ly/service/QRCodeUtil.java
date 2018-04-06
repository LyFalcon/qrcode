package com.ly.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.ly.model.QRCodeInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class QRCodeUtil {
    private QRCodeInfo qrCodeInfo;

    private static int BLACK = 0x000000;// 编码的颜色
    private static int WHITE = 0xFFFFFF;// 空白的颜色

    public QRCodeUtil(String context, String SavePath) {
        this.qrCodeInfo = new QRCodeInfo(context, 3, 1, SavePath);
    }
    public QRCodeUtil(String context, int versions, int errorCorrectionLevel, String SavePath) {
        this.qrCodeInfo = new QRCodeInfo(context, versions, errorCorrectionLevel, SavePath);
    }

    public boolean qrCodeEncode(){

        Hashtable<EncodeHintType,Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, qrCodeInfo.getErrorCorrectionLevel());

        int side = 0;
        try {
            side = countSide(qrCodeInfo.getVersions());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        //见信息编码为二进制序列
        BitMatrix matrix;
        try {
            matrix = new MultiFormatWriter().encode(qrCodeInfo.getContext(), BarcodeFormat.QR_CODE, side, side, hints);
        } catch (WriterException e) {
            e.printStackTrace();
            return false;
        }


        BufferedImage bufferedImage = toBufferedImage(matrix);

        File file = new File(qrCodeInfo.getSavaPath());

        try {
            ImageIO.write(bufferedImage, ".jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static int countSide(int versions) throws Exception {
        if (versions > 0 && versions < 41) {
            return (versions - 1) * 4 + 21;
        } else {
            throw new Exception("versions must between 1 and 40");
        }
    }

    public String qrCodeDecode(String save) {
        MultiFormatReader reader = new MultiFormatReader();

        File file = new File(save);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return result.getText();
    }
}
