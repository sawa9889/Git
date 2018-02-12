
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientLauncher {

    static class Input extends Thread {
        DataInputStream ois;

        Input(DataInputStream Ois) {
            ois = Ois;
        }

        public void run() {
            try {
                while (true) {
                    String in = ois.readUTF();
                    System.out.println(in);
                    if (in == "quit") {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String name;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            Input In = new Input(ois);
            In.start();
            System.out.println("Please enter name: ");
            oos.writeUTF(br.readLine());
            oos.flush();
            System.out.println("Client connected to socket.");
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");

            while (!socket.isOutputShutdown()) {

                if (br.ready()) {
                    System.out.println("Client start writing in channel...");
                    String clientCommand = br.readLine();
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Clien sent message " + clientCommand + " to server.");
                    if (clientCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Client kill connections");
                        System.out.println("reading...");
                        String in = ois.readUTF();
                        System.out.println(in);
                        break;
                    }
                }
            }
            System.out.println("Closing connections & channels on clentSide - DONE.");
        } catch (UnknownHostException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }


}