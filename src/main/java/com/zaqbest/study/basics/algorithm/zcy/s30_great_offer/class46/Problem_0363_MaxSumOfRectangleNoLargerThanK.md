### 一维数组的情况
有序表实现
![](https://assets.zaqbest.com/2023/01/25/63d13b77ce940.png)

### 二维数组的情况，使用压缩数组技巧变成一维数组
![](https://assets.zaqbest.com/2023/01/25/63d13b774b983.png)

计算行数
0-0， 0-1， 0-2，0.N-1
1-1, 1-2, 1..N-1
N-1,N-1

每个行计算0~M-1列

总复杂度 N^2*M, 所以考虑是否对矩阵进行转置，使得N更小