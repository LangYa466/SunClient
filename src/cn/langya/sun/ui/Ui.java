package cn.langya.sun.ui;

/**
 * @author LangYa
 * @ClassName Ui
 * @date 2023/12/28 20:58
 * @Version 1.0
 */

public class Ui extends UIManager {

    public int x;
    public int y;
    public int width;
    public int height;
    public int moveX;
    public int moveY;
    public boolean state;


    public Ui(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }




}
