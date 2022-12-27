## 最优解

### 整体一定是先下降，再上升的样子
![](https://pic.zaqbest.com/i/2022/12/22/63a3cc541c3e0.png)

### 考虑最低点，可能不重合的情况
0..i是下降区间  
i+1..N-1是上升区间
![](https://pic.zaqbest.com/i/2022/12/22/63a3cc5349ebd.png)

### 具体实现
通过dp来实现，dp[i]指的是从0..i的总成本
![](https://pic.zaqbest.com/i/2022/12/22/63a3cc55b7416.png)


## 动态规划（能学习到东西）