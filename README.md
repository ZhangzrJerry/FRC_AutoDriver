# FRCAutoDriver

## 项目说明

本项目利用人工势场法，实现机器人的路径规划，并通过其他设置完成轨迹优化，同时他还可以结合激光SLAM+目标识别等技术进行路径规划

因为本人在其他开发中主要使用C++和Python，相对而言Java的使用没那么熟练，因此项目在实际使用中可能会出现一些不可预知的问题，希望你可以在仓库中提出issue或成为我们的开发者，和我们一起完善这个开源项目。

## 使用说明

### TODO 00 为项目添加文件

你可以使用`git clone`方式或者下载为zip文件，获取本项目的全部代码，请将该文件夹拷贝到相应路径的文件夹，并按照TODO指引完善代码
> src/main/java/frc/robot/subsystems/PathSub
另外以下文件提供了路径规划的使用示意，可以参考
> src/main/java/frc/robot/commands/RoboCom.java

### TODO 01 编写你的逆运动学方法
```java
// src\main\java\frc\robot\subsystems\DriveSub.java
/**
 * 提供一个逆运动学方法，把xyw增量分配给各电机
 */
public void driver(StatusXYW delta){
    // TODO[01]矢量轮逆运动学分析可参考链接https://www.bilibili.com/read/cv14591015
}
```

### TODO 02 准确定位机器人的初始位置
```java
// src\main\java\frc\robot\commands\RoboCom.java
public void auto_init(){
    // TODO[02]以整张地图(823*1646,单位:cm)的左下角为原点，准确标记出机器人的位置和角度
    robot_status = new StatusXYW(300,500,0);
}
```
![images/01.png](https://user-images.githubusercontent.com/87751816/188202864-eeb3a2c2-9f09-4ffc-ae1d-2a5fd030769c.png)
如图所示，以场地左下角为原点，标记出机器的位置和角度，角度为x轴正方向与机器人正方向（自己定义，可以是收球方向、射球方向等）的夹角

### TODO 03 为地图进行标记
```java
// src\main\java\frc\robot\commands\RoboCom.java
public void auto_init(){
    // TODO[03]为地图进行标记，参考场地定位图https://firstfrc.blob.core.windows.net/frc2022/FieldAssets/2022LayoutMarkingDiagram.pdf

    // 用draw_line画出场地边界、中间线以及其他队伍会占用的空间
    PSub.draw_line(40, 40, 1606, 40, 100, true);
    PSub.draw_line(40, 783, 1606, 783, 100, true);
    
    // 用mark标记出目标球和非目标球
    PSub.mark(300, 500, -100, 0.99, true); // 目标球
    PSub.mark(500, 500, +100, 0.99, true); // 非目标球
}
```

### TODO 04 获取机器人的实际位置
```java
// src\main\java\frc\robot\commands\RoboCom.java
public void auto_drive(){
    // TODO[04]获取机器人的实际位置
    double x = 0.0;
    double y = 0.0;
    double w = 0.0;
    robot_status.set(x, y, w);
}
```

### TODO 05 选择合适的路径规划方式
```java
// src\main\java\frc\robot\commands\RoboCom.java 
public void auto_drive(){
    // TODO[05]选择合适的路径规划方式，drive_ahead或drive_moon
    DSub.driver(PSub.drive_ahead(robot_status, 15));
}
```

### TODO 06 判断机器成功拾球
```java
// src\main\java\frc\robot\commands\RoboCom.java
public void auto_drive(){
    // TODO[06]取消拾取到的球在地图上的标记
    if(true/* 选择你的condition，可以是位置达到阈值，当然如果你用colorsensor或通过射球/吸球的电压/速度变化来判断拾球成功那就更好了 */){
        PSub.mark(300, 500, +100, 0.99, true); // 取消目标球设定
    }
}
```