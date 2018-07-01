package lifegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by YuRacle on 2018/6/14.
 */
public class GameView extends JPanel implements ActionListener {

    public static void main(String[] args) {

        Color c = new Color(255,255,255);
        System.out.println(c.equals(Color.white));
    }

    private Thread thread;
    //游戏开始标记
    private boolean isStarting = false;
    //记录生命块颜色
    private Color[][] blockColor;
    //演化间隔, 单位ms
    private int time = 200;
    //行数, 列数
    private int rows = 32, cols = 32;
    private JLabel rowsLable, colsLable, timeLable;
    private JComboBox rowList, colList, tiemList;
    //开始, 结束, 下一代按钮
    private JButton startButton, stopButton, nextButton, ruleButton, clearButton;
    //生命块
    private JButton[][] blockGroup;
    private JPanel blockpanel, bottomPanel;

    public GameView() {

        this.setLayout(new BorderLayout());

        rowsLable = new JLabel("行数: ");
        colsLable = new JLabel("列数: ");
        timeLable = new JLabel("演化间隔: ");
        rowList = new JComboBox();
        for (int i = 10; i <= 100; i += 10) {
            rowList.addItem(String.valueOf(i));
        }
        rowList.addItem("200");
        rowList.setSelectedIndex(2);

        colList = new JComboBox();
        for (int i = 10; i <= 100; i += 10) {
            colList.addItem(String.valueOf(i));
        }
        colList.addItem("200");
        colList.setSelectedIndex(2);

        tiemList = new JComboBox();
        for (int i = 100; i <= 2000; i += 100) {
            tiemList.addItem(String.valueOf(i));
        }
        tiemList.setSelectedIndex(2);

        startButton = new JButton("开始演算");
        nextButton = new JButton("下一代");
        stopButton = new JButton("停止演算");
        clearButton = new JButton("初始化");
        ruleButton = new JButton("游戏规则");
//        blockGroup = new JButton[rows][cols];
//        blockColor = new Color[rows][cols];
        blockpanel = new JPanel(new GridLayout(rows-2, cols-2));
        bottomPanel = new JPanel();

        //底部按钮
        bottomPanel.add(rowsLable);
        bottomPanel.add(rowList);
        bottomPanel.add(colsLable);
        bottomPanel.add(colList);
        bottomPanel.add(timeLable);
        bottomPanel.add(tiemList);
        bottomPanel.add(startButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(stopButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(ruleButton);

        initSize();

        startButton.addActionListener(this);
        nextButton.addActionListener(this);
        stopButton.addActionListener(this);
        clearButton.addActionListener(this);
        ruleButton.addActionListener(this);
        rowList.addActionListener(this);
        colList.addActionListener(this);
        tiemList.addActionListener(this);

        this.add(blockpanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void init() {

    }

    //监听器
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startCalculation();
        } else if (e.getSource() == nextButton) {
            if (!isStarting) {
                nextGeneration();
            }else {
                JOptionPane.showMessageDialog(GameView.this, "请先停止演算");
            }
        } else if (e.getSource() == stopButton) {
            isStarting = false;
            thread = null;
        } else if (e.getSource() == clearButton) {
            if (!isStarting) {
                blockInitialize();
            }else {
                JOptionPane.showMessageDialog(GameView.this, "请先停止演算");
            }
        } else if (e.getSource() == ruleButton) {
            String rule = "这是0人游戏，即按照初始的设置，游戏自动演化。在类似围棋的棋盘中，每一个格子可以是空格或者存在一个生命/细胞/Cell；\n" +
                    "每一个格子有8个相邻的格子(正上方、正下方、右侧、左侧、左上方、右上方、左下方以及右下方)，相邻的格子中存活的生命数量称为其邻居(neighbor)数。\n" +
                    "在世代交替时，所有的格子根据其邻居数，诞生新生命、Cell保持存活或者Cell死亡。\n" +
                    "开始时点击生命块生成原始生命块,然后点击开始演算按钮开始。\n" +
                    "生命演化规则：\n" +
                    "1.白色代表死亡或没出生,不同颜色代表各式各样的人。\n" +
                    "2.一个生命如果恰好有2个邻居,他的生命状态不会改变,颜色不会改变。\n" +
                    "3.一个生命如果恰好有3个邻居，它会存活到下一个世代且颜色变换成周围上一代邻居颜色的混合色。\n" +
                    "3.一个空格，如果恰好有3个邻居，则诞生一个新生命且块颜色是周围邻居颜色的混合色。\n" +
                    "4.否则，会因为孤独或拥挤而死亡,颜色变成白色。";
            JOptionPane.showMessageDialog(GameView.this, rule);
        } else if (e.getSource() == rowList) {
            if (!isStarting) {
                rows = Integer.valueOf(rowList.getSelectedItem().toString())+2;
                initSize();
            }else {
                JOptionPane.showMessageDialog(GameView.this, "请先停止演算,再次运行");
            }
        } else if (e.getSource() == colList) {
            if (!isStarting) {
                cols = Integer.valueOf(colList.getSelectedItem().toString())+2;
                initSize();
            }else {
                JOptionPane.showMessageDialog(GameView.this, "请先停止演算,再次运行");
            }
        } else if (e.getSource() == tiemList) {
            if (!isStarting) {
                time = Integer.valueOf(tiemList.getSelectedItem().toString());
            }else {
                JOptionPane.showMessageDialog(GameView.this, "请先停止演算,再次运行");
            }
        }else {
            if (!isStarting) {
                for (int i = 1; i < rows-1; i++) {
                    for (int j = 1; j < cols-1; j++) {
                        if (e.getSource() == blockGroup[i][j]) {
                            Random random = new Random();
                            Color c = new Color(random.nextInt(255)+1, random.nextInt(255)+1
                                    , random.nextInt(255)+1);
                            blockGroup[i][j].setBackground(c);
                            blockColor[i][j] = c;
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * 开始生命演算
     */
    public void startCalculation() {
        isStarting = true;
        thread = new Thread(new Runnable() {
            public void run() {
                while (isStarting) {
                    nextGeneration();
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 演算下一代
     */
    public void nextGeneration() {
        blockColor = new GameRule(blockColor).updataBlock();

        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                blockGroup[i][j].setBackground(blockColor[i][j]);
            }
        }
    }

    /**
     * 初始化生命块
     */
    public void blockInitialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                blockColor[i][j] = Color.white;
                blockGroup[i][j].setBackground(blockColor[i][j]);
            }
        }
    }

    /**
     * 初始化演算界面大小
     */
    public void initSize() {
        blockpanel.removeAll();
        blockpanel.setLayout(new GridLayout(rows-2, cols-2));
        blockGroup = new JButton[rows][cols];
        blockColor = new Color[rows][cols];

        //生成生命块
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                blockGroup[i][j] = new JButton();
                blockColor[i][j] = new Color(255,255,255);
            }
        }

        //生命块颜色
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                blockGroup[i][j].setBackground(blockColor[i][j]);
                blockGroup[i][j].addActionListener(this);
                blockpanel.add(blockGroup[i][j]);
            }
        }
        blockpanel.updateUI();
        repaint();
    }

    public Color[][] getBlockColor() {
        return blockColor;
    }

    public void setBlockColor(Color[][] blockColor) {
        this.blockColor = blockColor;
    }
}
