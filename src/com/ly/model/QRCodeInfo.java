package com.ly.model;

public class QRCodeInfo {
    private String context;
    private int versions;
    private ErrorCorrectionLevel errorCorrectionLevel;

    public QRCodeInfo() {
    }

    public QRCodeInfo(String context, int versions, String errorCorrectionLevel) {
        this.context = context;
        this.versions = versions;
        this.errorCorrectionLevel = ErrorCorrectionLevel.getErrorCorrectionLevel(errorCorrectionLevel);
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

    public void ErrorCorrectionLevel(String errorCorrectionLevel) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.getErrorCorrectionLevel(errorCorrectionLevel);
    }

    private enum ErrorCorrectionLevel {
        L, M ,Q ,H;
        ErrorCorrectionLevel() {
        }

        public String getValue(){return this.toString().toLowerCase();}

        public static ErrorCorrectionLevel getErrorCorrectionLevel(String country) {
            for (ErrorCorrectionLevel errorCorrectionLevel : ErrorCorrectionLevel.values()) {
                if (errorCorrectionLevel.getValue().equals(country.toLowerCase()))
                    return errorCorrectionLevel;
            }

            return null;
        }
    }
}
