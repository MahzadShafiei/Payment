package Enums;

public enum SimOperator {
    HamrahAval(1),
    Irancell(2),
    Rightel(3);

    private int simOperator;
    SimOperator(int code) {
        this.simOperator = code;
    }

    public int getSimOperatorCode() {
        return simOperator;
    }

    public String getOperatorName() {
        return name();
    }


    public static SimOperator fromCode(int code) {
        for (SimOperator simOperator : SimOperator.values()) {
            if (simOperator.getSimOperatorCode() == code) {
                return simOperator;
            }
        }
        throw new IllegalArgumentException("Operator code " + code + " not found");
    }

}
