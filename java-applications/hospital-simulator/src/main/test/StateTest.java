import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void input_string_to_enum_type() {

        // setup
        String[] legalInputs = {"H", "F", "D", "T", "X", "H", "f", "d", "t", "x"};
        String[] illegalStrings = {"a", "fever", "1", "-"};

        // all legal values should result in an object of type State
        for (String s : legalInputs) {
            Assert.assertNotNull(State.fromString(s));
        }

        // all illegal values should result in an error being thrown
        for (String s : illegalStrings) {
            thrown.expect(IllegalArgumentException.class);
            Assert.assertNull(State.fromString(s));
        }

    }
}
