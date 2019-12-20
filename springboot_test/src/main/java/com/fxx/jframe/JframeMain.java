package com.fxx.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JframeMain {

    private final static Integer CHECKBOX_WIDTH = 150;
    private final static Integer CHECKBOX_HEIGHT = 30;

    private final static Integer LABLE_WEIGHT = 40;
    private final static Integer LABLE_HEIGHT = 30;

    private final static Integer PADDING_LEFT = 80;
    private final static Integer PADDING_TOP = 50;
    private final static Integer PADDING_RIGHT = 50;
    private final static Integer PADDING_TEN = 10;


    public static void main (String[] args) {
        JFrame jf = new JFrame("顺创石材");
        jf.setSize(1200, 800);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel(null);

        JLabel fruits = new JLabel("水果：");
        fruits.setBounds(PADDING_LEFT, PADDING_TOP, LABLE_WEIGHT, LABLE_HEIGHT);
        setLable(fruits);
        panel.add(fruits);
        // 创建一个下拉列表框
        final JComboBox<String> fruitsComboBox = new JComboBox<>(new String[]{"--请选择--", "香蕉", "雪梨", "苹果", "荔枝", "荔枝1", "荔枝2", "荔枝4", "荔枝", "荔枝"});
        fruitsComboBox.setBounds(PADDING_LEFT+LABLE_WEIGHT+PADDING_TEN, PADDING_TOP, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        panel.add(fruitsComboBox);

        // 添加一个标签
        JLabel automobile = new JLabel("汽车：");
        automobile.setBounds(PADDING_LEFT+LABLE_WEIGHT+PADDING_TEN*2+CHECKBOX_WIDTH, PADDING_TOP, LABLE_WEIGHT, LABLE_HEIGHT);
        panel.add(automobile);
        // 创建一个下拉列表框
        final JComboBox<String> automobileComboBox = new JComboBox<>(new String[]{"--请选择--", "保时捷", "法拉利", "奔驰", "奥迪"});
        automobileComboBox.setBounds(PADDING_LEFT+LABLE_WEIGHT*2+PADDING_TEN*3+CHECKBOX_WIDTH, PADDING_TOP, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        panel.add(automobileComboBox);

        // 添加一个标签
        JLabel city = new JLabel("城市：");
        city.setBounds(PADDING_LEFT+LABLE_WEIGHT*2+PADDING_TEN*4+CHECKBOX_WIDTH*2, PADDING_TOP, LABLE_WEIGHT, LABLE_HEIGHT);
        panel.add(city);
        // 创建一个下拉列表框
        final JComboBox<String> cityComboBox = new JComboBox<>(new String[]{"--请选择--", "北京", "上海", "武汉", "深圳12", "深圳11", "深圳0", "9深圳", "深圳8", "深圳7", "深圳6", "深圳5", "深圳4", "深圳3", "深2圳", "深圳1"});
        cityComboBox.setBounds(PADDING_LEFT+LABLE_WEIGHT*3+PADDING_TEN*4+CHECKBOX_WIDTH*2, PADDING_TOP, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        panel.add(cityComboBox);

        // 添加一个标签
        JLabel country = new JLabel("国家：");
        country.setBounds(PADDING_LEFT+LABLE_WEIGHT*3+PADDING_TEN*5+CHECKBOX_WIDTH*3, PADDING_TOP, LABLE_WEIGHT, LABLE_HEIGHT);
        panel.add(country);

        // 创建一个下拉列表框
        final JComboBox<String> countryComboBox = new JComboBox<>(new String[]{"--请选择--", "中国", "韩国", "泰国", "新加坡"});
        countryComboBox.setBounds(PADDING_LEFT+LABLE_WEIGHT*4+PADDING_TEN*5+CHECKBOX_WIDTH*3, PADDING_TOP, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        panel.add(countryComboBox);


        // 添加一个标签
        JLabel language = new JLabel("语言：");
        language.setBounds(PADDING_LEFT+LABLE_WEIGHT*4+PADDING_TEN*6+CHECKBOX_WIDTH*4, PADDING_TOP, LABLE_WEIGHT, LABLE_HEIGHT);
        panel.add(language);

        // 创建一个下拉列表框
        final JComboBox<String> languageComboBox = new JComboBox<>(new String[]{"--请选择--", "中国", "韩国", "泰国", "新加坡"});
        languageComboBox.setBounds(PADDING_LEFT+LABLE_WEIGHT*5+PADDING_TEN*6+CHECKBOX_WIDTH*4, PADDING_TOP, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        panel.add(languageComboBox);


        // 显示窗口
        jf.setContentPane(panel);
        jf.setVisible(true);
        jf.setResizable(false);

        /*
         * 也可以在 jf.setVisible(true) 之后添加按钮
         *
         * PS_01: jf.setVisible(true) 之后，内容面板才有宽高;
         * PS_02: 使用其他布局时, jf.setVisible(true) 之后添加的组件, 也会被当做是绝对布局来布置该组件（即需要手动指定坐标和宽高）;
         * PS_03: 使用其他布局时, jf.setVisible(true) 之前添加的组件, 如果在 jf.setVisible(true) 之后手动设置该组件的坐标和宽高,
         *        会将该组件当做绝对布局来对待（即设置坐标和宽高会生效）。
         */
        JButton billing  = new JButton("开单");
        // 把按钮位置设置在内容面板右下角, 并且设置按钮宽高为 100, 50
        billing.setBounds((panel.getWidth() - 100)/2, panel.getHeight() - 100, 100, 50);
        billing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击按钮, 显示新的一个窗口
                showNewWindow(jf);
            }
        });
        panel.add(billing);
    }

    public static void showNewWindow(JFrame relativeWindow) {
        // 创建一个新窗口
        JFrame newJFrame = new JFrame("新的窗口");

        newJFrame.setSize(500, 500);

        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setLocationRelativeTo(relativeWindow);

        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 窗口设置为不可改变大小
        newJFrame.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(1, 1));

        // 在新窗口中显示一个标签
        JLabel label = new JLabel("这是一个窗口");
        label.setFont(new Font(null, Font.PLAIN, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label);

        newJFrame.setContentPane(panel);
        newJFrame.setVisible(true);
    }

    public static void setLable(JLabel lable){
//        lable.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lable.setHorizontalAlignment(SwingConstants.CENTER);
        lable.setVerticalAlignment(SwingConstants.CENTER);
    }
}
