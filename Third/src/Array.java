/*public class Array<Type extends Number> implements MotherStack
{
    public Type[] Arr;// Массив
    private int Count;//счетчик элементов
    private int Freespace;//Счетчик свободного места(Если создали пустой массив например , то все места пустые)
    private int index=0;//для дисплея счетчик

    /*Array()
    {
        Arr=new Type[1];
        Count=0;
        Freespace=0;
    }

    Array(int n)//по кол-ву эл-ов
    {
        Arr=new Type[n];
        Count=n;
        Freespace=n;

    }

    Array(Type[] arr,int n)//по массиву и кол-ву эл-ов
    {
        Arr=arr;
        Count=n;
        if (arr.length>n){Freespace=arr.length-n;}
        else {
            Freespace = 0;
            for (int i = 0; i < n-arr.length; i++) {
                delete(Count-i);
            }
        }
    }

    Array(Type[] arr)//по массиву просто
    {
        Arr=arr;
        Count=arr.length;
        Freespace=0;
    }
    Array(Type[] arr,int i,int j)//по массиву от i до j
    {
        try {
            int n=0;
            for (int x = i;i<j && x < j; x++) {
                Arr[n++] = arr[x];
            }
            Count=n;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e+"Enter another size");
        }
    }

    void Add(Type n)// в конец добавляет
    {
        Type[] arr=Arr;
        Array<Type> array=new Array<Type>(Arr,++Count);
        array.Arr[Count-2]=n;
        Arr=array.Arr;
    }

    void AddInPos(Type x,int n)// помещение в позицию
    {
        Array<Type> array;
        if (Freespace == 0) {
            if ((n < (Count)) && (n > 0)) {
                array=new Array<Type>(Arr,++Count);
                for (int i = Count - 1; i > (n-1); i--) {
                    array.Arr[i] = array.Arr[i - 1];
                }
                array.Arr[n-1]=x;
                Arr = array.Arr;
            }
        } else {
            if ((n > 0) && (n < (Count - Freespace))) {
                array=new Array<Type>(Arr,Count);
                for (int i = Count - (1+Freespace); i > (n-1); i--) {
                    array.Arr[i] = array.Arr[i - 1];
                }
                array.Arr[n - 1] = x;
                Freespace--;
                Arr = array.Arr;
            } else if ((n > (Count - Freespace)) && (n < Count)) {
                Freespace--;
                Add(x);
            }
        }
    }

    public String getOne(String s)//Метод для дисплея, дает одно значение
    {
        if (index<Counter()-1){return s+=Arr[index++];}
        else{index=0;
        return s+=Arr[Counter()-1];}
    }

    public Integer Counter(){// возвращает кол-во несвободных эл-ов
        return Count-Freespace;
    }

    public Boolean delete(int n)// удаляет из заданной позиции
    {
        if (n>Count){ return false;}
        else
        {
            Array<Type> array=new Array<Type>(Arr,--Count);
            for (int i=0;i<n-1;i++){array.Arr[i]=Arr[i]; }
            for (int i=n-1;(i>0)&&(i<Count-1);i++){array.Arr[i]=Arr[i+1]; }
            Arr=array.Arr;
        }
        return true;
    }

    public Boolean Clear(){// чистит весь массив, делает его совсем пустым
        if (Count==0){return false;}
        else
        {
            Arr=null;
            Count=0;
        }
        Freespace=0;
        return true;
    }

    public boolean display(){
        for (int x=0;x<Count-Freespace;x++){System.out.print(Arr[x]+" ");
        }
        System.out.println("Freespace = " + Freespace);
        return true;
    }

    public void Sort(){// сортировка вставками
        Type temp=Arr[1];
        int  j=0;
        for (int i = 0; i < Counter(); i++) {
            temp = Arr[i];
            j = i - 1;
            while ((j >= 0) && (Arr[j].doubleValue()<temp.doubleValue())) {
                Arr[j + 1] = Arr[j];
                j--;
            }
            Arr[j + 1] = temp;
        }
    }

    public Integer[] Find(String s){// Найти места где расположено значение
        Integer[] arr=new Integer[0];
        Array<Integer> Index=new Array<Integer>(arr);
        int n=0;
        for (int x=0;x<(Count-Freespace);x++)
        {
            if (Integer.parseInt(s)==Arr[x].intValue())
            {
                Index.Add(x);
            }
        }
        return Index.Arr;
    }
}*/
