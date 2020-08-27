package pages;

public enum AttempsType {
    SHORT_ATTEMPT (2),
    MEDIUM_ATTEMPT (5),
    LONG_ATTEMPT (7);

    private int attemptAmount;

    AttempsType(int attemptAmount) {
        this.attemptAmount= attemptAmount;
    }

    public int getAttemptAmount(){
        return this.attemptAmount;
    }
}
