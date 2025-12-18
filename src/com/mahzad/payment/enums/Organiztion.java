package com.mahzad.payment.enums;

public enum Organiztion {
    Ab(1),
    Bargh(2),
    Gaz(3),
    TelephoneSabet(4),
    TelephoneHamrah(5),
    AvarezShahrdari(6),
    SazmanMaliat(8),
    RahnamaeiRanandegi(9);

    private int organizationCode;
    Organiztion(int organizationCode) {
        this.organizationCode = organizationCode;
    }

    public int getOrganizationCode() {
        return organizationCode;
    }

    public String getOrganizationName() {
        return name();
    }

    public static Organiztion fromCode(int organizationCode) {
        for (Organiztion organization : Organiztion.values()) {
            if (organization.getOrganizationCode() == organizationCode) {
                return organization;
            }
        }
        throw new IllegalArgumentException("Organization code " + organizationCode + " not found");
    }
}
