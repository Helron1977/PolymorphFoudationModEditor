package helron.foundationwizard.com.ui.customcomponents;

import helron.foundationwizard.com.Main;
import helron.foundationwizard.com.api.LuaGenerator;
import helron.foundationwizard.com.ui.customcomponents.JImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;

public class LuaScriptFrame extends JFrame{
    private JPanel consolePanel;
    private final LuaGenerator luaGenerator;
    private JTextArea luaScriptContainer;
    private String script;

    public LuaScriptFrame(LuaGenerator luaGenerator) throws IOException {
        this.luaGenerator = luaGenerator;


        init();

        initPanel();

        initContainer();

        initScript();


        luaScriptContainer.append(luaGenerator.InitializeLuaTable("myMod"));
        consolePanel.add(luaScriptContainer);
        JScrollPane scrollPane = new JScrollPane(consolePanel);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        add(scrollPane);

    }

    private void initScript() {
        this.script=luaGenerator.InitializeLuaTable("myMod");
    }

    private void init() {
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        };
        addWindowListener(l);

        getRootPane().setOpaque(true);

        getLayeredPane().setOpaque(false);
        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);


    }

    private void initPanel() throws IOException {
        InputStream background = Main.class.getResourceAsStream("/parchemin.png");
        Image backGround = ImageIO.read(background);
        consolePanel = new JImagePanel(backGround);

        consolePanel.setLayout(new GridLayout());

    }

    private void initContainer() {
        luaScriptContainer = new JTextArea();
        luaScriptContainer.setOpaque(true);
        luaScriptContainer.setFont(new Font("arial",Font.BOLD,10));
        luaScriptContainer.setEditable(true);
        luaScriptContainer.setAlignmentX(CENTER_ALIGNMENT);


    }

}
