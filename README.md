### TODO 01
```java
public void auto_init(){
    // TODO:以整张地图(823*1646,单位:cm)的左下角为原点，准确标记出机器人的位置和角度
    status = new StatusXYW(300,500,0);
}
```
![images/01.png](https://user-images.githubusercontent.com/87751816/188202864-eeb3a2c2-9f09-4ffc-ae1d-2a5fd030769c.png)
如图所示，以场地左下角为原点，标记出机器的位置和角度，角度为x轴正方向与机器人正方向（自己定义，可以是收球方向、射球方向等）的夹角