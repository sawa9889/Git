import java.util.Random;
public class ListRobots extends List
{
    node head;
    node index;

    static private class node {
        public node next;
        private Body value;

        node(Body x) {
            value = x;
            next = null;
        }

        node() {
            next=null;
            int x;
            Random i=new Random();
            x=i.nextInt(9);
            for(Body Body:Body.values()){if (Body.ordinal()==x){value =Body;}}
        }
        public Body getValue() {
            return value;
        }
    }

    static private enum hand{
        A('a',10),B('b',15),C('c',20),D('d',17),E('e',15),F('f',13),J('j',25),H('h',50),K('k',34),L('l',16);
        private char Name;
        private int Prod;
        hand(char name,int prod){
            Name=name;
            Prod=prod;
        }
        public hand generate(){
            int x;
            Random i=new Random();
            x=i.nextInt(9);
            for(hand Hand:this.values()){if (Hand.ordinal()==x){return Hand;}}
            return this;
        }
        public char getname(){
            return Name;
        }

    }
    static private enum Leg{
        A('a',10),B('b',15),C('c',20),D('d',17),E('e',15),F('f',13),J('j',25),H('h',50),K('k',34),L('l',16);
        private char Name;
        private int Prod;
        Leg(char name,int prod){
            Name=name;
            Prod=prod;
        }
        public Leg generate(){
            int x;
            Random i=new Random();
            x=i.nextInt(9);
            for(Leg Leg:this.values()){if (Leg.ordinal()==x){return Leg;}}
            return this;
        }
        public char getname(){
            return Name;
        }

    }
    static private enum Addiction{
        A('a',10),B('b',15),C('c',20),D('d',17),E('e',15),F('f',13),J('j',25),H('h',50),K('k',34),L('l',16);
        private char Name;
        private int Prod;
        Addiction(char name,int prod){
            Name=name;
            Prod=prod;
        }
        public Addiction generate(){
            int x;
            Random i=new Random();
            x=i.nextInt(9);
            for(Addiction Addiction:this.values()){if (Addiction.ordinal()==x){return Addiction;}}
            return this;
        }
        public char getname(){
            return Name;
        }
    }

    static private enum Body{
        A("a",2,2,3,400),B("b",1,2,0,500),C("c",2,4,4,600),D("d",1,3,2,550),E("e",3,4,2,650),F("f",4,2,1,750),J("j",4,4,0,450),H("h",1,3,0,250),K("k",1,4,2,470),L("l",0,4,6,560);
        private hand[] Hand;
        private Leg[] leg;
        private Addiction[] Add;
        private String name;
        private int arms,legs,adds,time;
        Body(String Name,int Arms, int Legs, int Adds,int Time){
            name=Name;
            arms=Arms;
            legs=Legs;
            adds=Adds;
            time=Time;
            Generate();
        }
        public void Generate() {
            int x;
            Random i = new Random();
            Hand = new hand[arms];
            for (int j = 0; j < arms; j++) {
                x = i.nextInt(9);
                for (hand hand : hand.values()) {
                    if (hand.ordinal() == x) {
                        Hand[j] = hand;
                        name += hand.getname();
                        time+=hand.Prod;
                    }
                }
            }

            leg = new Leg[legs];
            for (int j = 0; j < legs; j++) {
                x = i.nextInt(9);
                for (Leg Leg : Leg.values()) {
                    if (Leg.ordinal() == x) {
                        leg[j] = Leg;
                        name += Leg.getname();
                        time+=Leg.Prod;
                    }
                }
            }

            Add = new Addiction[adds];
            for (int j = 0; j < adds; j++) {
                x = i.nextInt(9);
                for (Addiction Addiction : Addiction.values()) {
                    if (Addiction.ordinal() == x) {
                        Add[j] = Addiction;
                        name += Addiction.getname();
                        time+=Addiction.Prod;
                    }
                }
            }
        }
        String getname(){return name;}
}


    static private class random implements Runnable
    {
        Thread t;
        ListRobots List;
        private int Count;

        random(int count,ListRobots list) {
            t=new Thread(this,"Randomize1");
            Count=count;
            List=list;
            t.start();
        }

        public void run() {
            try {
                System.out.println("123");
                int x;
                Random i = new Random();
                while (Count > 0) {
                    x = i.nextInt(9);
                    for (Body Body : Body.values()) {
                        if (Body.ordinal() == x) {
                            List.Add(Body);
                            t.sleep(Body.time);
                        }
                    }
                    Count--;
                    List.display();
                    System.out.println();
                }
            }
            catch(InterruptedException e){
                System.out.println(e+" "+Count);
            }
        }
    }

    void Randomize(int count){
        random obj=new random(count,this);
        try{
            obj.t.join();
        }
        catch(InterruptedException e){
            System.out.println(e+" ");
        }
    }

    public ListRobots() {
        super();
    }

    public ListRobots(Body[] arr, int n) {
        node a;
        for (int i=0;(i<arr.length)&&(i<n);i++){
            a=new node(arr[i]);
            a.next=head;
            head=a;
        }
        Countnodes=n;
        index=head;
    }

    public void Add(Body x){
        node a=head;
        while (a!=null){
            a=a.next;
        }
        a = new node(x);
        a.next=head;
        head=a;
        Countnodes++;
    }

    public boolean display(){
        node a=head;
        int i=0;
        while (a!=null){
            System.out.print(a.value.getname()+" node "+(i++)+"\n");
            a=a.next;
        }
        return true;
    }

    public ListRobots(Body[] arr) {
        node a;
        int n=0;
        for (Body i:arr){
            a=new node(i);
            a.next=head;
            head=a;
            n++;
        }
        Countnodes=n;
        index=head;
    }

    public ListRobots(Body[] arr, int i, int j) {
        node a;
        for (int x=i;(i<arr.length)&&(x<j);x++){
            a=new node(arr[x]);
            a.next=head;
            head=a;
        }
        if ((i<j)&&(j<arr.length)){Countnodes=j-i;}
        else{Countnodes=arr.length-i;}
        index=head;
    }

    public ListRobots(ListRobots A) {
        super(A);
    }

    @Override
    public void Sort() {
        int i = 0, j;
        node temp, temp2;
        node a = head, b;
        while (a != null) {
            b = head;
            i++;
            temp = new node(a.value);
            j = i - 1;
            while ((j > 0) && (a.getValue().name.compareTo(b.getValue().name)>0) && (b != null)) {
                b = b.next;
                j--;
            }
            temp = new node(b.value);
            b.value = a.value;
            while ((j > 0) && (a.getValue().name.compareTo(b.getValue().name)<=0) && (b.next != null)) {
                temp2 = new node(b.next.value);
                b.next.value = temp.value;
                temp.value = temp2.getValue();
                b = b.next;
                j--;
            }
            a = a.next;
        }
    }

}
