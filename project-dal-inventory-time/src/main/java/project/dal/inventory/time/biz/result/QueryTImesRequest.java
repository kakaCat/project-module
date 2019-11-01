package project.dal.inventory.time.biz.result;

import lombok.Data;
import project.common.core.result.AbstractRequest;

import java.util.Date;

@Data
public class QueryTImesRequest extends AbstractRequest {

    private String queryId;

    private String queryTime;

    private int count=0;



    @Override
    public void requestCheck() {

    }
}
