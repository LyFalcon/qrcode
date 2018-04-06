package com.ly.model;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeInfo {
    private String context;
    private int versions;
    private ErrorCorrectionLevel errorCorrectionLevel;
    private String savaPath;

    public QRCodeInfo() {
    }

    public QRCodeInfo(String context, int versions, int errorCorrectionLevel, String savaPath) {
        this.context = context;
        this.versions = versions;
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits(errorCorrectionLevel);
        this.savaPath = savaPath;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getVersions() {
        return versions;
    }

    public void setVersions(int versions) {
        this.versions = versions;
    }

    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    public void setErrorCorrectionLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.errorCorrectionLevel = errorCorrectionLevel;
    }

    public String getSavaPath() {
        return savaPath;
    }

    public void setSavaPath(String savaPath) {
        this.savaPath = savaPath;
    }
}
