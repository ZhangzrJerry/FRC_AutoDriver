# 导入头文件
import numpy as np
import matplotlib.pyplot as plt
def mark(mymap,x,y,value):
    tempmap = np.zeros((1646,823))
    tempmap[x][y] = value
    for i in range(x-1,0):
        tempmap[i][y] = tempmap[i+1][y] * 0.99
    for i in range(x+1,1646):
        tempmap[i][y] = tempmap[i-1][y] * 0.99
    for i in range(y-1,0):
        for j in range(0,1646):
            tempmap[j][i] = tempmap[j][i+1] * 0.99
    for i in range(y+1,823):
        for j in range(0,1646):
            tempmap[j][i] = tempmap[j][i-1] * 0.99
    return np.add(mymap,tempmap)
# x,y = 
mymap = np.zeros((1646,823))
mymap = mark(mymap,200,500,-100)
mymap = mark(mymap,300,550,100)
mymap = mark(mymap,900,300,-100)
data = []
for i in range(1646):
    for j in range(0,823):
        data.append([i,j,mymap[i][j]])
data = np.array(data).T
ax = plt.subplot(111, projection='3d')
ax.scatter(data[0],data[1],data[2],c='y')