package com.abstech.absreporting;

public class ReportModel {
    public ReportModel(String reportsSlNo, String reportsType, String reportsNo) {
        this.reportsSlNo = reportsSlNo;
        this.reportsType = reportsType;
        this.reportsNo = reportsNo;
    }

    public String getReportsSlNo() {
        return reportsSlNo;
    }

    public void setReportsSlNo(String reportsSlNo) {
        this.reportsSlNo = reportsSlNo;
    }

    public String getReportsType() {
        return reportsType;
    }

    public void setReportsType(String reportsType) {
        this.reportsType = reportsType;
    }

    public String getReportsNo() {
        return reportsNo;
    }

    public void setReportsNo(String reportsNo) {
        this.reportsNo = reportsNo;
    }

    private String reportsSlNo, reportsType, reportsNo;
}
