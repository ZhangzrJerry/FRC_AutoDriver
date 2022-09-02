package frc.robot.commands;
import frc.robot.standard.StatusXYW;

public class RoboCom {
    public void auto_init(){
        // TODO[01]以整张地图(823*1646,单位:cm)的左下角为原点，准确标记出机器人的位置和角度，详细说明见README
        status = new StatusXYW(300,500,0);
    }
    StatusXYW status;
}
