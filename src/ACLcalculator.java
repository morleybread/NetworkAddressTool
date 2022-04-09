import java.util.ArrayList;
import java.util.Arrays;

//ACL 子网掩码计算器

public class ACLcalculator {
    static  ArrayList<ArrayList<Integer>> temps =new ArrayList<>();//存储四个比较数组
    static int  numbers; //b是要生成的ip个数
    static ArrayList <Integer> preresult = new ArrayList<>();
    static ArrayList <Integer> list;//开始ip
    static ArrayList <Integer> list2;//结束ip
    static ArrayList<Integer> rearresult = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("简单的ACL通配符转换c类 ip");
        System.out.println("输入ip地址的范围，一共两次 敲完一个，回车");
        paresm("192.168.8.0");
        paress("192.168.88.255");
        //执行后list1 list2分别存储两个ip地址
        generate();
        precmpare(); //前比
        System.out.println("=============");
        rearcmpare();//后比 生成结果
        System.out.println("\n");
        System.out.println("前缀："+preresult); //相同为1不同为0
        System.out.println("后缀："+rearresult);//相同为0不同为1
    }

    public static void precmpare() {  //生成结果（前缀）
        for (int j = 0; j < 4; j++) { //列
            int temp = 255;
            int count=temps.get(j).size();
            for (int i = 0; i < count; i++) { //行
                temp = temp & temps.get(j).get(i);

            }
            System.out.println(temps);
            preresult.add(temp);
            System.out.println(preresult);
        }
    }

    public static void rearcmpare() { //生成结果（后缀）
        for (int j = 0; j < 4; j++) {

            ACLpare acl=new ACLpare();
            Integer [] cmparelist=new  Integer[temps.get(j).size()];
            temps.get(j).toArray(cmparelist);
            rearresult.add(new ACLpare().mainin(Arrays.stream(cmparelist).mapToInt(Integer::valueOf).toArray()));
        }
        System.out.println(rearresult);
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


    public  static  void  generate(){
        for(int i=0;i<=3;i++){
            int flagnumber=list.get(i);   //list记录 list的 第1-4 字节
           numbers= list2.get(i)-flagnumber;//number为要生成的个数 记录两个ip地址对应的第一 第二 第三 第四个 字节的差值
            ArrayList<Integer> ctrate=new ArrayList<>(); //每个ctrate存储 要比较的 数组序列
            for(int j=0;j<=numbers;j++){
                ctrate.add(flagnumber+j);
            }
            temps.add(ctrate);

        }


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




    //静态内部类
     public static class ACLpare {
           int [] a ; //给一个数组  遍历
           ArrayList<char[]> twoD=new ArrayList<>();
           ArrayList<Integer> resultarry=new ArrayList<>();
           ArrayList<Integer> latestResult=new ArrayList<>();

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