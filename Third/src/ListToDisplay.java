public class ListToDisplay {

    static public class node
    {
        protected node next;
        protected MotherStack value;

        node(MotherStack x)
        {
            value=x;
            next=null;
        }

        node()
        {
            next=null;
        }
    }

    node head;
    int Countnodes=0;

    ListToDisplay(){
        head=null;
    }

    ListToDisplay(MotherStack ...A){
        for(MotherStack x:A){
            node a=new node(x);
            a.next=head;
            head=a;
            Countnodes++;
        }
    }
    public void Add(MotherStack A){
        node a=new node(A);
        a.next=head;
        head=a;
        Countnodes++;
    }

    synchronized public void Display(int witdh,String s) {
        String Witdh = "";
        Boolean Switch = true;
        node a;
        String temp="";
        int i = 0;
        for (int x = 0; x < witdh; x++) {
            Witdh += " ";
        }
        while (Switch == true) {
            a = head;
            Switch = false;
            while (a != null) {
                temp="";
                if (i < a.value.Counter()) {
                    temp += a.value.getOne(temp);
                    int len=temp.length();
                    for (int x = 0; x < witdh-len; x++){temp += " ";}
                    Switch=true;
                }else{temp=Witdh;}
                System.out.print(temp+s);
                a=a.next;
            }
            System.out.println();
            i++;
        }
    }


}

