package project.dal.inventory.time.policy;

import project.dal.inventory.time.param.DalParam;

/**
 *
 * @author jinli Mar 4, 2018
 */
public interface TimeInventoryLockPolicy {

    int name();

    TimeInventoryLockValues lock(DalParam dalParam);

    public static class TimeInventoryLockValues {
        public int previous;
        public int following;

    }
}
