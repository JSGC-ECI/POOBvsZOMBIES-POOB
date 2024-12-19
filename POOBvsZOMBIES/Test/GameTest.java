import org.junit.jupiter.api.Test;
import presentation.Game;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GameTest is a test class designed to verify the initialization and
 * configuration of various components within the Game class.
 *
 * This class performs unit tests using the JUnit framework to validate
 * that the Game UI components, such as panels and buttons, are correctly
 * initialized and arranged as expected. The tests include validations for
 * private fields of the Game class through reflection, ensuring the integrity
 * of the UI structure.
 *
 * Methods in this class test the following:
 * - Initialization of the main game board panel.
 * - Proper setup of the plants and zombies panels with their respective layouts and components.
 * - Proper setup of the points panel with its labels.
 * - Validation that all necessary panels have been added to the Game frame.
 */
public class GameTest {

    /**
     * Tests the initialization of the "boardG" panel in the Game class.
     * Ensures that:
     * - The "boardG" panel is not null after initialization.
     * - The "boardG" panel contains exactly 50 components.
     * - All components within the "boardG" panel are instances of JButton.
     */
    @Test
    public void testPrepareElementsBoardInitializesBoardG() {
        Game game = new Game();

        JPanel boardG = getPrivateFieldValue(game, "boardG");
        assertNotNull(boardG, "boardG panel is not initialized.");
        assertEquals(50, boardG.getComponentCount(), "boardG does not contain the expected 50 components.");

        for (Component component : boardG.getComponents()) {
            assertTrue(component instanceof JButton, "All boardG components should be instances of JButton.");
        }
    }

    /**
     * Tests the initialization of the "plants" panel in the Game class.
     *
     * Ensures that:
     * - The "plants" panel is not null after initialization.
     * - The "plants" panel uses a FlowLayout as its layout manager.
     * - The "plants" panel contains exactly 5 components.
     * - All components within the "plants" panel are instances of JButton.
     */
    @Test
    public void testPrepareElementsBoardInitializesPlantsPanel() {
        Game game = new Game();

        JPanel plants = getPrivateFieldValue(game, "plants");
        assertNotNull(plants, "plants panel is not initialized.");

        assertTrue(plants.getLayout() instanceof FlowLayout, "plants panel layout should be a FlowLayout.");
        assertEquals(5, plants.getComponentCount(), "plants panel does not contain 5 components.");

        for (Component component : plants.getComponents()) {
            assertTrue(component instanceof JButton, "All plants panel components should be instances of JButton.");
        }
    }

    /**
     * Tests the initialization of the "zombies" panel in the Game class.
     *
     * Ensures that:
     * - The "zombies" panel is not null after initialization.
     * - The "zombies" panel uses a FlowLayout as its layout manager.
     * - The "zombies" panel contains exactly 5 components.
     * - All components within the "zombies" panel are instances of JButton.
     */
    @Test
    public void testPrepareElementsBoardInitializesZombiesPanel() {
        Game game = new Game();

        JPanel zombies = getPrivateFieldValue(game, "zombies");
        assertNotNull(zombies, "zombies panel is not initialized.");

        assertTrue(zombies.getLayout() instanceof FlowLayout, "zombies panel layout should be a FlowLayout.");
        assertEquals(5, zombies.getComponentCount(), "zombies panel does not contain 5 components.");

        for (Component component : zombies.getComponents()) {
            assertTrue(component instanceof JButton, "All zombies panel components should be instances of JButton.");
        }
    }

    /**
     * Tests the initialization of the "pointsPanel" in the Game class.
     *
     * Ensures that:
     * - The "pointsPanel" is not null after initialization.
     * - The "pointsPanel" contains exactly 3 components.
     * - All components within the "pointsPanel" are instances of JLabel.
     */
    @Test
    public void testPrepareElementsBoardInitializesPointsPanel() {
        Game game = new Game();

        JPanel pointsPanel = getPrivateFieldValue(game, "pointsPanel");
        assertNotNull(pointsPanel, "pointsPanel is not initialized.");

        assertEquals(3, pointsPanel.getComponentCount(), "pointsPanel does not contain the expected 3 components.");
        assertTrue(pointsPanel.getComponent(0) instanceof JLabel, "First component in pointsPanel should be a JLabel.");
        assertTrue(pointsPanel.getComponent(1) instanceof JLabel, "Second component in pointsPanel should be a JLabel.");
        assertTrue(pointsPanel.getComponent(2) instanceof JLabel, "Third component in pointsPanel should be a JLabel.");
    }

    /**
     * Tests that the Game class correctly adds JPanel components to the game frame.
     *
     * This method:
     * - Creates an instance of the Game class.
     * - Retrieves all components from the Game's content pane.
     * - Verifies that there is at least one instance of JPanel among the components.
     *
     * The test ensures that the necessary JPanel components are added to the game frame
     * during initialization.
     *
     * The assertion checks:
     * - If the count of JPanel components in the content pane is greater than zero.
     */
    @Test
    public void testPrepareElementsBoardAddsPanelsToGame() {
        Game game = new Game();
        Component[] components = game.getContentPane().getComponents();

        long componentCount = getComponentCountByClass(components, JPanel.class);
        assertTrue(componentCount > 0, "Game should add JPanel components to the frame.");
    }

    /**
     * Utility method to retrieve private field values from an object.
     *
     * This method uses reflection to access private fields of a given object.
     * It makes the field accessible, retrieves its value, and returns it as a generic type.
     *
     * @param obj       The object whose private field is to be accessed.
     * @param fieldName The name of the private field to access.
     * @return The value of the private field as a generic type.
     */
    @SuppressWarnings("unchecked")
    private <T> T getPrivateFieldValue(Object obj, String fieldName) {
        try {
            var field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            fail("Could not access private field: " + e.getMessage());
            return null;
        }
    }

    /**
     * Counts the number of components of a specified class from an array of components.
     *
     * This method takes an array of components and a class type to count how many components
     * are instances of the specified class or its subclasses.
     *
     * @param components Array of components to be checked.
     * @param cls        The class to count instances of.
     * @return The number of components that are instances of the specified class.
     */
    private long getComponentCountByClass(Component[] components, Class<?> cls) {
        return java.util.Arrays.stream(components)
                .filter(c -> cls.isAssignableFrom(c.getClass()))
                .count();
    }
}
