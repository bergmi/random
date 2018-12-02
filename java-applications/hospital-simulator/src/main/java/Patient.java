import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Patient {

    private State state;

    private Set<Drug> drugsAdministered;

    public Patient(State state) {
        this.state = state;
        this.drugsAdministered = new HashSet<>();
    }

    public Patient(State state, Set<Drug> drugsAdministered) {
        this.state = state;
        this.drugsAdministered = drugsAdministered;
    }

    public void addAdministeredDrug(Drug drug) {
        drugsAdministered.add(drug);
    }


    public State getState() {
        return state;
    }

    public Set<Drug> getDrugsAdministered() {
        return drugsAdministered;
    }
}
