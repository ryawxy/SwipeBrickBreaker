import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Random;

public class Item extends Rectangle {

    private int x;

    private int y;
    private double width;
    private double height;
    private int feature;
    private int feature2;
    private static final ArrayList<Item> items = new ArrayList<>();

    Random rand = new Random();

    public Item(int x, int y, double width, double height, int feature, int feature2 ){

     this.x = x;
     this.y = y;
     this.width = width;
     this.height = height;
     this.feature = feature;
     this.feature2 = feature2;


    }
    public static ArrayList<Item> getItems(){

        return items;
    }


    public int getx() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    public int gety() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public double getW() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getH() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }

    public void addItem(Item item){

        items.add(item);

    }

    public int getFeature2() {
        return feature2;
    }

    public void setFeature2(int feature2) {
        this.feature2 = feature2;
    }
}
