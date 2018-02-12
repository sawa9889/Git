/*public class Table implements MotherStack
{
    int [][] Arr;
    int Count;
    int[] Freespace;
    int index=0;

    Table()
    {
        Arr=new int[0][0];
        Count=0;
        Freespace=new int[0];
    }

    Table(int i,int j)
    {
        Arr=new int[i][j];
        Count=i*j;
        Freespace=new int[i];
        for(int x=0;x<i;x++){Freespace[x]=j;}
    }

    Table(int[][] arr)
    {
        Arr=new int[arr.length][arr[0].length];
        Count=arr.length*arr[0].length;
        Freespace=new int[arr.length];
        for(int x=0;(x < arr.length);x++){
            for(int y=0;(y < arr[x].length);y++) {
                Arr[x][y]=arr[x][y];
            }
            Freespace[x]=0;
        }
    }

    Table(int[][] arr,int i,int j,int i1,int j1)
    {
        Arr=new int[i1-i][j1-j];
        Count=(i1-i)*(j1-j);
        Freespace=new int[i1-i];
        for(int x=0;(x<arr.length)&&(x < i1-i);x++){
            for(int y=0;(y<arr[x].length)&&(y < j1-j);y++) {
                Arr[x][y]=arr[x+i-1][y+j-1];
            }
            if (j1>arr[x].length){Freespace[x]=(j1-arr[x].length);}
            else if (j>arr[x].length){Freespace[x]=j1-j;}
        }
        if (i1>arr.length){for (int x=j1;x<arr.length;x++)
            Freespace[x]=j1-j;}
        else if (i>arr.length){for (int x=0;x<arr.length;x++)
            Freespace[x]=j1-j;}

    }

    public String getOne(String s){
        String temp="";
        for (int y=0;y<(Arr[index].length-Freespace[index]);y++) {
            temp += Arr[index][y] + " ";
        }
        if (index == Arr.length - 1) {
            index = 0;
            return s += temp;
        } else {
            index++;
            return s += temp;
        }
    }

    public Integer Counter(){
        return (Arr.length-(FreespaceSum()/Arr[index].length));
    }

    public int FreespaceSum(){//считает общее кол-во свободных ячеек
        int sum=0;
        for (int x:Freespace){ sum+=x;}
        return sum;
    }

    public Boolean delete(int n) {
        if (n > ((Arr.length * Arr[0].length)-(FreespaceSum()))) {
            return false;
        }
        int i = (n-1) / Arr.length;
        int j = (n-1) % Arr[0].length;
        for (int y = j; y < Arr[0].length-1 ; y++) {
                Arr[i][y] = Arr[i][y+1];
        }
        if (i!=(Arr.length-1)) {
            Arr[i][Arr[0].length - 1] = Arr[i + 1][0];
        }
        for (int x = i+1; x < Arr.length; x++) {
                for (int y = 0; y < Arr[0].length-1 ; y++) {
                        Arr[x][y] = Arr[x][y+1];
            }
            if (x!=(Arr.length-1)) {
                Arr[x][Arr[0].length - 1] = Arr[x + 1][0];
            }
        }
        i=0;
        j=Arr.length-1;
        while (i==0){
            if (Freespace[j]<Arr[0].length) {
                ++Freespace[j];
                i = 1;
            }
            else{
                --j;
            }
        }
        return true;
    }

    public Boolean Clear(){
        if (Count==0){return false;}
        else
        {
            Arr=new int[0][0];
            Count=0;
        }
        return true;
    }

    public boolean display(){

        System.out.println();
        for (int x=0;x<Arr.length;x++){
            for (int y=0;y<Arr[x].length;y++){
                if (y<(Arr.length-Freespace[x])){System.out.print(Arr[x][y]+" ");}
            }
            System.out.println();
        }
        return true;
    }

    public void Sort(){
        int temp=0,y=0;
        for (int x=0;x<Arr.length;x++) {
            for (int i = 0; i < Counter(); i++) {
                temp = Arr[x][i];
                y = i - 1;
                while ((y >= 0) && (Arr[x][y] > temp)) {
                    Arr[x][y + 1] = Arr[x][y];
                    y--;
                }
                Arr[x][y + 1] = temp;
            }
        }
    }

    public Integer[] Find(String s) {
        Integer[] Index = new int[0];
        Integer[] temp;
        int n = 0;
        for (int x = 0; x < Arr.length; x++) {
            for (int y = 0; y < Arr[x].length-Freespace[x]; y++) {
                if (Arr[x][y]==Integer.parseInt(s)){
                    temp=new int[++n];
                    for (int i = 0; n > 1 && i < n-1; i++) {
                        temp[i] = Index[i];
                    }
                    temp[n - 1] = (x*Arr.length)+y+1;
                    Index = temp;
                }
            }
        }
        return Index;
    }
}*/
