package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.Main;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

public class Home extends JPanel {
    public Home(ApiStructuresExtractor structures) throws IOException {
        Frame frame = new MainFrame("Foundation Mod Editor");
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
        LayoutManager layoutVerticalSeparator = new GridLayout(3,1);
        verticalSeparator.setLayout(layoutVerticalSeparator);
        verticalSeparator.setPreferredSize(new Dimension(300,1024));


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

        InputStream saveIconeStream =Main.class.getResourceAsStream("/save-files.png");
        JImagePanel saveIcon = new JImagePanel(ImageIO.read(saveIconeStream));
            saveIcon.setStretch(true);
            saveIcon.setPreferredSize(new Dimension(50,50));

        InputStream loadIconStream = Main.class.getResourceAsStream("/folder.png");
        JImagePanel loadIcon = new JImagePanel(ImageIO.read(loadIconStream));
            loadIcon.setStretch(true);
            loadIcon.setPreferredSize(new Dimension(50,50));

        InputStream deleteIconStream = Main.class.getResourceAsStream("/delete.png");
        JImagePanel deleteIcon = new JImagePanel(ImageIO.read(deleteIconStream));
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


        InputStream logoStream =Main.class.getResourceAsStream("/fiefdomLogo.png");
        JImagePanel logo = new JImagePanel(ImageIO.read(logoStream));
        logo.setOpaque(false);
        logo.setStretch(true);
        logo.setBackground(new Color(143, 198, 246));
        logo.setPreferredSize(new Dimension(150,150));


        JLink title01 = new JLink("HOME", "https://www.polymorph.games/");


        JLink title02 = new JLink("WIKI","http://www.polymorph.games/foundation/modding/");


        JtextFileChooser title03 = new JtextFileChooser("   UPDATE   ");




        menu.add(title01);
        menu.add(title02);
        menu.add(title03);


        verticalSeparator.add(logo);
        verticalSeparator.add(loadSavePanel);
        verticalSeparator.add(selectMenu);

        leftpanel.add(menu);
        leftpanel.add(verticalSeparator, BorderLayout.EAST);

        body.add(horizontalBlue,BorderLayout.NORTH);
        body.add(myTab);
        panel.add(body);
        panel.add(leftpanel,BorderLayout.WEST);
        frame.setVisible(true);
        frame.add(panel);
        frame.pack();
        Dimension frameDim = FormsContainer.dim;
        frameDim.setSize(FormsContainer.dim.width*3/4, FormsContainer.dim.height*3/4 );

        frame.setSize(frameDim);

    }
}
