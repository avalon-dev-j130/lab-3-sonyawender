package ru.avalon.java;

import ru.avalon.java.actions.FileCopyAction;
import ru.avalon.java.actions.FileCreateAction;
import ru.avalon.java.actions.FileDeleteAction;
import ru.avalon.java.actions.FileMoveAction;
import ru.avalon.java.console.ConsoleUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка
 * многоуровневых приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {
    /**
     * Точка входа в приложение.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        new Lab3().run();
    }
    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием
     * перечисления {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        String sourcePath;
        String targetPath;
        String[] fileName;
        BufferedReader reader;
        switch (command) {
            case create:
                reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Target directory of the new file: ");
                targetPath = reader.readLine();
                FileCreateAction createAction = new FileCreateAction(targetPath);
                createAction.start();
                if(createAction.getCreateThread().isAlive()){
                    try {
                        createAction.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case copy:
                /*
                 * TODO №6 Обработайте команду copy
                 */
                reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Source directory of file to be copied: ");
                sourcePath = reader.readLine();
                System.out.print("Target directory of the copied file: ");
                targetPath = reader.readLine();
                FileCopyAction copyAction = new FileCopyAction(sourcePath, targetPath);
                if(copyAction.getCopyThread().isAlive()) {
                    try {
                        copyAction.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case move:
                /*
                 * TODO №7 Обработайте команду move
                 */
                reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Source directory of file to be moved: ");
                sourcePath = reader.readLine();
                fileName = sourcePath.split("/");
                System.out.print("Target directory of the moved file: ");
                targetPath = reader.readLine();
                FileMoveAction moveAction = new FileMoveAction(sourcePath, targetPath, fileName[fileName.length-1]);
                if(moveAction.getMoveThread().isAlive()) {
                    try {
                        moveAction.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case delete:
                reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Source directory of file to be deleted: ");
                targetPath = reader.readLine();
                FileDeleteAction deleteAction = new FileDeleteAction(targetPath);
                if(deleteAction.getDeleteThread().isAlive()) {
                    try {
                        deleteAction.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case exit:
                close();
                break;
                /*
                 * TODO №9 Обработайте необработанные команды
                 */
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
