import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classic smoketest.
 *
 * Tests the basic functionality.
 *
 * Because of the flying spaghetti monster requirement (1/10^6: dead ->alive),
 * tests can randomly fail.
 *
 * This could be prevented, but seems a bit out of scope for this task.
 *
 */
public class SmokeTest {
    private Collection<Patient> testPatient = new ArrayList<>();
    private Collection<Drug> testDrug = new ArrayList<>();

    @After
    public void cleanup() {
        testPatient.clear();
        testDrug.clear();
    }

    @Test
    public void no_patients_one_drug() {

        // setup
        testDrug.add(Drug.ASPIRIN);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // same states except diabetes, he dies without insulin
        Assert.assertEquals("Result: F:0,H:0,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void no_patients_no_drug() {

        // setup

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // same states except diabetes, he dies without insulin
        Assert.assertEquals("Result: F:0,H:0,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void aspirin_cures_fever() {

        // setup
        testPatient.add(new Patient(State.FEVER));
        testDrug.add(Drug.ASPIRIN);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // Aspirin cures fever
        Assert.assertEquals("Result: F:0,H:1,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void paracetamol_cures_fever() {

        // setup
        testPatient.add(new Patient(State.FEVER));
        testDrug.add(Drug.PARACETAMOL);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // Aspirin cures fever
        Assert.assertEquals("Result: F:0,H:1,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void antibiotics_cures_tuberculosis() {

        // setup
        testPatient.add(new Patient(State.TUBERCULOSIS));
        testDrug.add(Drug.ANTIBIOTICS);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // Aspirin cures fever
        Assert.assertEquals("Result: F:0,H:1,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void insulin_stops_diabetics_from_dying() {

        // setup
        testPatient.add(new Patient(State.DIABETES));
        testDrug.add(Drug.INSULIN);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // Aspirin cures fever
        Assert.assertEquals("Result: F:0,H:0,D:1,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void insulin_and_antibiotics_cause_healthy_patients_to_catch_fever() {

        // setup
        testPatient.add(new Patient(State.HEALTHY));
        testDrug.add(Drug.INSULIN);
        testDrug.add(Drug.ANTIBIOTICS);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // Aspirin cures fever
        Assert.assertEquals("Result: F:1,H:0,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void insulin_and_antibiotics_cause_patients_to_die() {

        // 1st setup
        testPatient.add(new Patient(State.FEVER));
        testDrug.add(Drug.PARACETAMOL);

        // 1st action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // Paracetamol cures fever
        Assert.assertEquals("Result: F:0,H:1,D:0,T:0,X:0", SimulatorUtil.produceResultString(effected));

        // 2nd setup
        testDrug.remove(Drug.PARACETAMOL);
        Assert.assertTrue(testDrug.isEmpty());
        testDrug.add(Drug.ASPIRIN);

        // 2nd action
        effected = DrugAdministration.treatPatients(effected, testDrug);

        // Aspirin cures fever
        Assert.assertEquals("Result: F:0,H:0,D:0,T:0,X:1", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void one_of_every_state_no_drugs() {

        // setup
        testPatient.add(new Patient(State.HEALTHY));
        testPatient.add(new Patient(State.FEVER));
        testPatient.add(new Patient(State.DIABETES));
        testPatient.add(new Patient(State.TUBERCULOSIS));
        testDrug.add(Drug.NO_DRUG);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // same states except diabetes, he dies without insulin
        Assert.assertEquals("Result: F:1,H:1,D:0,T:1,X:1", SimulatorUtil.produceResultString(effected));

    }

    @Test
    public void one_of_every_state() {

        // setup
        testPatient.add(new Patient(State.HEALTHY));
        testPatient.add(new Patient(State.FEVER));
        testPatient.add(new Patient(State.DIABETES));
        testPatient.add(new Patient(State.TUBERCULOSIS));
        testDrug.add(Drug.INSULIN);
        testDrug.add(Drug.ANTIBIOTICS);
        testDrug.add(Drug.ASPIRIN);

        // action
        Collection<Patient> effected = DrugAdministration.treatPatients(testPatient, testDrug);

        // H -> F -> H (Insulin and Antibiotics, then Aspirin)
        // F -> H (Aspirin)
        // D -> D (does not die)
        // T -> H (Antibiotics)
        Assert.assertEquals("Result: F:0,H:3,D:1,T:0,X:0", SimulatorUtil.produceResultString(effected));

    }

}
