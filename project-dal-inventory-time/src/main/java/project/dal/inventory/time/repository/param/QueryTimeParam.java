package project.dal.inventory.time.repository.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class QueryTimeParam {

    private int scope;

    private List<String> scopeIds;

    private Date startDate;

    private Date endDate;

    public void addScopeIdSingle(String scopeId){
        scopeIds = new ArrayList<>(1);
        scopeIds.add(scopeId);
    }

    public String getScopeIdSingle(){
        return scopeIds.get(0);
    }


}
