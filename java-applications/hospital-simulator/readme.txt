The Hospital Simulator - V1.0 - Michael Berg

Basic gradle tasks:

"gradlew build"     -       compile the classes and build the project [output: /build/classes]
"gradlew clean"     -       clean the output of the above mentioned task
"gradlew test"      -       run the tests for this project

Run the program on the command line:

1) run gradlew build in the root directory
2) navigate to ./build/libs
3) run java -cp hospital-simulation.jar Simulator *patiens* *drugs* (see below for requested argument format)

*----------------------------------------------------------------------------------------------------------------------*
*----------------------------------------------------------------------------------------------------------------------*

Problem description You were asked by a doctor friend to prepare for her a “Hospital simulator”,
which can simulate the future patients’ state, based on their current state and a list of drugs they take.
Patients can have one of these states:
● F: Fever
● H: Healthy
● D: Diabetes
● T: Tuberculosis
● X: Dead

In the “Hospital simulator” drugs are provided to all patients. It is not possible to target a specific patient.
This is the list of available drugs:
● As: Aspirin
● An: Antibiotic
● I: Insulin
● P: Paracetamol

Drugs can change patients’ states. They can cure, cause side effects or even kill a patient if not properly prescribed.
Drugs effects are described by the following rules:
● Aspirin cures Fever
● Antibiotic cures Tuberculosis
● Insulin prevents diabetic subject from dying, does not cure Diabetes
● If insulin is mixed with antibiotic, healthy people catch Fever
● Paracetamol cures Fever
● Paracetamol kills subject if mixed with aspirin
● One time in a million the Flying Flying Spaghetti Monster shows his noodly power
and resurrects a dead patient (Dead becomes Healthy).

Input Parameter 1:
List of patients' health status codes, separated by a comma. e.g. “D,F,F” means we have 3 patients,
one with diabetes and two with fever.
Parameter 2:
List of drugs codes, separated by a comma, e.g. “As,I” means patients will be treated with Aspirin and Insulin.
Output The result should be sent to stdout. It should be a comma separated string with number of patients with
a given state, following the format:
        F:NP,H:NP,D:NP,T:NP,X:NP
Where:
● F, H, D, T, X are patients’ health status codes
● NP is a number of patients for a given state
E.g. “F:0,H:2,D:0,T:0,X:1” means there are two healthy patients and one that is dead.

*----------------------------------------------------------------------------------------------------------------------*
*----------------------------------------------------------------------------------------------------------------------*