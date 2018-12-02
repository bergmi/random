public enum State {

    FEVER("F", "Fever"),
    HEALTHY("H", "Healthy"),
    DIABETES("D", "Diabetes"),
    TUBERCULOSIS("T", "Tuberculosis"),
    DEAD("X", "Dead");

    private String state;

    private String fullStringRepresentation;

    State(String state, String fullStringRepresentation) {
        this.state = state;
        this.fullStringRepresentation = fullStringRepresentation;
    }

    public static State fromString(String s) {
        if (s.length() > 1)
            throw new IllegalArgumentException("Health states are represented by one character only.");

        for (State existingState : State.values()) {
            if (existingState.shortRep().equalsIgnoreCase(s))
                return existingState;
        }

        throw new IllegalArgumentException("No health state found corresponding to input argument: " + s);
    }

    public static String validInputArguments() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < State.values().length; ++i) {
            builder.append(State.values()[i].shortRep()).append(": ").append(State.values()[i].fullRep()).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length());

        return builder.toString();
    }

    public String shortRep() {
        return this.state;
    }

    public String fullRep() {
        return this.fullStringRepresentation;
    }

}
