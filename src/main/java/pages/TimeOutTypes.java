package pages;

public enum TimeOutTypes {
    LOW (10),
    MEDIUM (20),
    HIGH (30);

    private int timeOutInSec;

    TimeOutTypes(int timeOut) {
        this.timeOutInSec = timeOut;
    }

    public int getTimeOutInSec(){
        return this.timeOutInSec;
    }
}
