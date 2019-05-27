package project.module.domain.enums;

public enum UserStatus {
    NORMAL(1),
    FREEZE(2),
    DEL(3);

    private int status;
    UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
