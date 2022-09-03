package frc.robot.subsystems.PathSub;
import java.lang.Math;

/**
 * 提供了一个标准的记录机器人状态的类，可以是全局状态、速度状态、增量状态
 */
public class StatusXYW {

    public double x = 0;
    public double y = 0;
    public double w = 0;

    /**
     *  默认构造函数
     */
    public StatusXYW(){

    }

    /**
     *  深拷贝复制
     */
    public StatusXYW(StatusXYW that){
        this.x = that.x;
        this.y = that.y;
        this.w = that.w;
    }

    /**
     * 带参构造函数
     * @param x 单位：cm
     * @param y 单位：cm
     * @param w 单位：角度
     */
    public StatusXYW(double x,double y,double w){
        this.x = x;
        this.y = y;
        this.w = w;
    }
    
    /**
     * 齐次坐标变换更新
     * @param delta 含有x,y,w变化量的StatusXYW
     * @return 更新后的StatusXYW
     */
    public StatusXYW update(StatusXYW delta){
        /*
         * 数学原理：齐次坐标变换法
         * 新状态矩阵 = 旋转矩阵 * 位移矩阵 * 原状态矩阵 = 齐次坐标变换矩阵 * 原状态矩阵
         * [x]   [+cosθ -sinθ delta_x]   [prev_x]
         * [y] = [+sinθ +cosθ delta_y] * [prev_y]
         * [w]   [  0     0   delta_w]   [prev_w]
         */
        StatusXYW temp = this;
        // 把角度转换为弧度
        double radian = Math.PI * delta.w / 180.0;
        double cosw = Math.cos(radian);
        double sinw = Math.sin(radian);
        // 矩阵运算
        this.x = cosw * temp.x - sinw * temp.y + delta.x;
        this.y = sinw * temp.x + cosw * temp.y + delta.y;
        this.w = temp.w + delta.w;
        return this;
    }
}
