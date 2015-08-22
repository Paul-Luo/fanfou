package info.fanfou.constants;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
public enum  OrderStateDef {

    UNCONFIRMED("Unconfirmed"),
    CONFIRMED("Confirmed"),
    CANCELED("Canceled");


    private String codeState;

    private OrderStateDef(String codeState) {
        this.codeState = codeState;
    }

    public String getCodeState() {
        return codeState;
    }
}
