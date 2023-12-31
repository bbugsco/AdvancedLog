package com.github.bbugsco.advancedlog.Logging;

import com.github.bbugsco.advancedlog.AdvancedLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class Logger {

    private final AdvancedLog plugin;
    private final String PATH;

    private String date;
    private PrintWriter writer;

    public Logger(AdvancedLog plugin) {
        this.plugin = plugin;
        this.PATH = plugin.getConfig().getString("log_path");
        initializeWriter();
    }

    private void initializeWriter() {

        // Create directory
        File dir = new File(PATH);
        if (dir.mkdirs()) {
            this.plugin.getLogger().info("Created log directory " + dir);
        }

        // Create log file
        this.date = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
        File logFile = new File(PATH, date + ".log");

        // Check if file exists, otherwise create file
        if (!logFile.exists()) {
            try {
                if (logFile.createNewFile()) {
                    this.plugin.getLogger().info("Created log file " + logFile.getName());
                }
            } catch (IOException e) {
                e.fillInStackTrace();
                plugin.getLogger().warning("Failed to create log file " + logFile.getName());
                plugin.getLogger().warning(e.getMessage());
            }
        }

        // Create PrintWriter instance
        FileWriter fw;
        try {
            fw = new FileWriter(logFile, true);
            this.writer = new PrintWriter(fw);
        } catch (IOException e) {
            e.fillInStackTrace();
            plugin.getLogger().warning("Failed to create log file " + logFile.getName());
            plugin.getLogger().warning(e.getMessage());
        }
    }

    public void log(String log) {

        // Check if date is current, if not then update writer
        String date = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
        if (!java.util.Objects.equals(this.date, date)) {
            close();
            initializeWriter();
        }

        // Create full log string
        String stringBuilder = "[" + new java.util.Date(System.currentTimeMillis()) + "] " + log + "\n";
        this.writer.write(stringBuilder);
        this.writer.flush();

    }

    public void close() {
        this.writer.close();
    }

}
