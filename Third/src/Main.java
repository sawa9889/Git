class Display implements Runnable{
    Thread t;
    ListToDisplay List;
    String S="";
    int Witdh;

    Display(ListToDisplay list,int witdh,String s,String Name){
        t=new Thread(this,Name);
        S=s;
        List=list;
        Witdh=witdh;
        System.out.println( Name+" started"+"\n");
        t.start();
    }

    public void run() {
        List.Display(Witdh, S);
    }
}



public class Main {



    public static void main(String[] args) {
        List<Integer> lst3=new List<Integer>();
        lst3.Add(1);
        lst3.Add(2);
        lst3.Add(3);
        System.out.println(lst3.Countnodes);
        lst3.display();
        ListRobots lst=new ListRobots();
        lst.Randomize(10);
        Double i = 0.0;
        Integer[] Arr = new Integer[5];
        String[] Arr2 = new String[5];
        String[][] Arr1 = new String[5][5];
        for (Integer x = 0; x < 5; x++) {
            Arr[x] = x;
            i=x*1.2;
            Arr2[x]=(i).toString();
            for (Integer y = 0; y < 5; y++) {
                i=-x+y*1.5;
                Arr1[x][y] = (i).toString();
            }
        }
        ListRobots List = new ListRobots();
        List.display();
        System.out.println();
        List.Sort();
        List.display();


        List<Integer> lst1 = new List<Integer>(Arr,0,5);
        List<Integer> lst2 = new List<Integer>(Arr,0,5);
        ListToDisplay List1=new ListToDisplay();
        List1.Add(lst1);
        List1.Add(lst2);

        Display obj1=new Display(List1,20,"|","Display1");

        try {
            obj1.t.join();
        }
        catch(InterruptedException e){System.out.println(e);}
        System.out.print(" End of programme ");
        }
    }

