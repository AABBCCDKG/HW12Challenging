
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PaintTest {

    @Test
    public void testPaintComponents() {
        // Create an instance of Paint
        Paint paint = new Paint();

        // Test if the primary buttons exist
        JButton clrButton = paint.getClearButton();
        assertNotNull("Clear button should not be null", clrButton);

        JButton fillButton = paint.getFillButton();
        assertNotNull("Fill button should not be null", fillButton);

        JButton eraseButton = paint.getEraseButton();
        assertNotNull("Erase button should not be null", eraseButton);

        JButton randomButton = paint.getRandomButton();
        assertNotNull("Random button should not be null", randomButton);

        // Test if the text fields exist
        JTextField hexText = paint.getHexTextField();
        assertNotNull("Hex Text Field should not be null", hexText);
        assertEquals("Default text of hexText should be '#'", "#", hexText.getText());

        JTextField rText = paint.getRTextField();
        assertNotNull("R Text Field should not be null", rText);
        assertEquals("Default text of rText should be empty", "", rText.getText());

        JTextField gText = paint.getGTextField();
        assertNotNull("G Text Field should not be null", gText);
        assertEquals("Default text of gText should be empty", "", gText.getText());

        JTextField bText = paint.getBTextField();
        assertNotNull("B Text Field should not be null", bText);
        assertEquals("Default text of bText should be empty", "", bText.getText());
    }
}
