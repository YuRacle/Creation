package lifegame;

import java.awt.*;
import java.util.ArrayList;

import static lifegame.colorTools.*;

/**
 * Created by YuRacle on 2018/6/21.
 */
public class GameRule {

    //记录生命块颜色
    private Color[][] blockColor;
    private int rows, cols;

    public GameRule(Color[][] blockColor) {
        this.blockColor = blockColor;
        rows = blockColor.length;
        cols = blockColor[0].length;
    }

    /**
     * 演化下一代
     * @return 下一代的格局
     */
    public Color[][] updataBlock() {
        //下一代颜色块
        Color[][] nextBlockColor = new Color[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nextBlockColor[i][j] = new Color(255,255,255);
            }
        }

        //当前生命块邻居数
        int neighborCount;
        //当前生命块邻居的颜色集合
        ArrayList<Color> neighborColor = new ArrayList<Color>();
        for (int i=1; i < rows-1; i++) {
            for (int j=1; j < cols-1; j++) {
                //初始化
                neighborColor.clear();

                neighborCount = getNeighborCount(i, j);
                //邻居数适合则存活
                if (neighborCount == 2) {
                    nextBlockColor[i][j] = blockColor[i][j];
                }else if (neighborCount == 3) {
                    //下一代生命块的颜色
                    neighborColor = getNeighborColor(i, j);
                    Color color = neighborColor.get(0);
                    for (int k=1; k < neighborColor.size(); k++) {
                        color = colorOverlay(color, neighborColor.get(k));
                    }
                    nextBlockColor[i][j] = color;
                }else {
                    nextBlockColor[i][j] = Color.white;
                }
            }
        }
        return nextBlockColor;
    }

    /**
     * 获取当前生命块的邻居的个数
     * @param row 当前生命块的行数
     * @param col 当前生命块的列数
     * @return 当前生命块的邻居个数
     */
    public int getNeighborCount(int row, int col) {
        //当前生命块邻居生命个数
        int neighborCount = 0;
        for (int i = row-1; i <= row+1; i++) {
            for (int j = col-1; j <= col+1; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (!(blockColor[i][j].equals(Color.white))) {
                    neighborCount++;
                }
            }
        }
        return neighborCount;
    }

    /**
     * 获取当前生命块的邻居的颜色
     * @param row 当前生命块的行数
     * @param col 当前生命块的列数
     * @return 当前生命块的邻居的颜色集合
     */
    public ArrayList<Color> getNeighborColor(int row, int col) {
        //当前生命块邻居颜色
        ArrayList<Color> neighborColor = new ArrayList<Color>();
        for (int i = row-1; i <= row+1; i++) {
            for (int j = col-1; j <= col+1; j++) {
                if (!(blockColor[i][j].equals(Color.white))) {
                    neighborColor.add(blockColor[i][j]);
                }
            }
        }
        return neighborColor;
    }

}
