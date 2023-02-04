package starwarscantina.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class Repository {

    public static final String MAIN_BACKGROUND = "/images/bg_main.jpg";
    public static final String GAME_BACKGROUND = "/images/bg_game.jpg";
    public static final String CARD_BACKGROUND = "/images/bg_card.png";
    
    public static final ImageIcon MAINMENU_BG = new ImageIcon("src/images/mainmenu.png");
    public static final ImageIcon MAINMENU_NEWGAME = new ImageIcon("src/images/button_newgame.png");
    public static final ImageIcon MAINMENU_HIGHSCORE = new ImageIcon("src/images/button_highscore.png");
    public static final ImageIcon MAINMENU_GUIDE = new ImageIcon("src/images/button_guide.png");
    public static final ImageIcon MAINMENU_ABOUT = new ImageIcon("src/images/button_about.png");
    public static final ImageIcon MAINMENU_EXIT = new ImageIcon("src/images/button_exit.png");

    public static final ImageIcon ICON_ABOUT = new ImageIcon("src/images/icon_about.png");
    public static final ImageIcon ICON_ENDJOP01 = new ImageIcon("src/images/icon_endjop01.png");
    public static final ImageIcon ICON_ENDJOP02 = new ImageIcon("src/images/icon_endjop02.png");
    public static final ImageIcon ICON_ENDJOP03 = new ImageIcon("src/images/icon_endjop03.png");
    public static final ImageIcon ICON_ENDJOP04 = new ImageIcon("src/images/icon_endjop04.png");
    public static final ImageIcon ICON_ENDJOP05 = new ImageIcon("src/images/icon_endjop05.png");

    public static final String GAME_BGSOUNDTRACK = "/sounds/swcbgtrack.au";
    public static final String GAME_ENDTRACK = "/sounds/end.au";
    public static final String GAME_BYETRACK = "/sounds/bye.wav";

    public static final String URL_GUIDE = "file:///D:/LinkGroup%20Projekat/StarWarsCantina/src/guide.html";
    
    public static final String GAME_TITLE = "Star Wars Cantina - Aleksandar Jovanović";

    public static final String TEXT_FONT = "Sans serif";
    public static final String TEXT_JLABEL = "Našli ste 0/10 parova";
    public static final String TEXT_CARD01 = "Našli ste ";
    public static final String TEXT_CARD02 = "/";
    public static final String TEXT_CARD03 = " parova";

    public static final String TEXT_ABOUT = "Staw Wars Cantina\nAleksandar Jovanović © 2018\nNeprofitabilna aplikacija.\nStar Wars je vlasništvo Disney i LucasArts.";
    public static final String TEXT_FINISHRESULT01 = "Čestitamo! Našli ste sve parove!\nNapravili ste ";
    public static final String TEXT_FINISHRESULT02 = " klikova do završetka igre.";

    public static final String TEXT_PERSISTENCE = "StarWarsCantinaPU";
    public static final String TEXT_QUERY = "Player.findAll";
    public static final String TEXT_LIST01 = " 𝄆  Nadimak: ";
    public static final String TEXT_LIST02 = " ➩ ";
    public static final String TEXT_LIST03 = "Broj klikova: ";
    public static final String TEXT_LIST04 = "  𝄇\r\n";
    
    public static final String TEXT_JOP00_01 = "\nDa li želite da sačuvate vaš rezultat?";
    public static final String TEXT_JOP00_02 = "Rezultat";

    public static final String TEXT_JOP01_01 = "Unesite vaš nadimak:";
    public static final String TEXT_JOP01_02 = "Unesite potrebnu informaciju";

    public static final String TEXT_JOP02_01 = "Vaši podaci su uspešno sačuvani!";
    public static final String TEXT_JOP02_02 = "Podaci sačuvani!";

    public static final String TEXT_JOP03_01 = "Rezultati: \n";
    public static final String TEXT_JOP03_02 = "Rezultati";

    public static final String TEXT_JOP04_01 = "Hvala što ste igrali Star Wars Cantina!";
    public static final String TEXT_JOP04_02 = "HVALA!";

    public static final String MENU_FILE = "Fajl";
    public static final String MENU_NEW_GAME = "Nova igra";
    public static final String MENU_QUIT = "Izađi";
    public static final String MENU_HELP = "Pomoć";
    public static final String MENU_GUIDE = "Vodič";
    public static final String MENU_ABOUT = "O aplikaciji";
    public static final String MENU_HIGHSCORE = "Rezultati";

    public static final String QUIT_ACTION = "Quit";
    public static final String NEWGAME_ACTION = "New Game";
    public static final String ABOUT_ACTION = "About";
    public static final String GUIDE_ACTION = "Guide";
    public static final String HIGHSCORE_ACTION = "Rezultati";
    

    public static final int MAX_PAIRS = 10;

    public static final int DEFAULT_VERTICAL_RESOLUTION = 1080;

    public static final int RES_USE_DEFAULT_IMAGES = 3;

    public static final int START_X = 5;
    public static final int START_Y = 5;

    public static final int DEFAULT_X_Y_INCREMENT = 205;
    public static final int DEFAULT_CARD_SIZE = 200;
    
    private static final String SW1 = "sw1", SW2 = "sw2", SW3 = "sw3", SW4 = "sw04", SW5 = "sw05", SW6 = "sw06", SW7 = "sw07", SW8 = "sw08", SW9 = "sw09", SW10 = "sw10", SW11 = "sw11",
                                SW12 = "sw12", SW13 = "sw13", SW14 = "sw14", SW15 = "sw15", SW16 = "sw16", SW17 = "sw17", SW18 = "sw18", SW19 = "sw19", SW20 = "sw20", SW21 = "sw21",
                                SW22 = "sw22", SW23 = "sw23", SW24 = "sw24", SW25 = "sw25";
    
    public static final Map<String, String> IMAGES_MAP = createImagesMap();

    private static Map<String, String> createImagesMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put(SW1, "/images/sw01.jpg");
        result.put(SW2, "/images/sw02.jpg");
        result.put(SW3, "/images/sw03.jpg");
        result.put(SW4, "/images/sw04.jpg");
        result.put(SW5, "/images/sw05.jpg");
        result.put(SW6, "/images/sw06.jpg");
        result.put(SW7, "/images/sw07.jpg");
        result.put(SW8, "/images/sw08.jpg");
        result.put(SW9, "/images/sw09.jpg");
        result.put(SW10, "/images/sw10.jpg");
        result.put(SW11, "/images/sw11.jpg");
        result.put(SW12, "/images/sw12.jpg");
        result.put(SW13, "/images/sw13.jpg");
        result.put(SW14, "/images/sw14.jpg");
        result.put(SW15, "/images/sw15.jpg");
        result.put(SW16, "/images/sw16.jpg");
        result.put(SW17, "/images/sw17.jpg");
        result.put(SW18, "/images/sw18.jpg");
        result.put(SW19, "/images/sw19.jpg");
        result.put(SW20, "/images/sw20.jpg");
        result.put(SW21, "/images/sw21.jpg");
        result.put(SW22, "/images/sw22.jpg");
        result.put(SW23, "/images/sw23.jpg");
        result.put(SW24, "/images/sw24.jpg");
        result.put(SW25, "/images/sw25.jpg");
        return Collections.unmodifiableMap(result);
    }

    public static final Map<String, String> SOUNDS_MAP = createSoundsMap();

    private static Map<String, String> createSoundsMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put(SW1, "/sounds/sw01.wav");
        result.put(SW2, "/sounds/sw02.wav");
        result.put(SW3, "/sounds/sw03.wav");
        result.put(SW4, "/sounds/sw04.wav");
        result.put(SW5, "/sounds/sw05.wav");
        result.put(SW6, "/sounds/sw06.wav");
        result.put(SW7, "/sounds/sw07.wav");
        result.put(SW8, "/sounds/sw08.wav");
        result.put(SW9, "/sounds/sw09.wav");
        result.put(SW10, "/sounds/sw10.wav");
        result.put(SW11, "/sounds/sw11.wav");
        result.put(SW12, "/sounds/sw12.wav");
        result.put(SW13, "/sounds/sw13.wav");
        result.put(SW14, "/sounds/sw14.wav");
        result.put(SW15, "/sounds/sw15.wav");
        result.put(SW16, "/sounds/sw16.wav");
        result.put(SW17, "/sounds/sw17.wav");
        result.put(SW18, "/sounds/sw18.wav");
        result.put(SW19, "/sounds/sw19.wav");
        result.put(SW20, "/sounds/sw20.wav");
        result.put(SW21, "/sounds/sw21.wav");
        result.put(SW22, "/sounds/sw22.wav");
        result.put(SW23, "/sounds/sw23.wav");
        result.put(SW24, "/sounds/sw24.wav");
        result.put(SW25, "/sounds/sw25.wav");
        return Collections.unmodifiableMap(result);
    }
}
