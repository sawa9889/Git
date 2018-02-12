import java.util.ArrayList;
import java.util.Random;
public class Map {

    public ArrayList<island> islands=new ArrayList();


    public class Element{
        byte Level;
        byte n=5;
        object object;

        Element(int x,int y) {
            object=new object();
            Random j=new Random();
            Integer i;
            i=j.nextInt(1001);
            if(i<=n) {islands.add(new island(x,y));}
        }

        public class object{
            byte element;
            String Color;

            object(){
                element=0;
            }

        }

    }

    public class island{
        ArrayList<Integer> Bx=new ArrayList();
        ArrayList<Integer> By=new ArrayList();

        island(Integer x,Integer y){
            Bx.add(x);
            By.add(y);
        }

    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    Element[][] map;

    Map(){
        map=generate(1000,1000);
    }

    Map(Element[][] Map){
        map=Map;
    }

    Element[][] generate(int heith,int witdh){
        Element[][] Map=new Element[heith][witdh];
        for(int x=0;x<heith;x++){
            for(int y=0;y<witdh;y++){
                Map[x][y]=new Element(x,y);
            }
        }
        byte i=1;
        for (int x=0;x<islands.size();x++){genIsl(islands.get(x),i,50,Map);}
        return Map;
    }

    void genIsl(island land,byte level,int n,Element[][] map){
        Random j=new Random();
        Integer i;
        int X,Y;
        String Color="";
        i=j.nextInt(5);
        switch (i) {
            case 0:
                Color = ANSI_RED;
                break;
            case 1:
                Color = ANSI_BLUE;
                break;
            case 2:
                Color = ANSI_GREEN;
                break;
            case 3:
                Color = ANSI_CYAN;
                break;
            case 4:
                Color = ANSI_YELLOW;
                break;
        }
        for (int x=0;x<land.Bx.size();x++) {
            X = land.Bx.get(x);
            Y = land.By.get(x);
            map[X][Y].Level = level;
            i = j.nextInt(101);
            if (i <= 5 - 1) {
                map[X][Y].object.element = 4;
            } else if (i <= 10 - 1) {
                map[X][Y].object.element = 5;
            } else if (i <= 15 - 1) {
                map[X][Y].object.element = 6;
            } else {
                map[X][Y].object.element = level;
                map[X][Y].object.Color=Color;
            }
            i = j.nextInt(101);
            if (X+1<map.length) {
                if((map[X + 1][Y].Level < level)&&(i<n)) {
                    land.Bx.add(X + 1);
                    land.By.add(Y);
                    n--;
                }
            }
            if (X-1>0) {
                if((map[X - 1][Y].Level < level)&&(i<n)) {
                    land.Bx.add(X - 1);
                    land.By.add(Y);
                    n--;
                }
            }
            if (Y+1<map[0].length) {
                if((map[X][Y+1].Level < level)&&(i<n)) {
                    land.Bx.add(X);
                    land.By.add(Y+1);
                    n--;
                }
            }
            if (Y-1>0) {
                if((map[X][Y-1].Level < level)&&(i<n)) {
                    land.Bx.add(X);
                    land.By.add(Y-1);
                    n--;
                }
            }
        }

    }

/*    void select(int x,int y,String color){
        switch (color) {
            case "red":
                map[x][y]=ANSI_RED+map[x][y]+ANSI_RESET;
                break;
            case "yellow":
                map[x][y]=ANSI_YELLOW+map[x][y]+ANSI_RESET;
                break;
            case "blue":
                map[x][y]=ANSI_BLUE+map[x][y]+ANSI_RESET;
                break;
            case "green":
                map[x][y]=ANSI_GREEN+map[x][y]+ANSI_RESET;
                break;
            case "black":
                map[x][y]=ANSI_BLACK+map[x][y]+ANSI_RESET;
                break;
        }

    }*/

    double sqr(Double x){ return x*x;}

    void display(Double x,Double y,Double r) {
        String s="";
            for(Double x1=x-r , y1=y-r;(y1<=(y+r));x1++) {

                if ((x1>0)&&(y1>0)&&(y1<map[0].length)&&(x1<map.length)&&(sqr(x1-x)+sqr(y1-y)<=r*r)){s+=map[x1.intValue()][y1.intValue()];}
                else{s+=" ";}

                if (x1 == x + r) {
                    y1++;
                    x1=x-r;
                    System.out.println(s);
                    s="";
                }
            }
    }

    void display() {
    for (int x=0;x<map.length;x++){
        for(int y=0;y<map[0].length;y++){
            byte i=map[x][y].object.element;
            byte j=map[x][y].Level;
            if (i==0){
                System.out.print(' ');
            }
            else if ((i>=1)&&(i<=3)){
                System.out.print(map[x][y].object.Color + i +ANSI_RESET);
            }
            else{System.out.print(i);}
            /*if ((x==0)||(x==map.length-1)||(y==0)||(y==map[0].length-1)){System.out.print(' ');}
            else if((i>=0)&&(i<=3)&&(j==map[x+1][y].Level)&&(j==map[x-1][y].Level)&&(j==map[x][y+1].Level)&&(j==map[x][y-1].Level)) {System.out.print(' ');}
            else if((i>=0)&&(i<=3)&&(j!=map[x+1][y].Level)&&(j!=map[x-1][y].Level)&&(j==map[x][y+1].Level)&&(j==map[x][y-1].Level)) {System.out.print(ANSI_WHITE+'-'+ANSI_RESET);}
            else if((i>=0)&&(i<=3)&&(j==map[x+1][y].Level)&&(j==map[x-1][y].Level)&&(j!=map[x][y+1].Level)&&(j!=map[x][y-1].Level)) {System.out.print(ANSI_WHITE+'|'+ANSI_RESET);}
            else if((i>=0)&&(i<=3)&&(j!=map[x+1][y].Level)&&(j==map[x-1][y].Level)&&(j==map[x][y+1].Level)&&(j==map[x][y-1].Level)) {System.out.print(ANSI_WHITE+'^'+ANSI_RESET);}
            else if((i>=0)&&(i<=3)&&(j==map[x+1][y].Level)&&(j!=map[x-1][y].Level)&&(j==map[x][y+1].Level)&&(j==map[x][y-1].Level)) {System.out.print(ANSI_WHITE+'v'+ANSI_RESET);}
            else if((i>=0)&&(i<=3)&&(j==map[x+1][y].Level)&&(j==map[x-1][y].Level)&&(j!=map[x][y+1].Level)&&(j==map[x][y-1].Level)) {System.out.print(ANSI_WHITE+'>'+ANSI_RESET);}
            else if((i>=0)&&(i<=3)&&(j==map[x+1][y].Level)&&(j==map[x-1][y].Level)&&(j==map[x][y+1].Level)&&(j!=map[x][y-1].Level)) {System.out.print(ANSI_WHITE+'<'+ANSI_RESET);}
            else if (i==4){System.out.print(ANSI_GREEN+'^'+ANSI_RESET);}
            else if (i==5){System.out.print(ANSI_CYAN+'@'+ANSI_RESET);}
            else{System.out.print(' ');}*/
        }
        System.out.println();
    }
    }


}
