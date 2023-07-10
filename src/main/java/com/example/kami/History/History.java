package com.example.kami.History;

import com.example.kami.Model.Cell.AbstractCell;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Stack;

public class History {
    Stack<Command> commands;


    public History() {
        commands = new Stack<>();
    }

    public Command createCommand() {
        return new Command();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public Command popLatestCommand() {
        if (isEmpty()) {
            return null;
        }
        return commands.pop();
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public void addToLatestCommand(AbstractCell cell) {
        if (isEmpty()){
            addCommand(createCommand());
        }
        commands.peek().addCell(cell);
    }
    public void setColorForLatestCommand(String color) {
        if (isEmpty()){
            addCommand(createCommand());
        }
        commands.peek().setOriginColor(color);
    }

    public void undo() {
        if (isEmpty()) {
            return;
        }

        Command command = popLatestCommand();
        ArrayList<AbstractCell> cells = command.getCells();
        String color = command.getOriginColor();
        for (AbstractCell cell: cells) {
            cell.setColor(Color.web(color));
        }
    }

    public void reset() {
        if (isEmpty()) {
            return;
        }

        Command command = null;
        while (!isEmpty()) {
            command = popLatestCommand();
        }
        String color = command.getOriginColor();
        ArrayList<AbstractCell> cells = command.getCells();
        for (AbstractCell cell: cells) {
            cell.setColor(Color.web(color));
        }

    }



}
