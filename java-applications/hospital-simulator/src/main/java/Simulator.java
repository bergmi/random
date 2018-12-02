import java.util.ArrayList;
import java.util.Collection;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Welcome to the hospital simulator."
                    + "Please provide 2 consecutive command line arguments."
                    + "1st arg: A list of patients' represented by their health states (f.e.: \"H\",\"F\",\"T\",\"D\")"
                    + "2nd arg: A list of drugs to be administered to each patient (f.e.: \"P\",\"I\")"
                    + "Supported health states are: " + State.validInputArguments()
                    + "Supported drugs are: " + Drug.validInputArguments()
                    + "\n\n");
        }

        Collection<Patient> patients = new ArrayList<>();
        Collection<Drug> drugs = new ArrayList<>();

        for (String s : args[0].replace("\\s", "").split(",|, ")) {
            patients.add(new Patient(State.fromString(s)));
        }

        for (String d : args[1].replace("\\s", "").split(",|, ")) {
            drugs.add(Drug.fromString(d));
        }

        Collection<Patient> effected = DrugAdministration.treatPatients(patients, drugs);

        System.out.print("Result: " + SimulatorUtil.produceResultString(effected));
    }

}
