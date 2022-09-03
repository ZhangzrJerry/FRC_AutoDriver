package frc.robot.commands;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PathSub.PathSub;
import frc.robot.subsystems.PathSub.StatusXYW;

public class RoboCom {

    PathSub PSub;
    DriveSub DSub;
    StatusXYW robot_status;

    public RoboCom(){
        PSub = new PathSub();
        DSub = new DriveSub();
    }

    public void auto_init(){

        // TODO[02]以整张地图(823*1646,单位:cm)的左下角为原点，准确标记出机器人的位置和角度
        robot_status = new StatusXYW(300,500,0);

        // TODO[03]画出路径
        // 用draw_line画出场地边界、中间线以及其他队伍占用的空间
        PSub.draw_line(40, 40, 1606, 40, 100, true);
        PSub.draw_line(40, 783, 1606, 783, 100, true);
        
        // 用mark标记出目标球和非目标球
        PSub.mark(300, 500, -100, 0.99, true); // 目标球
        PSub.mark(300, 500, +100, 0.99, true); // 非目标球
        
    }

    public void auto_drive(){
        
        // TODO[04]获取机器人的实时位置
        double x = 0.0;
        double y = 0.0;
        double w = 0.0;
        robot_status.set(x, y, w);

        // TODO[05]选择合适的路径跟踪方式
        DSub.driver(PSub.drive_ahead(robot_status, 15));

    }
    
}
