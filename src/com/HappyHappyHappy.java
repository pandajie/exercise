package com;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-11-7
 * @since 1.0.0
 */
public class HappyHappyHappy {
    int [] input = {2,1,5,3};

    public int solve(){
        select(input,0,input.length-1,1);
        return min;
    }

    public int childSum = 0;
    public int bobSum = 0;
    public int min = 99999;
    // p = 1 孩子选，p =-1 bob选
    public void select(int[] a,int i,int j,int p){
        if(i<=j) {
            if (p == 1) {
                if (a[i] > a[j]) {
                    childSum += a[i];
                    select(a,i+1,j,-p);
                    childSum -= a[i];
                }else if(a[i] < a[j]){
                    childSum += a[j];
                    select(a,i,j-1,-p);
                    childSum -= a[j];
                }else{
                    childSum += a[i];
                    select(a,i+1,j,-p);
                    childSum -= a[i];

                    childSum += a[j];
                    select(a,i,j-1,-p);
                    childSum -= a[j];
                }
            } else {
                //Bob先取左边，在取右边
                bobSum += a[i];
                select(a,i+1,j,-p);
                bobSum -= a[i];

                bobSum +=a[j];
                select(a,i,j-1,-p);
                bobSum -=a[j];
            }
        }else{
            if(childSum-bobSum>0) {
                min = Math.min(min, childSum - bobSum);
            }
        }
    }


    public static void main(String[] args) {
        HappyHappyHappy happy =  new HappyHappyHappy();
        int result = happy.solve();
        if(result == 99999){
            System.out.println("The child will be unhappy...");
        }else{
            System.out.println(result);
        }
    }
}
