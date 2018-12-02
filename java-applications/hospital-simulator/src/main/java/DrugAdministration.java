import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class DrugAdministration {

    public static Collection<Patient> treatPatients(Collection<Patient> patients, Collection<Drug> drugs) {

        if (patients == null || patients.size() == 0)
            return Collections.emptyList();

        if (drugs == null || drugs.size() == 0)
            return patients;

        Collection<Patient> effected = new ArrayList<>(patients);

        for (Drug drug : drugs) {
            effected = effected.stream()
                    .map(patient -> drugEffect(patient, drug))
                    .collect(Collectors.toList());
        }

        return checkForNonTreatedDiabetics(effected);

    }

    private static Patient drugEffect(Patient patient, Drug drug) {

        if (Drug.NO_DRUG == drug)
            return patient;

        switch (patient.getState()) {
            case DEAD:
                if (1 == ThreadLocalRandom.current().nextInt(10000001)) {
                    return effected(patient, State.HEALTHY, drug);
                } else {
                    return patient;
                }

            case FEVER:
                switch (drug) {
                    case ASPIRIN:
                        if (!patient.getDrugsAdministered().contains(Drug.PARACETAMOL)) {
                            return effected(patient, State.HEALTHY, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case PARACETAMOL:
                        if (!patient.getDrugsAdministered().contains(Drug.ASPIRIN)) {
                            return effected(patient, State.HEALTHY, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case ANTIBIOTICS:
                        return effected(patient, null, drug);

                    case INSULIN:
                        return effected(patient, null, drug);

                    default:
                        throw new IllegalStateException("A drug is in the system, for witch no effects have been captured yet: " + drug);
                }

            case HEALTHY:
                switch (drug) {
                    case ASPIRIN:
                        if (!patient.getDrugsAdministered().contains(Drug.PARACETAMOL)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case PARACETAMOL:
                        if (!patient.getDrugsAdministered().contains(Drug.ASPIRIN)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case ANTIBIOTICS:
                        if (!patient.getDrugsAdministered().contains(Drug.INSULIN)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.FEVER, drug);
                        }

                    case INSULIN:
                        if (!patient.getDrugsAdministered().contains(Drug.ANTIBIOTICS)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.FEVER, drug);
                        }

                    default:
                        throw new IllegalStateException("A drug is in the system, for witch no effects have been captured yet: " + drug);
                }

            case DIABETES:
                switch (drug) {
                    case ASPIRIN:
                        if (!patient.getDrugsAdministered().contains(Drug.PARACETAMOL)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case PARACETAMOL:
                        if (!patient.getDrugsAdministered().contains(Drug.ASPIRIN)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case ANTIBIOTICS:
                        return effected(patient, null, drug);

                    case INSULIN:
                        return effected(patient, null, drug);

                    default:
                        throw new IllegalStateException("A drug is in the system, for witch no effects have been captured yet: " + drug);
                }

            case TUBERCULOSIS:
                switch (drug) {
                    case ASPIRIN:
                        if (!patient.getDrugsAdministered().contains(Drug.PARACETAMOL)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case PARACETAMOL:
                        if (!patient.getDrugsAdministered().contains(Drug.ASPIRIN)) {
                            return effected(patient, null, drug);
                        } else {
                            return effected(patient, State.DEAD, drug);
                        }

                    case ANTIBIOTICS:
                        if (!patient.getDrugsAdministered().contains(Drug.INSULIN)) {
                            return effected(patient, State.HEALTHY, drug);
                        } else {
                            return effected(patient, State.FEVER, drug);
                        }

                    case INSULIN:
                        return effected(patient, null, drug);

                    default:
                        throw new IllegalStateException("A drug is in the system, for witch no effects have been captured yet: " + drug);
                }

            default:
                throw new IllegalStateException("A health state has been added to the system, for witch no effects have been captured yet: " + patient.getState());
        }

    }

    private static Patient effected(Patient current, State resultingState, Drug drug) {
        if (resultingState == null) {
            Patient effected = new Patient(current.getState(), current.getDrugsAdministered());
            effected.addAdministeredDrug(drug);
            return effected;
        } else {
            Patient effected = new Patient(resultingState, current.getDrugsAdministered());
            effected.addAdministeredDrug(drug);
            return effected;
        }
    }

    private static Collection<Patient> checkForNonTreatedDiabetics(Collection<Patient> effected) {
        if (effected.stream().noneMatch(patient -> State.DIABETES == patient.getState() && patient.getDrugsAdministered().stream().noneMatch(drug -> Drug.INSULIN == drug))) {
            return effected;
        } else {
            return effected.stream()
                    .map(patient -> {
                                if (State.DIABETES == patient.getState())
                                    return new Patient(State.DEAD, patient.getDrugsAdministered());
                                else
                                    return new Patient(patient.getState(), patient.getDrugsAdministered());
                            }
                    )
                    .collect(Collectors.toList());
        }
    }

}
