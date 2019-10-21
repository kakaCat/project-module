package project.common.core.result;


public class ResponseUtil<T> {

    private final static int SUCCESS = 200;

    private final static int ERROR = 500;

    private ResponseData<T> responseData;

    public ResponseUtil() {
        responseData = new ResponseData<>();
        responseData.setSuccess(true);
        responseData.setMessage("success");
        responseData.setCode(SUCCESS);
    }

    public ResponseData<T> setData(T t) {
        this.responseData.setResult(t);
        this.responseData.setSuccess(true);
        responseData.setCode(SUCCESS);
        return this.responseData;
    }

    public ResponseData<T> setData(T t, String msg) {
        this.responseData.setResult(t);
        this.responseData.setSuccess(true);
        this.responseData.setMessage(msg);
        responseData.setCode(SUCCESS);
        return this.responseData;
    }

    public ResponseData<T> setErrorMsg(String msg) {
        this.responseData.setSuccess(false);
        this.responseData.setMessage(msg);
        responseData.setCode(ERROR);
        return this.responseData;
    }

    public ResponseData<T> setErrorMsg(Integer code, String msg) {
        this.responseData.setSuccess(false);
        this.responseData.setMessage(msg);
        responseData.setCode(ERROR);
        return this.responseData;
    }
}
