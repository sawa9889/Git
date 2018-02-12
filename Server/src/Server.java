import javax.swing.*;
import java.awt.*;

public class Server extends JFrame {
    JPanel panel1;


    Server(String name){
        super(name);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box box=Box.createVerticalBox();
        panel1.setLayout(null);
        JButton button1=new JButton("+");
        JButton button2=new JButton("-");
        JButton button3=new JButton("=");
        JButton button4=new JButton("/");
        panel1.add(button1);
        button1.setLocation(10,20);
        button1.setSize(100,100);
        panel1.add(button2);
        button2.setLocation(20,30);
        button2.setSize(100,100);
        panel1.add(button3);
        button3.setLocation(30,40);
        button3.setSize(100,100);
        panel1.add(button4);
        button4.setLocation(40,50);
        button4.setSize(100,100);
        this.setContentPane(panel1);
        this.setSize(200,200);

    }

    public static void main(String[] args) {
        JFrame Jf=new Server("Server");
        Jf.setVisible(true);
    }
}
