import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Base extends JFrame{
    public Base(){
        this.getContentPane().add(panel);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(passwordField1.getText())){
                    JOptionPane.showMessageDialog(null,"Succes");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Failure");
                }

            }
        });
    }


    private JButton button;
    private JPanel Panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel panel;
}
