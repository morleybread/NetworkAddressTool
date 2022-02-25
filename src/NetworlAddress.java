
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

//输入ip地址
//输入子网掩码
//得到网络号
public class NetworlAddress {
    public ArrayList<Integer> iplist=new ArrayList<>();
    public ArrayList<Integer> subnetmasklist =new ArrayList<>();
    public ArrayList<ArrayList<Integer>> resultlist=new ArrayList<>();
    public  ArrayList <Integer> lasted;
    public int[] arry;


    public static void main(String[] args) {
        NetworlAddress networlAddress=new NetworlAddress();
        networlAddress.setip("141.14.72.24");
        networlAddress.setsetsubnetwork("255.255.224.0");
        networlAddress.generate();
    }

    public void  setip(String ip) {
        String[] sub = ip.split("\\."); //把ip字符串以点分隔
        for (String str : sub) {
            iplist.add(Integer.parseInt(str)); //转换为 整数类型
        }
        System.out.println(iplist);
    }

    public void  setsetsubnetwork(String ip) {
        String[] sub = ip.split("\\."); //把ip字符串以点分隔
        for (String str : sub) {
            subnetmasklist.add(Integer.parseInt(str)); //转换为 整数类型
        }
        System.out.println(subnetmasklist);
    }

public void generate() {
    resultlist.add(iplist);
    resultlist.add(subnetmasklist);
    for (int j = 0; j < 4; j++) {
        arry = new int[2];
        for (int i = 0; i < 2; i++) { //循环从第二个开始           //也就是 每列比较
            arry[i] = resultlist.get(i).get(j);
        }
        System.out.println(Arrays.toString(arry));
        ACLpare my = new ACLpare();
        System.out.println(my.mainin(arry));

    }
}




    public static class ACLpare {
        int [] a ; //给一个数组  遍历
        ArrayList<char[]> twoD=new ArrayList<>();//存储两行 8位字符列表
        ArrayList<Integer> arrys =new ArrayList<>();// 存储结果
        ArrayList<Integer> latestResult=new ArrayList<>();//返回
        public int mainin(int[] m){
            if(m[0]>m[1]){
                return 0;
            }
            a=m;

            for (int k : a) {
                pares(k);
            }


       /*
       1111 1111 掩码
       0000 1111
       ------------
       0000 1111

       1110 0000 掩码
       1000 0000
       1000 0000


        */

            //返回掩码有多少个一
            int s=0;
            for(char ss:twoD.get(1)){
                if (ss=='1'){
                    s++;
                }
                else {
                    break;
                }
            }




            for(int j=0;j<s;j++)//twoD.get(0).length为 8 //循环8次//获取一个字符数组长度 列  比列
            {

                arrys.add((int) twoD.get(0)[j]-48);


            };
            for(int p=arrys.size();p<8;p++){
                arrys.add(0);

            }




//            boolean flag=false;
//            for(int i=1;i<twoD.size();i++){         //有多少个数组 twoD.size()为行
//                if(twoD.get(i)[j]!=twoD.get(i-1)[j]){        //竖向遍历
//                    flag=true;
//                }
//            }
//            if (flag){
//                arrys.add(0);
//            }else
//            {
//               arrys.add(1);
//            }
//        }



//
//resultarry 为最终结果
            StringBuilder myresult= new StringBuilder();
            for (Integer integer : arrys) {
                myresult.append(integer);
            }

            latestResult.add(parseInt(String.valueOf(myresult),2));

            return latestResult.get(0);

        }



        public  char [] zerorepaire(int num,int []arr){//返回补齐的0
            //用0补齐8位

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

        public  void pares (int num){  //转为8位 字符数组

            String binary=Integer.toBinaryString(num);
            char [] chararry= this.zerorepaire(num,a);
            char[] rechar= binary.toCharArray();

            //合并字符数组
            char  [] result0=new char[chararry.length+rechar.length];
            System.arraycopy(chararry,0,result0,0,chararry.length);
            System.arraycopy(rechar,0,result0,chararry.length,rechar.length);
            twoD.add(result0);


        }





    }

}
