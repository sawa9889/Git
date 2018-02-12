import java.awt.*;
public class main {


    public static void main(String[] args) {
        Base base=new Base();
        base.pack();
        base.setSize(new Dimension(200,200));
        base.setVisible(true);
        Map map=new Map();
        System.out.println("0"=="0");
        //for(int x=0,y=99;x<100;x++,y--) {
         //       map.select(x,x,"red");
         //       map.select(y,x,"yellow");
         //   }
        map.display();
    }
}
