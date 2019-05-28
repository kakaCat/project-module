package project.module.exception.gateway.configuration.common;


public class ApiResponseEntity<T extends Object> {

    public static final int EXCEPTION_STATUS = -1;

    /**
     * 响应状态:0成功 1失败 -1异常
     */
    private int status;

    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 数据内容
     */
    private T data;

    public ApiResponseEntity(){}


    public ApiResponseEntity(Builder builder) {
        this.errorMsg=builder.errorMsg;
        this.data = (T) builder.data;
        this.status = builder.status;
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

    public static class Builder<T extends Object> {
        public ApiResponseEntity builder(){
            return new ApiResponseEntity(this);
        }


        private String errorMsg;

        private T data;

        private int status;

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
