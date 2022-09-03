# FRCAutoDriver

## 项目说明

这个项目利用人工势场法，实现机器人的路径规划，并通过其他设置完成轨迹优化，同时他还可以结合激光SLAM+目标识别等技术进行路径规划

因为本人在其他开发中主要使用C++和Python，相对而言Java的使用没那么熟练，因此项目在实际使用中可能会出现一些不可预知的问题，希望你可以在仓库中提出issue或成为我们的开发者，和我们一起完善这个开源项目。

后续会进一步学习Java，把这些类封装为接口，而不是需要修改类方法的内部代码

## 使用说明

### TODO 00 为项目添加核心文件

你可以使用`git clone`方式或者下载为zip文件，获取本项目的全部代码，请将以下文件拷贝到对应的文件夹，并按照TODO指引完善
> src/main/java/frc/robot/subsystems/PathSub.java

> src/main/java/frc/robot/subsystems/PoseSub.java

> src/main/java/frc/robot/subsystems/DriveSub.java

> src/main/java/frc/robot/standard/StatusXYW.java

另外以下文件提供了`auto_init`和`auto_drive`的使用示意，可以在自己的项目中参考该写法
> src/main/java/frc/robot/commands/RoboCom.java

> src/main/java/frc/robot/Robot.java

### TODO 01 准确定位机器人的初始位置
```java
// RoboCom.java
public void auto_init(){
    // TODO[01]以整张地图(823*1646,单位:cm)的左下角为原点，准确标记出机器人的位置和角度
    PoSub = new PoseSub(300,500,0);
}
```
![images/01.png](https://user-images.githubusercontent.com/87751816/188202864-eeb3a2c2-9f09-4ffc-ae1d-2a5fd030769c.png)
如图所示，以场地左下角为原点，标记出机器的位置和角度，角度为x轴正方向与机器人正方向（自己定义，可以是收球方向、射球方向等）的夹角

### TODO 02 获取机器人的实际位置
```java
// PoseSub.java
/**
 * TODO[02]请完善以下代码，或者重写代码块实现功能
 * @return 返回一个记载机器人位姿状态的StatusXYW对象
 */
public StatusXYW update(){
    double delta_x = 0.0;
    double delta_y = 0.0;
    double delta_w = 0.0;
    StatusXYW delta_status = new StatusXYW(delta_x,delta_y,delta_w);
    robot_status.update(delta_status);
    return robot_status;
}
```