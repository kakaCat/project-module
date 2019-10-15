package project.dal.inventory.time.policy;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Policyfactory {


    private static Map<Integer, TimeInventoryCreatePolicy> createPolicies = new HashMap<>();

    private static Map<Integer, TimeInventoryLockPolicy> lockPolicies = new HashMap<>();


    public static void putCreatePolicies(){
//        createPolicies.put();
    }

    private static void putlockPolicies(){
//       lockPolicies.put(lockPolicy.name(), lockPolicy);
    }


    public TimeInventoryCreatePolicy getCreatePolicy(int policy) {

        return createPolicies.get(policy);
    }
}
