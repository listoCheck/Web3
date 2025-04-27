package org.example.web3;

import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Named;
import org.example.web3.baze.Test;
import org.example.web3.managedBeansFolder.AttemptStats;
import org.example.web3.managedBeansFolder.HitRatio;
import org.primefaces.PrimeFaces;

import javax.management.*;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
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

    private final AttemptStats statsMBean = new AttemptStats();
    private final HitRatio hitRatioMBean = new HitRatio();


    public void init(@Observes @Initialized(SessionScoped.class) Object unused) {
        MBeanRegistry.registerBean(statsMBean, "attemptStats");
        MBeanRegistry.registerBean(hitRatioMBean, "hitRatio");
    }

    public void destroy(@Observes @Destroyed(SessionScoped.class) Object unused) {
        MBeanRegistry.unregisterBean(statsMBean);
        MBeanRegistry.unregisterBean(hitRatioMBean);
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
        statsMBean.updateAttempt(isPointInside());
        hitRatioMBean.updateStats(isPointInside());
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
