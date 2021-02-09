package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.Main;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Home extends JPanel {


    public Home(ApiStructuresExtractor structures) throws IOException {
        Color darkGreen = new Color(0x0E1D0E);
        Color mediumGreen = new Color(0x183818);
        Color lightGreen = new Color(0x2B632B);
        JFrame frame = new MainFrame("Foundation Mod Editor");
        frame.setResizable(true);
        //Panel panel = new Panel();

        //TODO set a path in resources. set down the quality of the background image
        JImagePanel panel = new JImagePanel("src/main/resources/splash.png");
        panel.setStretch(false);

        UIManager.put("TabbedPane.contentOpaque", false);




        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        JPanel horizontalBlue = new JPanel();
        horizontalBlue.setBackground(mediumGreen);
        horizontalBlue.setPreferredSize(new Dimension(1024,50));


        FormsContainer myTab = new FormsContainer("BUILDING", structures);
        panel.add(myTab,BorderLayout.CENTER);

        LayoutManager layout = new BorderLayout();
        panel.setLayout(layout);

        JPanel leftpanel = new JPanel();
        LayoutManager layoutLeftPanel = new BorderLayout();
        leftpanel.setLayout(layoutLeftPanel);

        JPanel verticalSeparator = new JPanel();
        verticalSeparator.setBackground(lightGreen);
        LayoutManager layoutVerticalSeparator = new GridLayout(3,1);
        verticalSeparator.setLayout(layoutVerticalSeparator);
        verticalSeparator.setPreferredSize(new Dimension(300,1024));


        JPanel selectMenu = new JPanel(new GridLayout(2,1));
        selectMenu.setOpaque(false);
        JTextField subMenu = new JTextField("Select your form");
        subMenu.setBackground(lightGreen);
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
            //formSelector.setBackground(lightGreen);
            formSelector.setEditable(true);
        JTextField boxField = (JTextField)formSelector .getEditor().getEditorComponent();
            boxField.setHorizontalAlignment(JTextField.HORIZONTAL);
            boxField.setFont(new Font("Serif", Font.BOLD, 15));
            boxField.setForeground(Color.white);
            boxField.setBorder(BorderFactory.createEmptyBorder());
            boxField.setBackground(lightGreen);
            boxField.setFocusable(false);
        formSelector.addItem("BUILDING");
        formSelector.addItem("EVENT");
        formSelector.addItemListener(e -> {
            myTab.removeAll();
            Form tab= new Form(Objects.requireNonNull(formSelector.getSelectedItem()).toString(), structures, myTab);
            tab.setOpaque(false);
            myTab.add(formSelector.getSelectedItem().toString(),tab);
        });

        JPanel loadSavePanel = new JPanel();
        GridBagLayout loadSavePanelLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.insets = new Insets(0,0,0,15);
            gbc.fill= GridBagConstraints.BOTH;
            loadSavePanel.setLayout(loadSavePanelLayout);
            loadSavePanel.setBackground(lightGreen);
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
        menu.setBackground(darkGreen);
        LayoutManager layoutMenu = new GridLayout(6,1);
        menu.setLayout(layoutMenu);


        InputStream logoStream =Main.class.getResourceAsStream("/fiefdomLogo.png");
        JImagePanel logo = new JImagePanel(ImageIO.read(logoStream));
        logo.setOpaque(false);
        logo.setStretch(true);
        logo.setBackground(mediumGreen);
        logo.setPreferredSize(new Dimension(150,150));


        JLink title01 = new JLink("HOME", "https://www.polymorph.games/");


        JLink title02 = new JLink("WIKI","http://www.polymorph.games/foundation/modding/");


        JtextFileChooser title03 = new JtextFileChooser("   UPDATE   ");




        menu.add(title01);
        menu.add(title02);
        menu.add(title03);

//test
        verticalSeparator.setOpaque(false);
        leftpanel.setOpaque(false);
        menu.setOpaque(false);
        horizontalBlue.setOpaque(false);
        loadSavePanel.setOpaque(false);
        selectMenu.setOpaque(false);
        boxField.setOpaque(false);
        myTab.setOpaque(false);
        subMenu.setOpaque(false);


        verticalSeparator.add(logo);
        verticalSeparator.add(loadSavePanel);
        verticalSeparator.add(selectMenu);

        leftpanel.add(menu);
        leftpanel.add(verticalSeparator, BorderLayout.EAST);

        body.add(horizontalBlue,BorderLayout.NORTH);
        body.add(myTab);
        panel.add(body);
        //test
        panel.setOpaque(true);

        panel.add(leftpanel,BorderLayout.WEST);
        frame.setVisible(true);
        frame.add(panel);
        frame.pack();
        Dimension frameDim = FormsContainer.dim;
        frameDim.setSize(FormsContainer.dim.width*3/4, FormsContainer.dim.height*3/4 );
        System.out.println(frame.getSize().toString());

        frame.setSize(frameDim);

    }
}
