package com.ly.service;

import com.ly.model.QRCodeInfo;

public class QRCodeUtil {
    private QRCodeInfo qrCodeInfo;

    public QRCodeUtil(String context) {
        qrCodeInfo = new QRCodeInfo(context, 3, "l");
    }
    public QRCodeUtil(String context, int versions, String errorCorrectionLevel) {
        qrCodeInfo = new QRCodeInfo(context, versions, errorCorrectionLevel);
    }

    private static int countSide(int versions) {
        if (versions > 0 && versions < 41) {
            return versions * 
        }
    }
}
