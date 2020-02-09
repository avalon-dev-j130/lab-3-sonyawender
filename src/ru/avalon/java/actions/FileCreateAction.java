package ru.avalon.java.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Действие, которое создает новые файлы.
 */
public class FileCreateAction implements Action {
    private String path;
    private Thread createThread;

    public FileCreateAction(String path) {
        this.path = path;
        createThread = new Thread(this, "createFile");
        createThread.start();
    }

    @Override
    public void run() {
        try {
            Files.createFile(Paths.get(path));
        } catch (IOException ignored) {
        }
    }

    @Override
    public void close() {
        System.out.println("Your file was successfully created! \nTo finish print 'exit'.");
    }

    public Thread getCreateThread() {
        return createThread;
    }
}
