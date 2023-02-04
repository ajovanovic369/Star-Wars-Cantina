package starwarscantina.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import starwarscantina.logic.Game;
import starwarscantina.utils.Image_Loader;
import starwarscantina.utils.Repository;

public class GUI extends JFrame {

    private static GUI instance = null;
    private Image_Loader loader = new Image_Loader();
    private final int DEFAULT_FRAME_WIDTH = 1920;
    private final int DEFAULT_FRAME_HEIGHT = 1050;
    int cardIncrement = 0;
    int cardSize = 0;
    private static JLabel status = new JLabel();
    private JMenuBar menuBar = null;
    private JButton buttonNewGame = null;
    private JButton buttonHighScore = null;
    private JButton buttonGuide = null;
    private JButton buttonAbout = null;
    private JButton buttonExit = null;
    private Image_Loader bgPanel = null;
    private Image_Loader cardPanel = null;
    private Image_Loader statusPanel = null;
 

    protected GUI() {
    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    public void refreshGUI() {
        this.getContentPane().add(bgPanel);
        this.repaint();
    }

    private Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    private void createGUI() {

        this.setTitle(Repository.GAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = getScreenSize();

        setDefaultResolutionGUI(screenSize);

        this.getContentPane().add(bgPanel);
        this.setJMenuBar(menuBar);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cursor);

        this.setResizable(true);
    }

    public void showGUI() {
        createMenuBar();
        createGUI();
        this.setVisible(true);
    }

    public void resetPanels() {
        bgPanel.removeAll();
        statusPanel.removeAll();
        cardPanel.removeAll();
    }

    public void setDefaultResolutionGUI(Dimension screenSize) {
        this.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        int x = (screenSize.width - DEFAULT_FRAME_WIDTH) / 2;
        int y = (screenSize.height - DEFAULT_FRAME_HEIGHT) / 2;
        this.setLocation(x, y);
    }

    public void createBgPanel() {

        if (bgPanel == null) {
            bgPanel = new Image_Loader();
            BufferedImage bgImage;
            bgImage = loader.loadImage(Repository.MAIN_BACKGROUND);

            bgPanel = new Image_Loader(bgImage);

            bgPanel.revalidate();
            bgPanel.repaint();
        }

        bgPanel.setLayout(null);
    }

    public void createGamePanel(ArrayList<GUI_Card> cards) {

        BufferedImage bgImage;
        bgImage = loader.loadImage(Repository.GAME_BACKGROUND);

        if (cardPanel == null) {
            cardPanel = new Image_Loader(bgImage);
        }
        cardPanel.setLayout(null);

        cardIncrement = Repository.DEFAULT_X_Y_INCREMENT;
        cardSize = Repository.DEFAULT_CARD_SIZE;

        int x = 0;
        int y = 0;
        int cardsSet = 0;

        for (GUI_Card sw : cards) {
            sw.setBounds(Repository.START_X + x, Repository.START_Y + y, cardSize, cardSize);
            cardPanel.add(sw);

            x += cardIncrement;
            cardsSet++;
            if (cardsSet == 5 || cardsSet == 10 || cardsSet == 15) {
                x = 0;
                y += cardIncrement;
            }
        }

        cardPanel.setBounds(435, 100, 1030, 825);

        cardPanel.revalidate();
        cardPanel.repaint();

        bgPanel.add(cardPanel);
    }

    public void mainMenuPanel() {
        
        if (buttonNewGame == null) {
            buttonNewGame = new JButton();
        }
        
        buttonNewGame.setBackground(Color.darkGray);
        buttonNewGame.setIcon(Repository.MAINMENU_NEWGAME);
        buttonNewGame.setBounds(540, 18, 180, 35);
        buttonNewGame.setMnemonic(KeyEvent.VK_N);
        buttonNewGame.addActionListener(new MenuBarListener(Repository.NEWGAME_ACTION));

        if (buttonHighScore == null) {
            buttonHighScore = new JButton();
        }
        
        buttonHighScore.setBackground(Color.darkGray);
        buttonHighScore.setIcon(Repository.MAINMENU_HIGHSCORE);
        buttonHighScore.setBounds(730, 18, 180, 35);
        buttonHighScore.addActionListener(new MenuBarListener(Repository.HIGHSCORE_ACTION));
        
        if (buttonGuide == null) {
            buttonGuide = new JButton();
        }

        buttonGuide.setBackground(Color.darkGray);
        buttonGuide.setIcon(Repository.MAINMENU_GUIDE);
        buttonGuide.setBounds(920, 18, 120, 35);
        buttonGuide.addActionListener(new MenuBarListener(Repository.GUIDE_ACTION));
        
        if (buttonAbout == null) {
            buttonAbout = new JButton();
        }

        buttonAbout.setBackground(Color.darkGray);
        buttonAbout.setIcon(Repository.MAINMENU_ABOUT);
        buttonAbout.setBounds(1050, 18, 180, 35);
        buttonAbout.addActionListener(new MenuBarListener(Repository.ABOUT_ACTION));
        
        if (buttonExit == null) {
            buttonExit = new JButton();
        }

        buttonExit.setBackground(Color.darkGray);
        buttonExit.setIcon(Repository.MAINMENU_EXIT);
        buttonExit.setMnemonic(KeyEvent.VK_Q);
        buttonExit.setBounds(1238, 18, 120, 35);
        buttonExit.addActionListener(new MenuBarListener(Repository.QUIT_ACTION));

        bgPanel.add(buttonNewGame);
        bgPanel.add(buttonHighScore);
        bgPanel.add(buttonGuide);
        bgPanel.add(buttonAbout);
        bgPanel.add(buttonExit);
    }

    public void createStatusPanel() {

        if (statusPanel == null) {
            statusPanel = new Image_Loader();
        }

        statusPanel.setBackground(Color.BLACK);
        status = new JLabel(Repository.TEXT_JLABEL);
        status.setFont(new Font(Repository.TEXT_FONT, Font.PLAIN, 16));
        status.setForeground(Color.WHITE);
        statusPanel.add(status);
        statusPanel.setOpaque(false);

        statusPanel.setBounds(435, 69, 1030, 35);

        bgPanel.add(statusPanel);
    }

    public static void setTextToStatusLabel(String text) {
        status.setText(text);
    }

    private void createMenuBar() {

        menuBar = new JMenuBar();

        JMenu menu = new JMenu(Repository.MENU_FILE);
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        JMenuItem newGame = new JMenuItem(Repository.MENU_NEW_GAME);
        newGame.setMnemonic(KeyEvent.VK_N);
        newGame.addActionListener(new MenuBarListener(Repository.NEWGAME_ACTION));
        menu.add(newGame);

        JMenuItem quit = new JMenuItem(Repository.MENU_QUIT);
        quit.setMnemonic(KeyEvent.VK_I);
        quit.addActionListener(new MenuBarListener(Repository.QUIT_ACTION));
        menu.add(quit);
        
        JMenu menu2 = new JMenu(Repository.MENU_HIGHSCORE);
        menu2.setMnemonic(KeyEvent.VK_R);
        menuBar.add(menu2);
        
        JMenuItem highscore = new JMenuItem(Repository.MENU_HIGHSCORE);
        highscore.setMnemonic(KeyEvent.VK_E);
        highscore.addActionListener(new MenuBarListener(Repository.HIGHSCORE_ACTION));
        menu2.add(highscore);

        JMenu menu3 = new JMenu(Repository.MENU_HELP);
        menu3.setMnemonic(KeyEvent.VK_P);
        menuBar.add(menu3);

        JMenuItem guide = new JMenuItem(Repository.MENU_GUIDE);
        guide.setMnemonic(KeyEvent.VK_V);
        guide.addActionListener(new MenuBarListener(Repository.GUIDE_ACTION));
        menu3.add(guide);

        JMenuItem about = new JMenuItem(Repository.MENU_ABOUT);
        about.setMnemonic(KeyEvent.VK_O);
        about.addActionListener(new MenuBarListener(Repository.ABOUT_ACTION));
        menu3.add(about);
    }

     private class MenuBarListener implements ActionListener {

        private String action = null;

        public MenuBarListener(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action.equals(Repository.QUIT_ACTION)) {
                Game.exitApplication();
            }
            if (action.equals(Repository.NEWGAME_ACTION)) {
                Game.getInstance().startAgain();
            }

            if (action.equals(Repository.ABOUT_ACTION)) {
                Game.getInstance().showAboutDialog();
            }
            if (action.equals(Repository.GUIDE_ACTION)) {
                Game.getInstance().openHelpGuide();
            }
            if (action.equals(Repository.HIGHSCORE_ACTION)) {
                Game.getInstance().highScore();
            }
        }
    }
}
