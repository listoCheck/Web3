package org.example.web3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("bean")
@SessionScoped
public class PointCheckerBean implements Serializable {

    private int x;
    private double y;
    private double r = 1.0;
    private String result;

    // Список для хранения результатов
    private List<PointResult> results = new ArrayList<>();

    // Геттеры и сеттеры для X, Y, R и result
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

    // Метод для проверки точки
    public void checkPoint() {
        if (isPointInside()) {
            result = "YES";
        } else {
            result = "NO";
        }
        // Добавляем результат в список
        results.add(new PointResult(x, y, r, result));
    }

    // Логика проверки попадания в область (примерная)
    private boolean isPointInside() {
        // Проверка по фигурам на графике (например, круг, треугольник, прямоугольник)
        return (x >= 0 && y >= 0 && x * x + y * y <= r * r) ||  // Четверть круга
                (x <= 0 && y <= 0 && x >= -r && y >= -r / 2) ||  // Прямоугольник
                (x >= 0 && y <= 0 && y >= x - r / 2);             // Треугольник
    }

    // Геттер для списка результатов
    public List<PointResult> getResults() {
        return results;
    }

    public void setResults(List<PointResult> results) {
        this.results = results;
    }
}
