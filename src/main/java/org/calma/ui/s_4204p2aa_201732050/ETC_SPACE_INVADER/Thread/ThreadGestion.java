package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread;



import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.App_javafx;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadGestion {
    private static BlockingQueue<Command> commandQueue = new ArrayBlockingQueue<>(100);

    public BlockingQueue<Command> getCommandQueue() {
        return commandQueue;
    }

    public void setCommandQueue(BlockingQueue<Command> commandQueue) {
        ThreadGestion.commandQueue = commandQueue;
    }

    public void sendCommandToGame(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void focusSlickWindow() {
        try {
            // Assurez-vous que le titre de la fenêtre Slick2D est unique
            String title = App_javafx.getTitre(); // Remplacez par le titre réel de la fenêtre
            String cmd = "cmd /c \"echo off | powershell -Command \"$w=(New-Object -ComObject WScript.Shell); $w.AppActivate('" + title + "')\"\"";
            Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
