package View;

import Logic.UploadingLogic;
import Logic.WorkerPoolLogic;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UploadPage extends MainFrame {
    private String username;
    private File selectedFile;
    UploadPage(String username) {
        super(500, 500);
        this.username = username;
    }

    @Override
    protected void initComponents() {
        setTitle("Upload File");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(10, 20, 120, 25);
        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        });
        mainPanel.add(chooseFileButton);

        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(10, 60, 120, 25);
        uploadButton.addActionListener(e -> {
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(this, "No file chosen", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                startUpload();
            }
        });
        mainPanel.add(uploadButton);
    }

    private void startUpload() {
        String fileUrl = selectedFile.getAbsolutePath();
        String fileName = selectedFile.getName();
        UploadingLogic uploadingLogic = new UploadingLogic(username, fileUrl,fileName);
    }
}
