package org.example.web3.managedBeansFolder;

public interface AttemptStatsMBean {
    int getTotalAttempts();
    int getTotalMisses();
    void checkForConsecutiveMisses();
}