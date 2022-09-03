package frc.robot.subsystems.PathSub;

/**
 * 因为Java基础不好，接口类没搞明白，所以自己写了个Matrix类
 */
public class MyMatrix {
    int rows;
    int cols;
    double[][] data;
    public MyMatrix(){
        this(1646,823);
    }
    public MyMatrix(int x,int y){
        this.rows = x;
        this.cols = y;
        //y行x列
        this.data = new double[y][x];
    }
    public double get(int x,int y){
        return this.data[x][y];
    }
    public void set(int x,int y,double value){
        this.data[x][y] = value;
    }
    public int[] shape(){
        int[] a = {this.rows,this.cols};
        return a;
    }
    public MyMatrix add(MyMatrix that){
        if(this.rows!=that.shape()[0] || this.cols!=that.shape()[1]){
            return null;
        }
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                this.set(i,j,this.get(i,j)+that.get(i,j));
            }
        }
        return this;
    }
}
