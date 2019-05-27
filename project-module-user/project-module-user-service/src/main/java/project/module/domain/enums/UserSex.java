package project.module.domain.enums;

public enum UserSex {

    MAN(1),
    WOMAN(0);

    private int status;
    UserSex(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
