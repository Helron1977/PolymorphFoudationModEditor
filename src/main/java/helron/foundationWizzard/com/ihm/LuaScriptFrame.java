package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.Main;
import helron.foundationWizzard.com.api.LuaGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;

public class LuaScriptFrame extends JFrame{
    public LuaScriptFrame(LuaGenerator lg) throws IOException {

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        };
        addWindowListener(l);
        setUndecorated(true);
        getRootPane().setOpaque(false);
        getLayeredPane().setOpaque(false);
        setBackground(new Color(255, 0, 0, 1));
        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);



        InputStream background = Main.class.getResourceAsStream("/parchemin.png");
        Image backGround = ImageIO.read(background);
        JImagePanel consolePanel = new JImagePanel(backGround);

        LayoutManager layoutManager = new GridBagLayout();
        consolePanel.setLayout(layoutManager);

        JTextArea luaScriptContainer = new JTextArea();
        luaScriptContainer.setOpaque(false);
        luaScriptContainer.append(lg.InitializeLuaTable("myMod"));
        luaScriptContainer.setFont(new Font("arial",Font.BOLD,10));
        luaScriptContainer.setEditable(true);


        consolePanel.add(luaScriptContainer);
        add(consolePanel);
    }
}
