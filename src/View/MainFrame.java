package View;

import javax.swing.*;

public abstract class MainFrame extends JFrame {
    public JPanel mainPanel;
    protected double width;
    protected double height;
    abstract protected void initComponents();

    MainFrame(int width, int height){
        this.width = width;
        this.height = height;
        initMainFrame();
        initComponents();
        this.setVisible(true);
    }

    protected void initMainFrame(){
        this.mainPanel = new JPanel() ;

        this.setContentPane(this.mainPanel);

        this.setTitle("Idm++++");
        this.setSize((int) this.width, (int) this.height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public int getWidth() {
        return (int) width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return (int) height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
