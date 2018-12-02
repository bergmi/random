import java.util.Collection;

public class SimulatorUtil {

    public static String produceResultString(Collection<Patient> effected) {
        StringBuilder builder = new StringBuilder("Result: ");

        for (State state : State.values()) {
            builder.append(state.shortRep()).append(":").append(effected.stream().filter(p -> state ==p.getState()).count()).append(",");
        }

        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }
}
