package touchme;

import java.util.ArrayList;

/**
 * Created by YuRacle on 2018/5/31.
 */
public class TouchText {

    public static TouchText touchText;
    private ArrayList<String> textList = new ArrayList<String>();
    private ArrayList<String> btnTextList = new ArrayList<String>();

    public static TouchText instance() {
        if (touchText == null) {
            touchText = new TouchText();
        }
        return touchText;
    }

    {
        //java基础知识
        textList.add("面向对象的特征: -抽象 -继承 -封装 -多态性");
        textList.add("String 类是final类，不可以被继承。");
        textList.add("原始类型: boolean，char，byte，short，int，long，float，double。");
        textList.add("包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double。");
        textList.add("构造器不能被继承，因此不能被重写，但可以被重载。");
        textList.add("静态方法只能访问静态成员，因为非静态方法的调用要先创建对象，在调用静态方法时可能对象并没有被初始化。");
        textList.add("方法的重载和重写都是实现多态的方式，区别在于前者实现的是编译时的多态性，而后者实现的是运行时的多态性。");
        textList.add("重载发生在一个类中，同名的方法如果有不同的参数列表（参数类型不同、参数个数不同或者二者都不同）则视为重载,重载对返回类型没有特殊的要求。");
        textList.add("重写要求子类被重写方法与父类被重写方法有相同的返回类型。");

        textList.add("- ArithmeticException（算术异常） \n" +
                "- ClassCastException （类转换异常） \n" +
                "- IllegalArgumentException （非法参数异常） \n" +
                "- IndexOutOfBoundsException （下标越界异常） \n" +
                "- NullPointerException （空指针异常） \n" +
                "- SecurityException （安全异常）");
        textList.add("2 << 3（左移3位相当于乘以2的3次方，右移3位相当于除以2的3次方）。");
        textList.add("goto 是Java中的保留字，在目前版本的Java中没有使用。");

        btnTextList.add("趁按钮不注意!");
        /*btnTextList.add("↑ ↓ ← →");
        btnTextList.add("(*￣∇￣*) ");
        btnTextList.add("好好学习!");
        btnTextList.add("天天向上!");
        btnTextList.add("点掉我试试");
        btnTextList.add("点不掉哈");
        btnTextList.add("(～￣▽￣)～ ");
        btnTextList.add("多看看笔记");*/
    }

    public TouchText() {

    }

    public ArrayList<String> getTextList() {
        return textList;
    }

    public ArrayList<String> getBtnTextList() {
        return btnTextList;
    }
}
