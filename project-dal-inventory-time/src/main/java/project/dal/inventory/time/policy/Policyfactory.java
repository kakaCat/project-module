package project.dal.inventory.time.policy;

import org.springframework.stereotype.Service;
import project.dal.inventory.time.metadata.LockPolicy;
import project.dal.inventory.time.policy.createpolicy.DefaultDayShift;
import project.dal.inventory.time.policy.lockpolicy.HomeOrigin;

import java.util.HashMap;
import java.util.Map;

@Service
public class Policyfactory {


    private final static Map<Integer, TimeInventoryCreatePolicy> createPolicies = new HashMap<>(16);

    private final static Map<Integer, TimeInventoryLockPolicy> lockPolicies = new HashMap<>(16);


    static  {
        createPolicies.put(2,new DefaultDayShift());

    }
    static  {
        lockPolicies.put(LockPolicy.HOME_ORIGIN.getIndex(),new HomeOrigin());
    }

    public TimeInventoryCreatePolicy getCreatePolicy(int policy) {

        return createPolicies.get(policy);
    }


    public TimeInventoryLockPolicy getLockPolicies(int policy) {

        return lockPolicies.get(policy);
    }

}
