package frc.robot.subsystems;
import frc.robot.standard.StatusXYW;

public class PoseSub {

    // 分别记录机器人的位姿状态和速度状态
    StatusXYW robot_status, vel_status;

    public PoseSub(){
        robot_status = new StatusXYW(0.0,0.0,0.0);
        vel_status = new StatusXYW(0.0,0.0,0.0);
    }

    /**
     * @param x 单位：cm
     * @param y 单位：cm
     * @param w 单位：角度
     */
    public PoseSub(double x, double y, double w){
        robot_status = new StatusXYW(x,y,w);
        vel_status = new StatusXYW(0.0,0.0,0.0);
    }

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

}
