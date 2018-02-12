import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler extends MultiThreadServer implements Runnable {

    private static Socket clientDialog;
    private String name,partner;
    private boolean Free=true;
    public DataOutputStream out;
    public DataInputStream in;
    Thread t;
    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }
    String getName(){return name;}
    String getPartner(){return name;}
    void changeFree(){Free=!Free;}
    boolean getFree(){return Free;}
    void setPartner(String name){partner=name;}
    @Override
    public void run() {

        try {
            System.out.println(clientDialog.getChannel()+ "Channel");
            System.out.println(clientDialog.getLocalAddress()+ "Address");
            out = new DataOutputStream(clientDialog.getOutputStream());
            in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");
            System.out.println("DataOutputStream  created");

            String out1;
            {
                out1=in.readUTF();
                for (String x:super.getNames()){
                    if((x!=null)&&(x.equalsIgnoreCase(out1))){out1="Name is taken already";}
                }
                out.writeUTF(out1);
                out.flush();
            }while(out1=="Name is taken already");
            name = out1;
            System.out.println("Name is "+name);

            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel "+name);

                String entry = in.readUTF();

                System.out.println("READ from " +name+ " message - " + entry);

                if (entry.equalsIgnoreCase("quit")) {

                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("quit");
                    Thread.sleep(3000);
                    break;
                }
                else if (entry.equalsIgnoreCase("connect")){
                    System.out.println("Connect to...");
                    entry = in.readUTF();
                    for (String x:super.getNames()) {
                        if ((x.equalsIgnoreCase(entry)) && (super.isFree(entry))&&(!x.equalsIgnoreCase(name))) {
                            System.out.println("Connected 1");
                            out.writeUTF("connecting");
                            out.writeUTF(x);
                            partner=x;
                            super.setPartner(x,name);
                            DataOutputStream temp;
                            super.getOutput(x);
                            temp = out;
                            super.setOutput(name, super.getOutput(entry));
                            super.setOutput(entry, temp);
                            out.writeUTF("connecting");
                            out.writeUTF(name);
                            System.out.println("Connected 2");
                            break;
                         } else if ((x.equalsIgnoreCase(entry)) && (!super.isFree(entry))){
                            out.writeUTF("Can't connect");
                        }
                    }
                }
                else if (entry.equalsIgnoreCase("close connection")) {
                    for (String x:super.getNames()) {
                        if (x.equalsIgnoreCase(partner)) {
                            System.out.println("Disconnected 1" );
                            out.writeUTF("Disconnected");
                            super.setPartner(partner,partner);
                            DataOutputStream temp;
                            super.getOutput(x);
                            temp = out;
                            super.setOutput(name, super.getOutput(partner));
                            super.setOutput(partner, temp);
                            out.writeUTF("Disconnected");
                            System.out.println("Disconnected 2" );
                            partner=name;
                        }
                    }
                }
                else if(entry.equalsIgnoreCase("Update")){
                    String str="";
                    for (String x:super.getNames()){
                        str+=x+" ";
                    }
                    out.writeUTF(str);
                    out.writeUTF(str);
                }
                else {
                    System.out.println("Server try writing to channel " + name);
                    out.writeUTF(entry);
                    System.out.println("Server Wrote message to " + name);
                    out.flush();
                }
            }
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            in.close();
            out.close();
            clientDialog.close();
            super.killConection(name);
            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}