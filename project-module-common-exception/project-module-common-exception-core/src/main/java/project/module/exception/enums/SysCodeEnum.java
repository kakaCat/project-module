package project.module.exception.enums;

public enum SysCodeEnum {

    SYSTEM_SUCCESS("10000", "成功",1);

    private String errCode;

    private String errorMsg;

    private int status;

    SysCodeEnum(String errCode,String errorMsg,int status) {
        this.errCode = errCode;
        this.errorMsg = errorMsg;
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
