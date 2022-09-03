package frc.robot.commands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PathSub;
import frc.robot.subsystems.PoseSub;

public class RoboCom {

    private PoseSub PoSub;
    private PathSub PaSub;
    private DriveSub DSub;

    public void auto_init(){
        // TODO[01]以整张地图(823*1646,单位:cm)的左下角为原点，准确标记出机器人的位置和角度，详细说明见README
        PoSub = new PoseSub(300,500,0);
    }

    public void auto_drive(){
        
    }
    
}
