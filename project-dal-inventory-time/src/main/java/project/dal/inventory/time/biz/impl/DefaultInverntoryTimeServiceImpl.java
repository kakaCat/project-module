package project.dal.inventory.time.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.common.core.result.AbstractRequest;
import project.common.core.result.AbstractResponse;
import project.dal.inventory.time.biz.InverntoryTimeService;
import project.dal.inventory.time.biz.context.BaseContext;
import project.dal.inventory.time.biz.context.Context;
import project.dal.inventory.time.repository.service.TimeInventoryDbService;

@Service
public class DefaultInverntoryTimeServiceImpl implements InverntoryTimeService {
    
    private final TimeInventoryDbService timeInventoryDbService;
    @Autowired
    public DefaultInverntoryTimeServiceImpl( TimeInventoryDbService timeInventoryDbService1){
        this.timeInventoryDbService = timeInventoryDbService1;
    }


    @Override
    public AbstractResponse queryInverntoryTime(AbstractRequest request) {
        Context context= new BaseContext();
        init(context);
        queryTime(context);
        convertTime(context);
        resultTime(context);

        return null;
    }

    private void queryTime(Context context) {
//        timeInventoryDbService.

    }

    private void convertTime(Context context) {
    }

    private void resultTime(Context context) {
    }



    private void init(Context context) {
    }


}
