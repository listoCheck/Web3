package org.example.web3.managedBeansFolder;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Named("hitRatio")
@SessionScoped
public class HitRatio implements HitRatioMBean, Serializable {
    private final AtomicInteger totalAttempts = new AtomicInteger();
    private final AtomicInteger totalHits = new AtomicInteger();

    @Override
    public int getTotalAttempts() {
        return totalAttempts.get();
    }

    @Override
    public int getTotalHits() {
        return totalHits.get();
    }

    @Override
    public double getHitRatio() {
        if (totalAttempts.get() == 0) {
            return 0.0;
        }
        return (double) totalHits.get() / totalAttempts.get() * 100.0;
    }

    public void updateStats(boolean hit) {
        totalAttempts.incrementAndGet();
        if (hit) {
            totalHits.incrementAndGet();
        }
    }
}