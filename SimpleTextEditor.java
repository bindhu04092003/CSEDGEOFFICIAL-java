import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleTextEditor extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;

    public SimpleTextEditor() {
        setTitle("Simple Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // File menu items
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Add listeners
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        fileChooser = new JFileChooser();
    }

    private void openFile() {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
                textArea.setText(text.toString());
                currentFile = file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            }
        }
        if (currentFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exitApp() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to save before exit?");
        if (option == JOptionPane.YES_OPTION) {
            saveFile();
        } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            return;
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SimpleTextEditor editor = new SimpleTextEditor();
                editor.setVisible(true);
            }
        });
    }
}