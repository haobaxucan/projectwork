package com.java_jiji.suanfa.demo.datastruct;

/**
 * Created by xc on 2019/8/22.
 */
public class SparseArray {
    
    /**
     * 二位数组的遍历
     * @param args
     */
    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
    
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        
        for(int row[]:chessArr1){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    
    
        // 将二维数组 转 稀疏数组的思
     // 1. 先遍历二维数组 得到非 0 数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
    
        // 2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
// 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
      
     // 遍历二维数组，将非 0 的值存放到 sparseArr 中
        int count = 0; //count 用于记录是第几个非 0 数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
    
    }
    
}
