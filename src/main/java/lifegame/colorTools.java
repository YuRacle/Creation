package lifegame;

import java.awt.*;

/**
 * Created by YuRacle on 2018/6/21.
 */
public class colorTools {
    /**
     * 把Color对象转化为16进制化的字符串
     * @param color Color对象
     * @return 16进制化的字符串
     */
    public static String toHexFromColor(Color color) {

        StringBuilder strings = new StringBuilder();
        String red = Integer.toHexString(color.getRed());
        String green = Integer.toHexString(color.getGreen());
        String blue = Integer.toHexString(color.getBlue());

        red = (red.length() == 1 ? "0" + red : red).toUpperCase();
        green = (green.length() == 1 ? "0" + green : green).toUpperCase();
        blue = (blue.length() == 1 ? "0" + blue : blue).toUpperCase();

        strings.append("0xFF");
        strings.append(red);
        strings.append(green);
        strings.append(blue);

        return strings.toString();
    }

    /**
     * 把16进制字符串转化为Color对象
     * @param strings 16进制字符串
     * @return Color对象
     */
    public static Color toColorFromString(String strings) {
        Color color;
        String colorStr = strings.substring(4);
        color = new Color(Integer.parseInt(colorStr, 16));
        return color;
    }

    /**
     * 混合2种颜色
     * @param c1 第一种颜色
     * @param c2 第二种颜色
     * @return 混合后颜色
     */
    public static Color colorOverlay(Color c1, Color c2) {
        int r1 , g1, b1, r2, g2, b2;
        int red, green, blue;
        r1 = c1.getRed();
        g1 = c1.getGreen();
        b1 = c1.getBlue();
        r2 = c2.getRed();
        g2 = c2.getGreen();
        b2 = c2.getBlue();

        red = (r1 + r2)/2;
        green = (g1 + g2)/2;
        blue = (b1 + b2)/2;

        return new Color(red, green, blue);
    }
}
