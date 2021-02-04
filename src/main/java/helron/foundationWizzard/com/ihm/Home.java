package helron.foundationWizzard.com.ihm;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import helron.foundationWizzard.com.Main;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Home extends JPanel {
    public Home() {
        LayoutManager layout = new BorderLayout();
        setLayout(layout);
        Panel leftpanel = new Panel();
        leftpanel.setBackground(new Color(255,255,255));
        JTextArea jta = new JTextArea("test");
        JButton jb= new JButton("test");
        leftpanel.add(jta);
        add(jb);
        add(leftpanel,BorderLayout.WEST);
    }

    public static void main(String[] args) {
        InputStream source = Main.class.getResourceAsStream("/struct.json");

        //Read
        InputStreamReader reader = new InputStreamReader(source, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        ApiStructuresExtractor structures = new ApiStructuresExtractor(dictionary);




        Frame frame = new MainFrame("testtitle");

        frame.setResizable(true);
        Panel panel = new Panel();


        JPanel body = new JPanel(new BorderLayout());
        JPanel horizontalBlue = new JPanel();
        horizontalBlue.setBackground(new Color(67,119,202));
        horizontalBlue.setPreferredSize(new Dimension(1024,50));


        FormsContainer myTab = new FormsContainer("BUILDING", structures);
        panel.add(myTab,BorderLayout.CENTER);

        LayoutManager layout = new BorderLayout();
        panel.setLayout(layout);

        JPanel leftpanel = new JPanel();
        LayoutManager layoutLeftPanel = new BorderLayout();
        leftpanel.setLayout(layoutLeftPanel);

        JPanel verticalSeparator = new JPanel();
        verticalSeparator.setBackground(new Color(67,119,202));
        LayoutManager layoutVerticalSeparator = new GridLayout(5,1);
        verticalSeparator.setLayout(layoutVerticalSeparator);


        JPanel selectMenu = new JPanel(new GridLayout(2,1));
            selectMenu.setOpaque(false);
        JTextField subMenu = new JTextField("Select your form");
            subMenu.setBackground(new Color(0x5194E8));
            subMenu.setFont(new Font("Serif", Font.BOLD, 15));
            subMenu.setForeground(Color.white);
            subMenu.setOpaque(true);
            subMenu.setEditable(false);
            subMenu.setBorder(new EmptyBorder(0,0,0,0));
            subMenu.setHorizontalAlignment(JTextField.CENTER);
        JComboBox<String> formSelector = new JComboBox<>();
            formSelector.setForeground(Color.white);
            formSelector.setBorder(BorderFactory.createEmptyBorder());
            formSelector.setOpaque(false);
            formSelector.setFocusable(false);
            formSelector.setBackground(new Color(0x5194E8));
            formSelector.setEditable(true);
        JTextField boxField = (JTextField)formSelector .getEditor().getEditorComponent();
            boxField.setHorizontalAlignment(JTextField.HORIZONTAL);
            boxField.setFont(new Font("Serif", Font.BOLD, 15));
            boxField.setForeground(Color.white);
            boxField.setBorder(BorderFactory.createEmptyBorder());
            boxField.setBackground(new Color(0x5194E8));
            boxField.setFocusable(false);
            formSelector.addItem("BUILDING");
            formSelector.addItem("EVENT");

        JPanel loadSavePanel = new JPanel();
            GridBagLayout loadSavePanelLayout = new GridBagLayout();
            GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 1;
                gbc.insets = new Insets(0,0,0,15);
                gbc.fill= GridBagConstraints.BOTH;
            loadSavePanel.setLayout(loadSavePanelLayout);
            loadSavePanel.setBackground(new Color(143, 198, 246));
            loadSavePanel.setOpaque(true);
        JImagePanel saveIcon = new JImagePanel("src/main/resources/save-files.png");
            saveIcon.setStretch(true);
            saveIcon.setPreferredSize(new Dimension(50,50));

        JImagePanel loadIcon = new JImagePanel("src/main/resources/folder.png");
            loadIcon.setStretch(true);
            loadIcon.setPreferredSize(new Dimension(50,50));
        JImagePanel deleteIcon = new JImagePanel("src/main/resources/delete.png");
            deleteIcon.setStretch(true);
            deleteIcon.setPreferredSize(new Dimension(50,50));

        loadSavePanel.add(saveIcon, gbc);
        loadSavePanel.add(loadIcon);
        loadSavePanel.add(deleteIcon);


        selectMenu.add(subMenu);
        selectMenu.add(formSelector);


        JPanel menu = new JPanel();
        menu.setBackground(new Color(22,33,53));
        LayoutManager layoutMenu = new GridLayout(6,1);
        menu.setLayout(layoutMenu);


        JImagePanel logo = new JImagePanel("src/main/resources/fiefdomLogo.png");
        logo.setOpaque(false);
        logo.setStretch(true);
        logo.setPreferredSize(new Dimension(250,250));

        JPanel emptyUpLeftCorner = new JPanel();
        emptyUpLeftCorner.setPreferredSize(new Dimension(150,150));
        emptyUpLeftCorner.setOpaque(false);

        JTextField title01 = new JTextField("HOME");
        title01.setHorizontalAlignment(JTextField.CENTER);
        title01.setForeground(Color.white);
        title01.setFont(new Font("Serif", Font.BOLD, 20));
        title01.setOpaque(false);
        title01.setEditable(false);
        title01.setBorder(new EmptyBorder(0,0,0,0));

        JTextField title02 = new JTextField();
        title02.setText("   WIKI   ");
        title02.setHorizontalAlignment(JTextField.CENTER);
        title02.setForeground(Color.white);
        title02.setFont(new Font("Serif", Font.BOLD, 20));
        title02.setOpaque(false);
        title02.setEditable(false);
        title02.setBorder(new EmptyBorder(0,0,0,0));

        JTextField title03 = new JTextField();
        title03.setText("   UPDATE   ");
        title03.setHorizontalAlignment(JTextField.CENTER);
        title03.setForeground(Color.white);
        title03.setFont(new Font("Serif", Font.BOLD, 20));
        title03.setOpaque(false);
        title03.setEditable(false);
        title03.setBorder(new EmptyBorder(0,0,0,0));



        menu.add(emptyUpLeftCorner);
        menu.add(title01);
        menu.add(title02);
        menu.add(title03);

        verticalSeparator.add(logo);
        verticalSeparator.add(selectMenu);
        verticalSeparator.add(emptyUpLeftCorner);
        verticalSeparator.add(loadSavePanel);

        leftpanel.add(menu);
        leftpanel.add(verticalSeparator, BorderLayout.EAST);

        body.add(horizontalBlue,BorderLayout.NORTH);
        body.add(myTab);
        panel.add(body);
        panel.add(leftpanel,BorderLayout.WEST);
        frame.setVisible(true);
        frame.add(panel);
    }
}
