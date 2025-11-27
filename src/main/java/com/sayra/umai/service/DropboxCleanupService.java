package com.sayra.umai.service;

public interface DropboxCleanupService {
    void cleanOldTempFiles();
    int manualCleanup(String subfolder, int daysOld);
    int cleanupAllTempFiles();
}
