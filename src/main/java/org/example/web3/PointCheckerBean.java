package org.example.web3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("bean")
@SessionScoped
public class PointCheckerBean implements Serializable {

    private double x;
    private double y;
    private double r = 1.0;
    private String result;

    private List<PointResult> results = new ArrayList<>();

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

    public void checkPoint() {
        if (isPointInside()) {
            result = "YES";
        } else {
            result = "NO";
        }
        results.add(new PointResult(getX(), getY(), getR(), result));
    }

    private boolean isPointInside() {
        return (x <= 0 && y <= 0 && x * x + y * y <= r * r) ||
                (x >= 0 && y >= 0 && y <= - x + r/2) ||
                (x >= 0 && y <= 0 && y >= -r && x <= r/2);
    }

    public List<PointResult> getResults() {
        return results;
    }

    public void setResults(List<PointResult> results) {
        this.results = results;
    }

    public void setCoordinates(double x, double y) {
        this.x = Math.round(x * 100.0) / 100.0;
        this.y = Math.round(y * 100.0) / 100.0;
    }
}
