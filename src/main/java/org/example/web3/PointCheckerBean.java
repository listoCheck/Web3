package org.example.web3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.example.web3.baze.Test;
import org.primefaces.PrimeFaces;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.web3.baze.Test.connection;

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

    public void checkPoint() throws SQLException {
        if (isPointInside()) {
            result = "YES";
        } else {
            result = "NO";
        }
        //PrimeFaces.current().executeScript("drawPointRemote()");
        PrimeFaces.current().executeScript("updatePointColors("+ x + "," + y + "," + r + ",\"" + result + "\")");
        PointResult pointResult = new PointResult(x, y, r, result);
        results.add(pointResult);
        try {
            addData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public void addData() throws SQLException {
        try  {
            Test.test();
            Test.statmt.execute("INSERT INTO point_results(x, y, r, result) VALUES ('" + x + "', '" + y + "', '" + r + "', '" + result + "');");
            PrimeFaces.current().executeScript("message('" + "Данные успешно добавлены." + "');");
        } catch (SQLException e) {
            // Обработка исключения
            PrimeFaces.current().executeScript("message('" + "Ошибка при добавлении данных в базу данных: " + e.getMessage() + "');");
        }
    }

}
