package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class ZombiePlantSelector extends JFrame {
    private ButtonGroup zombieGroup;
    private ButtonGroup plantGroup;
    private JRadioButton zombiesOriginal;
    private JRadioButton zombiesStrategic;
    private JRadioButton plantsIntelligent;
    private JRadioButton plantsStrategic;
    private JButton confirmButton;

    /**
     * Constructs a ZombiePlantSelector, which is a GUI application for selecting
     * zombie and plant strategies in a gaming context. The window is initialized
     * with a title, size, and layout, and contains radio button options for selecting
     * different strategies for zombies and plants. A confirmation button allows
     * users to confirm and display their selections or prompt them if selections
     * are incomplete.
     *
     * The GUI comprises two main panels: one for zombie selection and another for
     * plant selection. Each panel contains radio buttons to select either the
     * original or strategic zombie types, and intelligent or strategic plant types,
     * respectively.
     *
     * When the confirm button is clicked, if both a zombie and a plant type have
     * been selected, a dialog is shown displaying the user's choices. If either
     * selection is missing, a warning dialog prompts the user to complete their
     * selection.
     */
    public ZombiePlantSelector() {
        setTitle("Selector de Zombies y Plantas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Panel de Zombies
        JPanel zombiePanel = new JPanel();
        zombiePanel.setLayout(new BoxLayout(zombiePanel, BoxLayout.Y_AXIS));
        zombiePanel.setBorder(BorderFactory.createTitledBorder("Zombies"));
        zombieGroup = new ButtonGroup();
        zombiesOriginal = new JRadioButton("Zombies Original");
        zombiesStrategic = new JRadioButton("Zombies Strategic");
        zombieGroup.add(zombiesOriginal);
        zombieGroup.add(zombiesStrategic);
        zombiePanel.add(zombiesOriginal);
        zombiePanel.add(zombiesStrategic);

        // Panel de Plantas
        JPanel plantPanel = new JPanel();
        plantPanel.setLayout(new BoxLayout(plantPanel, BoxLayout.Y_AXIS));
        plantPanel.setBorder(BorderFactory.createTitledBorder("Plantas"));
        plantGroup = new ButtonGroup();
        plantsIntelligent = new JRadioButton("Plants Intelligent");
        plantsStrategic = new JRadioButton("Plants Strategic");
        plantGroup.add(plantsIntelligent);
        plantGroup.add(plantsStrategic);
        plantPanel.add(plantsIntelligent);
        plantPanel.add(plantsStrategic);

        // Botón de confirmación
        confirmButton = new JButton("Confirmar Selección");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zombieSelection = getSelectedButtonText(zombieGroup);
                String plantSelection = getSelectedButtonText(plantGroup);
                if (zombieSelection != null && plantSelection != null) {
                    JOptionPane.showMessageDialog(ZombiePlantSelector.this,
                            "Selecciones:\nZombies: " + zombieSelection + "\nPlantas: " + plantSelection);
                } else {
                    JOptionPane.showMessageDialog(ZombiePlantSelector.this,
                            "Por favor, selecciona una opción de cada categoría.");
                }
            }
        });

        add(zombiePanel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre paneles
        add(plantPanel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Espacio antes del botón
        add(confirmButton);

        setVisible(true);
    }

    /**
     * Retrieves the text of the currently selected button within a ButtonGroup.
     *
     * @param buttonGroup the ButtonGroup in which to search for the selected button
     * @return the text of the selected button if one is selected; otherwise, null
     */
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (AbstractButton button : Collections.list(buttonGroup.getElements())) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZombiePlantSelector();
            }
        });
    }
}