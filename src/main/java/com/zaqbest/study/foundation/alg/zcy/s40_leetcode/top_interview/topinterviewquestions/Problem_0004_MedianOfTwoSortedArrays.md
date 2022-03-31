假设
arr1:10个数
arr2:17个数

1) k >1 && k <= 10

取arr1的前7个数和arr2的前7个数的中位数

2) k > 10 && k <= 17

例如求第15小

![](https://cdn.zaqbest.com/61770476bfbc925808de8f26c265c2c.jpg)

![](https://cdn.zaqbest.com/b55d82066b5adc7eb3f4c0ea0a9089e.jpg)

3) k > 17 && k <= 27

比如求第23小

![](https://cdn.zaqbest.com/e519024ebb4f62ff119b24a9213219a.jpg)

![](https://cdn.zaqbest.com/bae03b876fd0be89d6ec03f5b323941.jpg)

**特殊处理**

6是不是13小，判断6是不是大于17'，如果是直接返回

13’是不是13小，13‘大于等于10，直接返回

再处理arr1[7,8,9,10]和arr1[14',15',16',17']