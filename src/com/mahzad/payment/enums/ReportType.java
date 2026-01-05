package com.mahzad.payment.enums;

public enum ReportType {
    Daily(1),
    ByTime(2);

    private final int reportCode;
    ReportType(int reportCode) {
        this.reportCode = reportCode;
    }

    public int getReportCode() {
        return reportCode;
    }

    public String getReportName() {
        return name();
    }

    public static ReportType fromCode(int reportCode) {
        for (ReportType code : ReportType.values()) {
            if (code.getReportCode() == reportCode) {
                return code;
            }
        }
        throw new IllegalArgumentException("Menu code " + reportCode + " not found");
    }
}
