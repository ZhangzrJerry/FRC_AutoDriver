package frc.robot.subsystems.PathSub;

import java.lang.Math;

public class PathSub {

    MyMatrix mymap;

    public PathSub(){
        this(1646,823);
    }

    public PathSub(int x_size,int y_size){
        mymap = new MyMatrix(x_size,y_size);
    }

    /**
     * @param x 标记点的x坐标
     * @param y 标记点的y坐标
     * @param value 标记点的值
     * @param k 标记点值向周围扩散的衰减系数,0-1
     * @param replace 假则不对mymap修改
     * @return 返回生成的矩阵
     */
    public MyMatrix mark(int x,int y,double value,double k,boolean replace){
        MyMatrix temp = new MyMatrix(mymap.shape()[0],mymap.shape()[1]);
        temp.set(x, y, value);
        for(int i=x-1;i>=0;i--){
            temp.set(i,y,temp.get(i+1,y)*k);
        }
        for(int i=x+1;i<temp.shape()[0];i++){
            temp.set(i,y,temp.get(i-1,y)*k);
        }
        for(int i=y-1;i>=0;i--){
            for(int j=0;j<temp.shape()[0];j++){
                temp.set(j,i,temp.get(j,i+1)*k);
            }
        }
        for(int i=y+1;i<temp.shape()[1];i++){
            for(int j=0;j<temp.shape()[0];j++){
                temp.set(j,i,temp.get(j,i-1)*k);
            }
        }
        if(replace)this.mymap.add(temp);
        return temp;
    }

    public MyMatrix draw_line(int x_begin,int y_begin,int x_end,int y_end,double value,boolean replace){
        MyMatrix temp = new MyMatrix(mymap.shape()[0],mymap.shape()[1]);
        int ymin = y_begin < y_end ? y_begin : y_end;
        int ymax = y_begin > y_end ? y_begin : y_end;
        if(x_begin==x_end){
            for(int i=ymin;i<=ymax;i++){
                temp.set(x_begin, i, value);
            }
        }else{
            double delta_x = x_end - x_begin;
            double delta_y = y_end - y_begin;
            double k = delta_y / delta_x;
            double b = y_end - x_end * k;
            for(int i=ymin;i<=ymax;i++){
                int j = (int)((i-b)/k);
                temp.set(j, i, value);
            }
        }
        if(replace)this.mymap.add(temp);
        return temp;
    }

    // private int check(int value,int a,int b){
    //     int ans = value;
    //     ans = ans < a ? a : ans;
    //     ans = ans > b ? b : ans;
    //     return ans;
    // }

    /**
     * 机器人正方向始终朝着前进的方向
     * @param robot_status 机器人的实时状态
     * @param steps 机器人向前移动的相对步数
     * @return 返回一个StatusXYW对象，记载机器人往哪个方向走，单位：厘米、角度
     */
    public StatusXYW drive_ahead(StatusXYW robot_status,int steps){
        int x = 0;
        int y = 0;
        // 如果这里用while的话，到鞍点没注意的话会卡在这个函数里面，for的话顶多不动
        for(int i=0;i<steps;i++){
            int[] temp = this.found(x+(int)robot_status.x,y+(int)robot_status.y);
            x += temp[0];
            y += temp[1];
        }
        double w = Math.tanh((double)y/(double)x)-robot_status.w;
        StatusXYW delta = new StatusXYW(x,y,w);
        return delta;
    }

    /**
     * 机器人正方向始终朝着目标
     * @param robot_status 机器人的实时状态
     * @param steps 机器人向前移动的相对步数
     * @param x 目标的横坐标
     * @param y 目标的纵坐标
     * @return 返回一个StatusXYW对象，记载机器人往哪个方向走，单位：厘米、角度
     */
    public StatusXYW drive_moon(StatusXYW robot_status,int steps,int target_x,int target_y){
        int x = 0;
        int y = 0;
        // 如果这里用while的话，到鞍点没注意的话会卡在这个函数里面，for的话顶多不动
        for(int i=0;i<steps;i++){
            int[] temp = this.found(x+(int)robot_status.x,y+(int)robot_status.y);
            x += temp[0];
            y += temp[1];
        }
        double delta_x = target_x - x - robot_status.x;
        double delta_y = target_y - y - robot_status.y;
        double w = Math.tanh(delta_y / delta_x) - robot_status.w;
        StatusXYW delta = new StatusXYW(x,y,w);
        return delta;
    }

    private int[] found(int x, int y){
        int[]ans = {0,0};
        // 上下左右
        int mark = 1;
        double min = this.mymap.get(x, y+1);
        if(min>this.mymap.get(x, y-1)){
            min = this.mymap.get(x, y-1);
            mark = 2;
        }
        if(min>this.mymap.get(x-1, y)){
            min = this.mymap.get(x-1, y);
            mark = 3;
        }
        if(min>this.mymap.get(x+1, y)){
            mark = 4;
        }
        switch(mark){
            case 1:
                ans[1] = +1;
                break;
            case 2:
                ans[1] = -1;
                break;
            case 3:
                ans[0] = -1;
                break;
            case 4:
                ans[0] = +1;
                break;
        }
        return ans;
    }
}
