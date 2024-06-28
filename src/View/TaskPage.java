package View;

import javax.swing.*;

public class TaskPage extends MainFrame{
    private String username;
    TaskPage(String username) {
        super(500,500);
        this.username = username;
    }

    @Override
    protected void initComponents() {
        JButton UploadButton = new JButton("UploadPage");
        JButton DownloadButton = new JButton("DownloadPage");
        UploadButton.setFocusPainted(false);
        DownloadButton.setFocusPainted(false);
        UploadButton.setBounds(200,70,100,40);
        mainPanel.add(UploadButton);
        DownloadButton.setBounds(200,140,100,40);
        mainPanel.add(DownloadButton);
        UploadButton.addActionListener(e->{
            new UploadPage(username);
            this.dispose();
        });
        DownloadButton.addActionListener(e->{
            new DownloadPage();
            this.dispose();
        });

    }
}
