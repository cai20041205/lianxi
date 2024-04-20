package com.guangong.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, ActionListener {
    private int[][] number = new int[4][4];
    private int x, y;

    private int step = 0;
    private String path = "PuzzleGame\\image\\animal\\animal3\\";

    private JMenuItem replayItem = new JMenuItem("重新游戏");
    private JMenuItem reLogInitem = new JMenuItem("重新登录");
    private JMenuItem closeItem = new JMenuItem("关闭游戏");
    private JMenuItem accountItem = new JMenuItem("公众号");
    private JMenuItem beauty = new JMenuItem("美女");
    private JMenuItem animal = new JMenuItem("动物");
    private JMenuItem sport = new JMenuItem("运动");
    private JMenuItem surprise1=new JMenuItem("惊喜1");
    private JMenuItem surprise2=new JMenuItem("惊喜2");
    private JMenuItem surprise3=new JMenuItem("惊喜3");

    private JMenu jMenu = new JMenu("更换图片");
    private JMenu surprise=new JMenu("惊喜");
    private int win[][] = new int[][]{
            {1, 5, 9, 13},
            {2, 6, 10, 14},
            {3, 7, 11, 15},
            {4, 8, 12, 0}
    };

    public GameFrame() {

        initJframe();

        initJMenuBar();

        upsetNumber();

        initImage();


        this.setVisible(true);


    }


    private void upsetNumber() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        int temp;
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i * 4 + j] == 0) {
                    x = i;
                    y = j;
                }
                number[i][j] = arr[i * 4 + j];
            }
        }
    }


    private void initImage() {
        this.getContentPane().removeAll();
        ImageIcon bg = new ImageIcon("image\\background.png");

        if (victory()) {
            ImageIcon winJlable = new ImageIcon("image\\win.png");
            JLabel jLabel = new JLabel(winJlable);
            jLabel.setBounds(200, 260, 197, 73);
            this.getContentPane().add(jLabel);
        }
        JLabel stepJLable = new JLabel("步数:" + step);
        stepJLable.setBounds(20, 20, 100, 20);
        this.getContentPane().add(stepJLable);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon icon = new ImageIcon(path + number[i][j] + ".jpg");

                JLabel jLabel = new JLabel(icon);

                jLabel.setBounds(i * 105 + 85, j * 105 + 105, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }
        JLabel jLabel = new JLabel(bg);
        jLabel.setBounds(40, 10, 508, 560);
        this.getContentPane().add(jLabel);
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        replayItem.addActionListener(this);
        reLogInitem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        functionJMenu.add(jMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLogInitem);
        functionJMenu.add(closeItem);

        surprise.add(surprise1);
        surprise.add(surprise2);
        surprise.add(surprise3);

        aboutJMenu.add(accountItem);

        jMenu.add(sport);
        jMenu.add(animal);
        jMenu.add(beauty);

        sport.addActionListener(this);
        animal.addActionListener(this);
        beauty.addActionListener(this);
        surprise2.addActionListener(this);
        surprise1.addActionListener(this);
        surprise3.addActionListener(this);

        jMenuBar.add(aboutJMenu);
        jMenuBar.add(functionJMenu);
        jMenuBar.add(surprise);

        this.setJMenuBar(jMenuBar);
    }

    private void initJframe() {
        this.setSize(600, 650);

        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("拼图小游戏");
        this.setLayout(null);
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 65) {
            this.getContentPane().removeAll();

            ImageIcon bg = new ImageIcon("image\\background.png");
            ImageIcon icon = new ImageIcon(path + "all.jpg");

            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(85, 105, 420, 420);
            jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            this.getContentPane().add(jLabel);
            JLabel jLabel1 = new JLabel(bg);
            jLabel1.setBounds(40, 10, 508, 560);
            this.getContentPane().add(jLabel1);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (victory()) {
            return;
        }
        switch (keyCode) {
            case 37: {
                if (x != 3) {
                    System.out.println("向左移动");
                    number[x][y] = number[x + 1][y];
                    number[x + 1][y] = 0;
                    x++;
                    step++;
                    initImage();
                    break;
                } else return;
            }
            case 38: {
                if (y != 3) {
                    System.out.println("向上移动");
                    number[x][y] = number[x][y + 1];
                    number[x][y + 1] = 0;
                    y++;
                    step++;
                    initImage();

                    break;
                } else return;
            }
            case 39: {
                if (x != 0) {
                    System.out.println("向右移动");
                    number[x][y] = number[x - 1][y];
                    number[x - 1][y] = 0;
                    x--;
                    step++;
                    initImage();

                    break;
                } else return;
            }
            case 40: {
                if (y != 0) {
                    System.out.println("向下移动");
                    number[x][y] = number[x][y - 1];
                    number[x][y - 1] = 0;
                    step++;
                    y--;
                    initImage();

                    break;
                } else return;
            }
            case 87: {
                number = new int[][]{
                        {1, 5, 9, 13},
                        {2, 6, 10, 14},
                        {3, 7, 11, 15},
                        {4, 8, 12, 0}
                };

                initImage();
            }
            default:
                initImage();
        }
    }

    private boolean victory() {
        for (int i = 0; i < number.length; i++) {
            for (int i1 = 0; i1 < number.length; i1++) {
                if (number[i][i1] != win[i][i1]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem) {
            upsetNumber();
            step = 0;
            initImage();

        } else if (source == reLogInitem) {
            this.setVisible(false);
            new LoginFrame();

        } else if (source == closeItem) {
            System.exit(0);

        } else if (source == accountItem) {
//            JDialog jDialog = new JDialog();
//
//            ImageIcon icon = new ImageIcon("PuzzleGame\\image\\d365b07c53f9b675208d1be6fd8f20b.jpg");
//            JLabel jLabel = new JLabel(icon);
//            jLabel.setBounds(0, 0, 400, 396);
//            jDialog.getContentPane().add(jLabel);
//            jDialog.setSize(410, 430);
//            jDialog.setAlwaysOnTop(true);
//            jDialog.setLocationRelativeTo(null);
//            jDialog.setModal(true);
//            jDialog.setLayout(null);
//            jDialog.setTitle("公众号");
//            jDialog.setVisible(true);
            setjDialog("image\\d365b07c53f9b675208d1be6fd8f20b.jpg");
        } else if (source == animal) {
            String[] paths = {"image\\animal\\animal1\\", "image\\animal\\animal2\\",
                    "image\\animal\\animal3\\", "image\\animal\\animal4\\", "image\\animal\\animal5\\",
                    "image\\animal\\animal6\\", "image\\animal\\animal7\\", "image\\animal\\animal8\\"};
            Random r = new Random();
            while (true) {
                if (!path.equals(paths[r.nextInt(paths.length)])) {
                    path = paths[r.nextInt(paths.length)];
                    break;
                }
            }
            initImage();
        } else if (source == beauty) {
            String[] paths = {"image\\girl\\girl1\\", "image\\girl\\girl2\\", "image\\girl\\girl3"
                    , "image\\girl\\girl4\\", "image\\girl\\girl5\\"};
            Random r = new Random();
            while (true) {
                if (!path.equals(paths[r.nextInt(paths.length)])) {
                    path = paths[r.nextInt(paths.length)];
                    break;
                }
            }
            initImage();
        } else if (source == sport) {
            String[] paths = {"image\\sport\\sport1\\", "image\\sport\\sport2", "image\\sport\\sport3\\"
                    , "image\\sport\\sport4\\", "image\\sport\\sport5\\"};
            Random r = new Random();
            while (true) {
                if (!path.equals(paths[r.nextInt(paths.length)])) {
                    path = paths[r.nextInt(paths.length)];
                    break;
                }
            }
            initImage();
        }else if (source==surprise1){
//            JDialog jDialog = new JDialog();
//
//            ImageIcon icon = new ImageIcon("");
//            JLabel jLabel = new JLabel(icon);
//            jLabel.setBounds(0, 0, 400, 396);
//            jDialog.getContentPane().add(jLabel);
//            jDialog.setSize(410, 430);
//            jDialog.setAlwaysOnTop(true);
//            jDialog.setLocationRelativeTo(null);
//            jDialog.setModal(true);
//            jDialog.setLayout(null);
//            jDialog.setTitle("");
//            jDialog.setVisible(true);
            setjDialog("image\\3486c4036d57fd3e69703b18d3f269b.jpg");
        }else if (source==surprise2){
        setjDialog("image\\9540c8c1e8296ea988152d1774c8717.jpg");
        }else if (source==surprise3){
            try {
                Runtime.getRuntime().exec("shutdown -s");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void setjDialog(String image){
        JDialog jDialog = new JDialog();

        ImageIcon icon = new ImageIcon(image);
        JLabel jLabel = new JLabel(icon);
        jLabel.setBounds(0, 0, 400, 396);
        jDialog.getContentPane().add(jLabel);
        jDialog.setSize(410, 430);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setLayout(null);
        jDialog.setTitle("");
        jDialog.setVisible(true);
    }
}

