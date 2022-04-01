import java.util.ArrayList;
import java.util.Arrays;

//ACL 子网掩码计算器

public class ACLcalculator {
    static int p = 0;
    static int [] arrya ; //给一个数组  遍历
    static int b, temps;
    static ArrayList <Integer> preresult = new ArrayList<>();
    static ArrayList <Integer> list;
    static ArrayList <Integer> list2;
    static ArrayList<Integer> rearresult = new ArrayList<>();
    static  ArrayList<ArrayList<Integer>> genlist = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("简单的ACL通配符转换c类 ip");
        System.out.println("输入ip地址的范围，一共两次 敲完一个，回车");
        paresm("192.168.8.0");
        paress("192.168.15.255");
        //执行后list1 list2分别存储两个ip地址
        genate();//genlist为所有ip地址  每个ip地址用arrlist存储
        precmpare();
        System.out.println("=============");
        rearcmpare();//生成结果
        System.out.println("\n");
        System.out.println(preresult);
        System.out.println(rearresult);
    }

    public static void precmpare() {  //生成结果（前缀）
        for (int j = 0; j < 4; j++) { //列
            int temp = 255;
            for (int i = 1; i <= b; i++) { //行
                temp = temp & genlist.get(i).get(j);

            }
            System.out.println(temp);
            preresult.add(temp);
        }
    }

    public static void rearcmpare() { //生成结果（后缀）
        for (int j = 0; j < 4; j++) {
            arrya = new int[b];
            for (int i = 0; i < b; i++) { //循环从第二个开始           //也就是 每列比较
                arrya[i] = genlist.get(i).get(j);
            }
            System.out.println(Arrays.toString(arrya));
            ACLpare my = new ACLpare();
            System.out.println(my.mainin(arrya));
            rearresult.add(my.mainin(arrya));

        }

    }
    public  static void recive() {
		/*
		Scanner my=new Scanner(System.in);
		String name = my.nextLine();
		pares(name);
		Scanner my2=new Scanner(System.in);
		String name2 = my2.nextLine();
		 paress(name2);
		*/
    }


    public static void  paresm(String ip) {

        String[] gu = ip.split("\\."); //把ip字符串以点分隔
        list = new ArrayList<>();
        for (String str : gu) {
            list.add(Integer.parseInt(str)); //转换为 整数类型
        }
        System.out.println(list);
    }

    public static void  paress(String ip) {
        String[] gu = ip.split("\\."); //把ip字符串以点分隔
        list2 = new ArrayList<>();
        for (String str : gu) {
            list2.add(Integer.parseInt(str));//转换为 整数类型
        }
        System.out.println(list2);
    }


    public static void genate() {
        b = list2.get(3) - list.get(3); // b为要生成的ip个数
        int a = list.get(3);             //a为ip地址 第四个字节 数
        for (int i = 0; i <= b; i++) {
            list.set(3, a + i);           //生成要生成ip的第四个字节的数值
            genlist.add(new ArrayList<>());  //添加 Arrylist数组
            for (int j = 0; j < 4; j++) {
                genlist.get(i).add(list.get(j)); //为每个ip添加 list 四个的字节 第四个字节是 动态变化的
            }
        }
    }

    //静态内部类
     public static class ACLpare {
           int [] a ; //给一个数组  遍历
           ArrayList<char[]> twoD=new ArrayList<>();
           ArrayList<Integer> resultarry=new ArrayList<>();
           ArrayList<Integer> latestResult=new ArrayList<>();
//
//    public static void main(String[] args) {
//        ACLpare my=new ACLpare();
//        System.out.println(my.mainin(new int[]{192,192,192}));
//    }

    public int mainin(int[] m){
        this.a=m;
        for (int k : a) {
            pares(k);
        }


        for(int j=0;j<this.twoD.get(0).length;j++)
        //循环8次
        //获取一个字符数组长度 列
        {
            boolean flag=true;
            for(int i=1;i<this.twoD.size();i++){         //有多少个数组   行

                if(this.twoD.get(i)[j]!=this.twoD.get(i-1)[j]){        //竖向遍历
                    flag=false;

                }
            }

            if (flag){
                this.resultarry.add(0);
            }else
            {
                this.resultarry.add(1);
            }


        }
        StringBuilder myresult= new StringBuilder();
        for (Integer integer : this.resultarry) {
            myresult.append(integer);
        }

        this.latestResult.add(Integer.parseInt(String.valueOf(myresult),2));

        return latestResult.get(0);

    }



    public  char [] zerorepaire(int num,int []arr){//补齐0

        Arrays.sort(arr);
        int max= arr[arr.length-1];
        String binary=Integer.toBinaryString(max);
        char [] maxs= binary.toCharArray();
        String binarys=Integer.toBinaryString(num);
        char [] me= binarys.toCharArray();
        char [] aresult=new char[maxs.length-me.length];
        Arrays.fill(aresult,'0');
        return aresult;

    }

    public  void pares (int num){

        String binary=Integer.toBinaryString(num);
        char [] chararry= this.zerorepaire(num,this.a);

        char[] rechar= binary.toCharArray();
        //合并字符数组
        char  [] result0=new char[chararry.length+rechar.length];
        System.arraycopy(chararry,0,result0,0,chararry.length);
        System.arraycopy(rechar,0,result0,chararry.length,rechar.length);
        this.twoD.add(result0);

//        for(char a:result0){
//            System.out.println(a);
//        }




    }

}



}