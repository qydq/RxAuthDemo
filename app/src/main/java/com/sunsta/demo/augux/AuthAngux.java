package com.sunsta.demo.augux;

/**
 * Created by sunsta on 24/04/17.
 * 定义angux（备注：也就是Ui-Mode）,下面是几种状态，将各种状态提前定义
 */
public class AuthAngux {
    private final boolean inProcess;
    private final boolean usrValidate;
    private final boolean pwdValidate;
    private final boolean success;
    private final String errorMessage;

    private AuthAngux(boolean inProcess, boolean usrValidate, boolean pwdValidate, boolean success, String errorMessage) {
        this.inProcess = inProcess;
        this.usrValidate = usrValidate;
        this.pwdValidate = pwdValidate;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static AuthAngux idle() {
        return new AuthAngux(false, true, true, false, "");
    }

    public static AuthAngux inProcess() {
        return new AuthAngux(true, true, true, false, "");
    }

    public static AuthAngux success() {
        return new AuthAngux(false, true, true, true, "");
    }

    public static AuthAngux fail(boolean username, boolean password, String msg) {
        return new AuthAngux(false, username, password, false, msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isInProcess() {
        return inProcess;
    }

    public boolean isUsrValidate() {
        return usrValidate;
    }

    public boolean isPwdValidate() {
        return pwdValidate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
