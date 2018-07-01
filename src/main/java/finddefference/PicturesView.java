package finddefference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by YuRacle on 2018/6/26.
 */
public class PicturesView extends JPanel implements ActionListener{

    private JPanel picPanel;
    private JPanel buttomPanel;
    private String picPath1;
    private String picPath2;
    private String outPath;
    private JButton picButton1;
    private JButton picButton2;
    private JButton outButton;
    private JButton clearButton;
    private JLabel pic1;
    private JLabel pic2;
    private JLabel picOut;

    public PicturesView() {

        this.setLayout(new BorderLayout());
        picPanel = new JPanel(null);
        buttomPanel = new JPanel();
        pic1 = new JLabel("图片1",Label.LEFT);
        pic2 = new JLabel("图片2",Label.LEFT);
        picOut = new JLabel();
        picButton1 = new JButton("选择图片1");
        picButton2 = new JButton("选择图片2");
        outButton = new JButton("找茬");
        clearButton = new JButton("重置");

        picOut.setBounds(0,0,1280,720);
        pic1.setBounds(0, 0, 1158/2, 670);
        pic2.setBounds(1158/2+2, 0, 1158/2, 670);
        picPanel.add(picOut);
        picPanel.add(pic1);
        picPanel.add(pic2);
        buttomPanel.setPreferredSize(new Dimension(1280, 50));
        buttomPanel.add(picButton1);
        buttomPanel.add(picButton2);
        buttomPanel.add(outButton);
        buttomPanel.add(clearButton);

        picButton1.addActionListener(this);
        picButton2.addActionListener(this);
        outButton.addActionListener(this);
        clearButton.addActionListener(this);

        this.add(picPanel, BorderLayout.CENTER);
        this.add(buttomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == picButton1) {
            //设置图片1
            picPath1 = new FileChooser().getFilePath();
            ImageIcon icon = new ImageIcon(picPath1);
            icon.setImage(icon.getImage().getScaledInstance(1158/2,
                    670, Image.SCALE_DEFAULT));
            pic1.setHorizontalAlignment(0);
            pic1.setIcon(icon);
            repaint();
        }else if (e.getSource() == picButton2) {
            //设置图片2
            picPath2 = new FileChooser().getFilePath();
            ImageIcon icon = new ImageIcon(picPath2);
            icon.setImage(icon.getImage().getScaledInstance(1158/2,
                    670, Image.SCALE_DEFAULT));
            pic2.setHorizontalAlignment(0);
            pic2.setIcon(icon);
            repaint();
        }else if (e.getSource() == clearButton) {
            //重置界面
            picOut.setVisible(false);
            pic1.setIcon(null);
            picPath1 = null;
            pic2.setIcon(null);
            picPath2 = null;
            outPath = null;
            repaint();
        }else if (e.getSource() == outButton) {
            //找茬并显示结果图片
            if (picPath1 == null || picPath2 == null) {
                JOptionPane.showMessageDialog(PicturesView.this,"请选择图片！");
            }else {
                try {
                    outPath = new FindDefferentOfPictures().compare(picPath1,picPath2);
                    ImageIcon icon = new ImageIcon(outPath);
                    icon.setImage(icon.getImage().getScaledInstance(1280,
                            720, Image.SCALE_DEFAULT));
                    picOut.setHorizontalAlignment(0);
                    picOut.setIcon(icon);
                    picOut.setVisible(true);
                    repaint();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(PicturesView.this,"图片无法比较！");
                    e1.printStackTrace();
                }
            }
        }
    }


}
