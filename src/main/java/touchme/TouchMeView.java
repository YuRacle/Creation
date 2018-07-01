package touchme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import static touchme.TouchText.touchText;

/**
 * Created by YuRacle on 2018/5/31.
 */
public class TouchMeView extends JPanel implements MouseMotionListener{

    private JButton mButton;//按钮
    private JTextPane mText;//显示文字
    private JLabel mLocation;//显示坐标
    private ArrayList<String> texts = touchText.instance().getTextList();//文字list
    private ArrayList<String> btnTexts = touchText.instance().getBtnTextList();//文字list
    private int index = 0;//文字list索引
    private int btnIndex = 0;//按钮文字list索引
    private int mButtonX = 1155/2 - 50;//按钮x坐标
    private int mButtonY = 660/2 - 25;//按钮y坐标
    private Point mousepoint = new Point(mButtonX, mButtonY);//鼠标坐标点

    public TouchMeView() {
        this.setLayout(null);
//        this.setBackground(Color.red);
        mButton = new JButton("点我有惊喜!");
        mButton.setForeground(new Color(255,255,0));
        mButton.setSize(140,50);
        mButton.setLocation(mButtonX-70,mButtonY-25);

        mButton.setBackground(new Color(2,117,104));
        mButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(TouchMeView.this,"恭喜你!什么都不会发生!");

            }
        });

        mLocation = new JLabel();
        mLocation.setBounds(10,5,200,20);
        mLocation.addMouseMotionListener(this);

        mText = new JTextPane();
        mText.setEditable(false);
        mText.setOpaque(false);
        mText.setForeground(Color.red);
        mText.setFont(new Font("微软雅黑",1,20));
        mText.setBounds(450,250,300,100);
        mText.setText("点按钮开始游戏!");
        mText.addMouseMotionListener(this);

        this.add(mLocation);
        this.add(mText);
        this.add(mButton);
        this.addMouseMotionListener(this);
        this.setVisible(true);
    }

    //更新按钮坐标
    public void upataButtonLocation() {
        Random random;
        do {
            //随机数生成随机坐标
            random = new Random();
            mButtonX = Math.abs(random.nextInt(1100) - mousepoint.x);
            mButtonY = Math.abs(random.nextInt(660) - mousepoint.y);
        } while (mButtonX > 1145 || mButtonX < 10 || mButtonY > 650 || mButtonY < 10
                || (mousepoint.x > (mButtonX-10) && mousepoint.x < mButtonX+110 && mousepoint.y > mButtonY-10 && mousepoint.y < mButtonY+60));

        System.out.println("ok");
        System.out.println(mButtonX+ " "+mButtonY);
        mButton.setLocation(mButtonX,mButtonY);

        random = new Random();
        mText.setBounds(random.nextInt(700)+200, random.nextInt(350)+100,
                random.nextInt(250)+100, 350);
        //更新文本
        if (index == texts.size()) {
            index = 0;
        }
        mText.setText(texts.get(index));
        index++;
        //更新按钮文本
        if (btnIndex == btnTexts.size()) {
            btnIndex = 0;
        }
        mButton.setText(btnTexts.get(btnIndex));
        btnIndex++;

//        mLocation.updateUI();
//        mText.updateUI();
//        mButton.updateUI();
        mText.validate();
        mLocation.validate();
        mButton.validate();

       // this.repaint();
    }
    //鼠标拖动监听
    public void mouseDragged(MouseEvent e) {
        //获取鼠标坐标
        Point point = MouseInfo.getPointerInfo().getLocation();
        //预处理得到窗口坐标
        mousepoint.x = point.x - 438;
        mousepoint.y = point.y - 213;
        mLocation.setText(mousepoint.x+"  "+mousepoint.y);
//        System.out.println(mousepoint.x+" "+mousepoint.y);
        //检查,如果进入按钮范围,则更新按钮坐标
        if ( mousepoint.x > (mButtonX-10) && mousepoint.x < mButtonX+150 && mousepoint.y > mButtonY-10 && mousepoint.y < mButtonY+60) {
            //更新按钮坐标
            upataButtonLocation();
        }
    }
    //鼠标移动监听,同上
    public void mouseMoved(MouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        mousepoint.x = point.x - 438;
        mousepoint.y = point.y - 213;
        mLocation.setText(mousepoint.x+"  "+mousepoint.y);
//        System.out.println(mousepoint.x+" "+mousepoint.y);

        if ( mousepoint.x > (mButtonX-10) && mousepoint.x < mButtonX+150 && mousepoint.y > mButtonY-10 && mousepoint.y < mButtonY+60) {
            upataButtonLocation();
        }
    }
    //画背景图
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("src/images/touchviewbg.png").getImage();
        g.drawImage(image, 0, 0, 1920 * 2/3, 1080 * 2/3,this);
    }
}
