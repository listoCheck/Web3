package org.example.web3;

public class PointResult {
    private int x;
    private double y;
    private double r;
    private String result;

    // Конструктор
    public PointResult(int x, double y, double r, String result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    // Геттеры и сеттеры
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
}
