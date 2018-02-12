import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author mercenery
 *
 */
public class MultiThreadServer {
    int i=0;
    static private class ConnectClient extends MultiThreadServer implements Runnable{
        ServerSocket Server;
        int Port;
        Thread t;
        ConnectClient(int port){
            try
            {
            t=new Thread(this,"Console");
            Port=port;
            Server= new ServerSocket(port);
                System.out.println(Server.toString());
            t.start();
            }catch(IOException e){e.printStackTrace();}
        }

        public void run() {
            try
            {
                while (!Server.isClosed()) {
                    Socket client = Server.accept();
                    System.out.println(client.getInetAddress());
                    System.out.println(Server.getChannel() + " Channel");
                    System.out.println(Server.getInetAddress() + " Address");
                    System.out.println(Server.toString());
                    Threads.add(new MonoThreadClientHandler(client));
                    i=Threads.size()-1;
                    executeIt.execute(Threads.get(i));
                    System.out.print("Connection accepted.");
                }
            }catch(IOException e){e.printStackTrace();}
        }

    }


    private static ExecutorService executeIt = Executors.newFixedThreadPool(3);
    private static ArrayList<MonoThreadClientHandler> Threads=new ArrayList();

    static protected ArrayList<String> getNames(){
        ArrayList<String> str=new ArrayList<>();
        for (MonoThreadClientHandler x:Threads){
            str.add(x.getName());
        }
        return str;
    }

    static protected String getPartner(String name){
        for (MonoThreadClientHandler x:Threads){
            if (x.getName().equalsIgnoreCase(name)){
                return x.getPartner();
            }
        }
        return "";
    }

    static protected void setPartner(String name,String partner){
        for (MonoThreadClientHandler x:Threads)
            if (x.getName().equalsIgnoreCase(name))
                x.setPartner(partner);
    }

    static protected DataOutputStream getOutput(String name){
        for (MonoThreadClientHandler x:Threads){
            if (x.getName().equalsIgnoreCase(name)){
                return x.out;
            }
        }
        return null;
    }
    static protected void setOutput(String name,DataOutputStream Out){
        for (MonoThreadClientHandler x:Threads){
            if (x.getName().equalsIgnoreCase(name)){
                x.out=Out;
                x.changeFree();
            }
        }
    }

    static boolean isFree(String name){
        for (MonoThreadClientHandler x:Threads){
            if (x.getName().equalsIgnoreCase(name)){
                return x.getFree();
            }
        }
        return false;
    }

    static protected void killConection(String name){
        int j=0;
        for (String x:getNames()){
            if (x.equalsIgnoreCase(name)) {
                Threads.remove(j);
            }
            else{j++;}
        }
    }



    /**
     * @param args
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created, command console reader for listen to server commands");
            ConnectClient Client=new ConnectClient(5505);
            while (!Client.Server.isClosed()) {
                if (br.ready()){
                  String command=br.readLine();
                  if (command.equalsIgnoreCase("quit")){
                      System.out.println("Close server");
                      executeIt.shutdown();
                      Client.Server.close();
                      System.out.println("Server Closed");
                  }
                  else if(command.equalsIgnoreCase("show names")){
                      for(String x:getNames()){
                          System.out.println(x);
                      }
                  }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
