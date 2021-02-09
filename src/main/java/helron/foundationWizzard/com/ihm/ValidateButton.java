package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ValidateButton extends JButton {
    public ValidateButton() {
        super("Validate");
        this.addActionListener(e -> {
            try {
                LuaGenerator lg = new LuaGenerator(Form.inputs);

                Frame console = new Frame("Lua Code");
                WindowListener l = new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                };
                console.addWindowListener(l);

                JPanel consolePanel= new JPanel();
                JTextArea luaScriptContainer = new JTextArea();
                luaScriptContainer.append(lg.InitializeLuaTable("myMod"));

                consolePanel.add(luaScriptContainer);
                console.add(consolePanel);
                console.setVisible(true);
                console.setSize(150,150);
                console.setResizable(true);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
