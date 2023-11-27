package com.github.bbugsco.advancedlog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Objects;

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

        // Get file from path
        this.date = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
        File logFile = new File(PATH, date + ".log");

        // Check if file exists, otherwise create file
        if (!logFile.exists()) {
            try {
                if (logFile.createNewFile()) {
                    this.plugin.getLogger().info("Created log file " + logFile.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Create PrintWriter instance
        FileWriter fw;
        try {
            fw = new FileWriter(logFile, true);
            this.writer = new PrintWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String logEntry) {

        // Check if date is current, if not then update writer
        String date = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
        if (!Objects.equals(this.date, date)) {
            close();
            initializeWriter();
        }

        this.writer.write(System.currentTimeMillis() + " " + logEntry);
        this.writer.flush();

    }

    public void close() {
        this.writer.close();
    }

}