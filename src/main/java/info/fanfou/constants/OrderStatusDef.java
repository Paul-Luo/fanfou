package info.fanfou.constants;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
public enum OrderStatusDef {

    UNCONFIRMED("Unconfirmed"),
    CONFIRMED("Confirmed"),
    CANCELED("Canceled"),
    PAID("Paid");


    private String codeState;

    OrderStatusDef(String codeState) {
        this.codeState = codeState;
    }

    public String getCodeState() {
        return codeState;
    }

    public static OrderStatusDef getEnumFromString(String code) {
        for(OrderStatusDef orderStatusDef : OrderStatusDef.values()) {
            if (orderStatusDef.getCodeState().equals(code)) {
                return orderStatusDef;
            }
        }
        return null;
    }
}
