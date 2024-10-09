package org.example.web3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("bean")
@SessionScoped
public class PointCheckerBean implements Serializable {

    private int x = 0;
    private double y = 0;
    private double r = 0;
    private String result;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void checkPoint() {
        if ((x >= 0 && y >= 0 && x <= r && y <= r / 2) || // Прямоугольник
                (x >= 0 && y <= 0 && (x*x + y*y) <= (r*r)) || // Сектор
                (x <= 0 && y >= 0 && y <= (-0.5 * x + r / 2))) { // Треугольник
            result = x + " " + y + " " + r + " " + "YES";
        } else {
            result = x + " " + y + " " + r + " " + "NO";
        }
    }

    public String getButtonStyle(int buttonX) {
        return "active_button";
    }

}
