package project.module.exception.gateway.configuration.common;


import project.module.exception.enums.SysCodeEnum;

public class RestResult <T extends Object> {

    public static final int EXCEPTION_STATUS = -1;



    private String errorCode;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 数据内容
     */
    private T data;

    /**
     * 响应状态:0失败 1成功 -1异常
     */
    private int status;

    public RestResult (){}


    public RestResult (Builder builder) {
        this.errorCode= builder.errorCode;
        this.errorMsg=builder.errorMsg;
        this.data = (T) builder.data;
        this.status = builder.status;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static <T> RestResult <T> build(SysCodeEnum sysCodeEnum, T t){
        RestResult  restResult = new RestResult ();
        restResult.setErrorMsg(sysCodeEnum.getErrorMsg());
        restResult.setStatus(sysCodeEnum.getStatus());
        restResult.setData(t);
        restResult.setStatus(sysCodeEnum.getStatus());
        return restResult;
    }

    public static class Builder<T extends Object> {
        public RestResult builder(){
            return new RestResult(this);
        }

        private String errorCode;

        private String errorMsg;

        private T data;

        private int status;

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder status(int status){
            this.status = status;
            return this;
        }


    }

}
