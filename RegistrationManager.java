import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegistrationManager extends Frame {
    // Components
    private Label nameLabel = new Label("Name:");
    private TextField nameTextField = new TextField(20);
    private Label genderLabel = new Label("Gender:");
    private CheckboxGroup genderCheckboxGroup = new CheckboxGroup();
    private Checkbox maleCheckbox = new Checkbox("Male", genderCheckboxGroup, false);
    private Checkbox femaleCheckbox = new Checkbox("Female", genderCheckboxGroup, false);
    private Label countryLabel = new Label("Country:");
    private Choice countryChoice = new Choice();
    private Button registerButton = new Button("Register");
    private Button displayButton = new Button("Display Registrants");
    private TextArea displayTextArea = new TextArea(10, 30);

    // Constructor
    public RegistrationManager() {
        setTitle("User Registration");
        setSize(400, 300);
        setLayout(new BorderLayout());

        Panel inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(genderLabel);
        inputPanel.add(maleCheckbox);
        inputPanel.add(new Label()); // for spacing
        inputPanel.add(femaleCheckbox);
        inputPanel.add(countryLabel);
        inputPanel.add(countryChoice);

        Panel buttonPanel = new Panel();
        buttonPanel.add(registerButton);
        buttonPanel.add(displayButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(displayTextArea, BorderLayout.NORTH);

        // Populate the country choice dropdown
        countryChoice.add("Select Country");
        countryChoice.add("USA");
        countryChoice.add("Canada");
        countryChoice.add("UK");

        // Event handling
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRegistrants();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    // Method to register user
    private void registerUser() {
        String name = nameTextField.getText();
        String gender = (maleCheckbox.getState()) ? "Male" : "Female";
        String country = countryChoice.getSelectedItem();

        // Store the user details
        storeUserDetails(name, gender, country);

        // Clear input fields
        nameTextField.setText("");
        maleCheckbox.setState(false);
        femaleCheckbox.setState(false);
        countryChoice.select(0);

        // Inform the user
        displayTextArea.append("Registered: " + name + ", " + gender + ", " + country + "\n");
    }

    // Method to store user details
    private void storeUserDetails(String name, String gender, String country) {
        try (FileWriter fw = new FileWriter("registrants.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(name + ", " + gender + ", " + country);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to display registrants from the file
    private void displayRegistrants() {
        displayTextArea.setText(""); // Clear previous content

        try (BufferedReader br = new BufferedReader(new FileReader("registrants.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                displayTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        RegistrationManager app = new RegistrationManager();
        app.setVisible(true);
    }
}