import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DrugTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void input_string_to_enum_type() {

        // setup
        String[] legalInputs = {"As", "I", "An", "P", "as", "AS", "i", "an", "AN", "p"};
        String[] illegalStrings = {"a", "Paracetamol", "1", "-"};

        // all legal values should result in an object of type State
        for (String s : legalInputs) {
            Assert.assertNotNull(Drug.fromString(s));
        }

        // all illegal values should result in an error being thrown
        for (String s : illegalStrings) {
            thrown.expect(IllegalArgumentException.class);
            Assert.assertNull(Drug.fromString(s));
        }

    }
}
