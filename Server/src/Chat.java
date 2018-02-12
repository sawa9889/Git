import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Chat extends JFrame {
    JPanel panel1;
    private Socket socket;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private DataOutputStream oos ;
    private DataInputStream ois ;
    private String name,partner;
    private boolean Switch=true;

    synchronized private String read(){
        try {
            return ois.readUTF();
        }
        catch(IOException e ){System.out.println(e);}
        return "";
    }

    Chat(String Name) {
        super(Name);
        JTextArea text = new JTextArea();
        JPanel panel = new JPanel();
        JTextField text1 = new JTextField(30);
        JScrollPane scrool = new JScrollPane(text);
        JToolBar toolBar = new JToolBar("Instruments");
        JButton button1 = new JButton("Update");
        JButton button2 = new JButton("Connect");
        JButton button3 = new JButton("Disconnect");
        JButton button4 = new JButton("Send");
        JButton button5 = new JButton("Quit");
        JComboBox<String> box = new JComboBox<String>();

        class Input extends Thread {

            public void run() {
                while (Switch) {
                    String in = read();
                    if (in.equalsIgnoreCase("Disconnected")) {
                        button3.setEnabled(false);
                        button2.setEnabled(true);
                        box.setEnabled(true);
                        partner=name;
                    } else if (in.equalsIgnoreCase("connecting")) {
                        button3.setEnabled(true);
                        button2.setEnabled(false);
                        box.setEnabled(false);
                        partner=read();
                    } else {
                        text.append(partner + " : " + in + "\n");
                    }
                }
            }
        }

        Login Login = new Login();
        Input input=new Input();
        Login.setVisible(true);
        Login.addComponentListener(new ComponentAdapter() {

            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                Chat.super.setVisible(true);
                input.start();
            }
        });

        button1.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                        System.out.println("I'm here");
                        oos.writeUTF("Update");
                        String str = read();
                        String[] names = str.split(" ");
                        box.removeAllItems();
                        box.addItem(partner);
                        for (String x : names) {
                            box.addItem(x);
                        }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button2.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    oos.writeUTF("connect");
                    oos.writeUTF(box.getSelectedItem().toString());
                }
                catch(IOException ex){ex.printStackTrace();}

            }
        });

        button3.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    oos.writeUTF("close connection");

                }
                catch(IOException ex){ex.printStackTrace();}
            }
        });

        button3.setEnabled(false);

        button4.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    oos.writeUTF(text1.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button5.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    oos.writeUTF("quit");
                    Switch=false;
                }
                catch (IOException ex){ ex.printStackTrace();}

            }
        });

        box.addItem("Server");
        partner = box.getSelectedItem().toString();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel.setLayout(new FlowLayout());
        panel1.setLayout(new BorderLayout());

        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(button3);
        toolBar.add(button5);
        toolBar.add(box);

        scrool.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(text1);
        panel.add(button4);
        panel1.add(panel, "South");
        panel1.add(toolBar, "North");
        panel1.add(scrool, "Center");
        getContentPane().add(panel1);
        pack();
        setSize(450, 450);
        setResizable(false);

    }


    private class Login extends JFrame {

        Login(){
            super("Login");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);


            JTextField login = new JTextField(20);
            JTextField Addres = new JTextField(40);

            JLabel label1=new JLabel("Login: ");
            JLabel label2=new JLabel("Addres:");
            JButton button1 = new JButton("Connect to server");
            JButton button2 = new JButton("Login");
            button2.setEnabled(false);
            button1.addActionListener(new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    try
                    {
                        socket = new Socket(Addres.getText(), 5505);
                        oos = new DataOutputStream(socket.getOutputStream());
                        ois = new DataInputStream(socket.getInputStream());
                        button2.setEnabled(true);
                        button1.setEnabled(false);
                    } catch (UnknownHostException ex) {
                        JOptionPane.showMessageDialog(null,"Failed to connect");
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Failed to connect");
                        ex.printStackTrace();
                    }
                }
            });
            button2.addActionListener(new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        String str =login.getText();
                        oos.writeUTF(str);
                        oos.flush();
                        String in = ois.readUTF();
                        if (in.equalsIgnoreCase("Name is taken already")){
                            JOptionPane.showMessageDialog(null,in);
                        }
                        else {
                            name = str;
                            partner=name;
                            Login.super.setVisible(false);
                        }
                    }
                    catch(IOException ex){ex.printStackTrace();}
                }
            });

            Box box1=Box.createHorizontalBox();
            box1.add(label1);
            box1.add(login);
            Box box2=Box.createHorizontalBox();
            box2.add(label2);
            box2.add(Addres);
            Box box3=Box.createHorizontalBox();
            box3.add(button1);
            box3.add(button2);

            Box MainBox = Box.createVerticalBox();
            MainBox.add(box1);
            MainBox.add(Box.createVerticalStrut(20));
            MainBox.add(box2);
            MainBox.add(Box.createVerticalStrut(20));
            MainBox.add(box3);
            this.setContentPane(MainBox);
            this.setSize(250,150);
            this.setVisible(true);
            this.setResizable(true);

        }

    }

    public static void main(String[] args) {
        Chat chat=new Chat("Chat");
    }

}
