package org.example.web3;

public class PointResult {
    private double x;
    private double y;
    private double r;
    private String result;

    public PointResult(double x, double y, double r, String result) {
        this.x = Math.round(x * 100.0) / 100.0;
        this.y = Math.round(y * 100.0) / 100.0;
        this.r = Math.round(r * 100.0) / 100.0;
        this.result = result;
    }

    public double getX() {
        return Math.round(x * 100.0) / 100.0;
    }

    public void setX(int x) {
        this.x = Math.round(x * 100.0) / 100.0;
    }

    public double getY() {
        return Math.round(y * 100.0) / 100.0;
    }

    public void setY(double y) {
        this.y = Math.round(y * 100.0) / 100.0;
    }

    public double getR() {
        return Math.round(r * 100.0) / 100.0;
    }

    public void setR(double r) {
        this.r = Math.round(r * 100.0) / 100.0;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
