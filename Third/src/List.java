import java.util.Random;
public class List<Type extends Number> implements MotherStack
{
    node head;
    int Countnodes;
    node index;


    static private class node<Type extends Number>
    {
        public node next;
        public Type value;
        node(Type x)
        {
            value=x;
            next=null;
        }

        Type getValue()
        {
            return value;
        }
    }

    List()
    {
        head=null;
        Countnodes=0;
        index=head;
    }

    List(Type[] arr,int n)
    {
        node a;
        for (int i=0;(i<arr.length)&&(i<n);i++){
            a=new node(arr[i]);
            a.next=head;
            head=a;
        }
        Countnodes=n;
        index=head;
    }
    List(Type[] arr)
    {
        node a;
        int n=0;
        for (Type i:arr){
            a=new node(i);
            a.next=head;
            head=a;
            n++;
        }
        Countnodes=n;
        index=head;
    }

    List(Type[] arr,int i,int j)
    {
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

    List(List A)//конструктор копирования
    {
        node temp = A.head;
        node a;
        for(int i=0;i<A.Countnodes;i++){
            a=new node(temp.value);
            a.next=head;
            head=a;
            temp=temp.next;
        }
        index=head;
    }

    public void Add(Type x) {
        node a = head;
        while (a != null) {
            a = a.next;
        }
        a = new node(x);
        a.next=head;
        head=a;
        Countnodes++;

    }


    public String getOne(String s){
        node x;
        if (index==null) {
            index = head;
        }
            x=new node(index.getValue());
            index = index.next;
        return s+=x.value;
    }

    public Integer Counter(){
        return Countnodes;
    }

    public Boolean delete(int n){
        int i=0;
        node a=head;
        if (n<Countnodes){
            while (i<(n-1)) {a=a.next;}
            node b=a.next;
            a=a.next.next;
            b=null;
            return true;
        }
        else{return false;}
    }

    public Boolean Clear()
    {
        node a=head;
        while (a.next!=null){
            a=a.next;
            head.next=null;
            head=a;
        }
        return false;
    }

    public boolean display(){
        node a=head;
        int i=0;
        while (a!=null){
            System.out.print(a.value+" node "+(i++)+"\n");
            a=a.next;
        }
        return true;
    }


    public void Sort() {
        int i = 0, j;
        node  temp,temp2;
        node a = head, b;
        while (a != null) {
            b = head;
            i++;

            j = i - 1;
            while ((j > 0) && (b.value.doubleValue() < a.value.doubleValue()) && (b != null)) {
                b = b.next;
                j--;
            }
            temp =new node (b.value);
            b.value=a.value;
            while ((j > 0) && (b.value.doubleValue() >= a.value.doubleValue()) && (b.next != null)) {
                temp2=new node(b.next.value);
                b.next.value = temp.value;
                temp.value = temp2.getValue();
                b = b.next;
                j--;
            }
            a = a.next;
        }

    }

    public Integer[] Find(String s){
        node a=head;
        Integer[] Index=new Integer[0];
        Integer[] temp;
        int n=0;
        int j=0;
        while (a.next!=null){
            if (s==a.value.toString())
            {
                temp=new Integer[++n];
                try {
                    for (int i = 0; n > 1 && i < n; i++) {
                        temp[i] = Index[i];
                    }
                    temp[n - 1] = j;
                    Index = temp;
                }
                catch(ArrayIndexOutOfBoundsException e){System.out.println(e+" Find");
                }
            }
            a=a.next;
            j++;
        }
        return Index;
    }

}
