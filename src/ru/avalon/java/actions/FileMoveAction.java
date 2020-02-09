package ru.avalon.java.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    private String sourcePath;
    private String targetPath;
    private String fileName;
    private Thread moveThread;

    public FileMoveAction(String sourcePath, String targetPath, String fileName) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.fileName = fileName;
        moveThread = new Thread(this, "moveFile");
        moveThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        String fullPath = targetPath + "/" + fileName;
        try {
            Files.createFile(Paths.get(fullPath));
            Files.copy(Paths.get(sourcePath), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(sourcePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        /*
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */
        System.out.println("Your file was successfully moved to the path " + this.targetPath +
                "! \nTo finish print 'exit'.");
    }

    public Thread getMoveThread() {
        return moveThread;
    }
}
