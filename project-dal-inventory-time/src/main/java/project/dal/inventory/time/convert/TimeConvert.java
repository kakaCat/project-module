package project.dal.inventory.time.convert;

import project.dal.inventory.time.metadata.TimeUnitState;

public final class TimeConvert {


    public static int getPositionLength(TimeUnitState unit) {
        switch (unit) {
            case UNIT_QUARTER:
                return 96;
            case UNIT_HALF:
                return 48;
            case UNIT_FULL:
                return 24;
            default:
              return 0;
        }
    }

    public static int getUnitMinutes(TimeUnitState unit) {
        switch (unit) {
            case UNIT_FIVE:
                return 5;
            case UNIT_QUARTER:
                return 15;
            case UNIT_HALF:
                return 30;
            case UNIT_FULL:
                return 60;
            default:
                return 0;
        }
    }
}
