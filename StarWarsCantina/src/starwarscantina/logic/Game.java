package starwarscantina.logic;

import starwarscantina.gui.GUI_Card;
import starwarscantina.gui.GUI;
import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import starwarscantina.utils.Image_Loader;
import starwarscantina.player.Player;
import starwarscantina.utils.Repository;

public class Game {

    private static Game instance = null;
    private final Image_Loader loader = new Image_Loader();
    private AudioClip soundClip_BgTrack = null;
    private AudioClip soundClip_Card = null;
    private GUI gui;
    private ArrayList<GUI_Card> allCards = null;
    private ArrayList<GUI_Card> playSetCards = null;
    private BufferedImage cardBackground;
    private String nickname;
    private String no_clicks;
    private GUI_Card firstOpenCard = null;
    private GUI_Card secondOpenCard = null;
    private int cardsOpen = 0;
    private int pairsFound = 0;
    private AudioClip soundClip_EndTrack = null;
    private AudioClip soundClip_ByeTrack = null;

    protected Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public AudioClip loadSound(String name) {
        AudioClip clip = Applet.newAudioClip(getClass().getResource(name));
        return clip;
    }

    public void startNewGame() {

        soundClip_BgTrack = loadSound(Repository.GAME_BGSOUNDTRACK);
        soundClip_BgTrack.loop();

        gui = GUI.getInstance();

        createAllCards();
        playSetCards = createPlaySet();
        shuffleCards(playSetCards);

        gui.createBgPanel();
        gui.createGamePanel(playSetCards);
        gui.mainMenuPanel();
        gui.createStatusPanel();
        gui.showGUI();

    }

    public void startAgain() {
        gui.resetPanels();

        reset();

        for (GUI_Card c : playSetCards) {
            c.reset();
        }
        playSetCards.clear();
        playSetCards = createPlaySet();
        shuffleCards(playSetCards);
        gui.createBgPanel();
        gui.createGamePanel(playSetCards);
        gui.mainMenuPanel();
        gui.createStatusPanel();
        gui.refreshGUI();
    }

    private void createAllCards() {

        cardBackground = loader.loadImage(Repository.CARD_BACKGROUND);

        allCards = new ArrayList<GUI_Card>();

        BufferedImage swImage = null;
        HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

        HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();

        for (Map.Entry<String, String> sw : Repository.IMAGES_MAP.entrySet()) {
            swImage = loader.loadImage(sw.getValue());

            images.put(sw.getKey(), swImage);
        }

        for (Map.Entry<String, String> sound : Repository.SOUNDS_MAP.entrySet()) {
            soundClip_Card = loadSound(sound.getValue());
            if (soundClip_Card == null) {
            } else {
                sounds.put(sound.getKey(), soundClip_Card);
            }
        }

        GUI_Card swCard = null;
        for (Map.Entry<String, BufferedImage> sw : images.entrySet()) {
            if (sounds.containsKey(sw.getKey())) {
                AudioClip clip = sounds.get(sw.getKey());
                swCard = new GUI_Card(sw.getKey(), sw.getValue(), cardBackground, clip);
            } else {
                swCard = new GUI_Card(sw.getKey(), sw.getValue(), cardBackground);
            }
            allCards.add(swCard);
        }
    }

    private ArrayList<GUI_Card> createPlaySet() {
        Collections.shuffle(allCards);

        ArrayList<GUI_Card> cards = new ArrayList<GUI_Card>();
        for (int i = 0; i < Repository.MAX_PAIRS; i++) {
            cards.add(new GUI_Card(allCards.get(i).getName(), allCards.get(i).getImage(), cardBackground, allCards.get(i).getSound()));
            cards.add(new GUI_Card(allCards.get(i).getName(), allCards.get(i).getImage(), cardBackground, allCards.get(i).getSound()));
        }
        return cards;
    }

    private void shuffleCards(ArrayList<GUI_Card> cards) {
        Collections.shuffle(cards);
    }

    public static void exitApplication() {
        System.exit(0);
    }

    public void reset() {
        cardsOpen = 0;
        pairsFound = 0;
        firstOpenCard = null;
        secondOpenCard = null;

        String text = Repository.TEXT_CARD01 + pairsFound + Repository.TEXT_CARD02 + Repository.MAX_PAIRS + Repository.TEXT_CARD03;
        GUI.setTextToStatusLabel(text);
    }

    public boolean compareCards() {

        if (firstOpenCard.getName().equals(secondOpenCard.getName())) {
            firstOpenCard.setPairFound();
            secondOpenCard.setPairFound();

            AudioClip clip = firstOpenCard.getSound();

            if (clip != null) {
                clip.play();
            }

            pairsFound++;
            return true;
        } else {
            return false;
        }
    }

    private void playerData() {
        EntityManager em = Persistence.createEntityManagerFactory(Repository.TEXT_PERSISTENCE).createEntityManager();
        em.getTransaction().begin();
        Player p1 = new Player();
        p1.setPlayerNickname(nickname);
        p1.setPlayerNoclicks(no_clicks);
        em.persist(p1);
        em.getTransaction().commit();

        JOptionPane.showMessageDialog(null, Repository.TEXT_JOP02_01, Repository.TEXT_JOP02_02, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP03);

        Query query = em.createNamedQuery(Repository.TEXT_QUERY);
        List<Player> player = (List<Player>) query.getResultList();

        List<String> playerlist = new ArrayList<String>();
        for (Player plist : player) {
            playerlist.add(Repository.TEXT_LIST01 + plist.getPlayerNickname() + Repository.TEXT_LIST02 + Repository.TEXT_LIST03 + plist.getPlayerNoclicks() + Repository.TEXT_LIST04);
        }
        String HighScoreFM = playerlist.toString().replace(",", "").replace("[", " ").replace("]", "");
        JOptionPane.showMessageDialog(null, Repository.TEXT_JOP03_01 + HighScoreFM, Repository.TEXT_JOP03_02, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP04);
        soundClip_ByeTrack = loadSound(Repository.GAME_BYETRACK);
        soundClip_ByeTrack.play();
        JOptionPane.showMessageDialog(null, Repository.TEXT_JOP04_01, Repository.TEXT_JOP04_02, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP05);
    }

    public void checkGameEnd() {
        if (pairsFound == Repository.MAX_PAIRS) {

            String text = Repository.TEXT_FINISHRESULT01 + GUI_Card.getMouseClicks() + Repository.TEXT_FINISHRESULT02;
            GUI.setTextToStatusLabel(text);

            soundClip_EndTrack = loadSound(Repository.GAME_ENDTRACK);
            soundClip_EndTrack.play();
            int answer = JOptionPane.showConfirmDialog(null, text + Repository.TEXT_JOP00_01, Repository.TEXT_JOP00_02, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP01);
            switch (answer) {
                case JOptionPane.YES_OPTION:
                    nickname = JOptionPane.showInputDialog(null, Repository.TEXT_JOP01_01, Repository.TEXT_JOP01_02, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP02, null, "").toString();
                    no_clicks = Integer.toString(GUI_Card.getMouseClicks());
                    playerData();
                    break;
                case JOptionPane.NO_OPTION:
                    soundClip_ByeTrack = loadSound(Repository.GAME_BYETRACK);
                    soundClip_ByeTrack.play();
                    JOptionPane.showMessageDialog(null, Repository.TEXT_JOP04_01, Repository.TEXT_JOP04_02, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP05);
                    break;
            }
            GUI_Card.setMouseClicks(0);
        } else {
            cardsOpen = 0;
            String text = Repository.TEXT_CARD01 + pairsFound + Repository.TEXT_CARD02 + Repository.MAX_PAIRS + Repository.TEXT_CARD03;
            GUI.setTextToStatusLabel(text);
        }
    }

    public boolean showCard(GUI_Card card) {
        if ((cardsOpen == 0 || cardsOpen == 1) && !card.isOpen()) {
            cardsOpen++;
            card.setOpen(true);
            return true;
        }
        if (cardsOpen == 2) {
            if (card.isOpen()) {
                closeOpenedCards();
            } else {
                closeOpenedCards();
                cardsOpen++;
                card.setOpen(true);
                return true;
            }
        }
        return false;
    }

    public void closeOpenedCards() {

        cardsOpen = 0;
        firstOpenCard.setOpen(false);
        firstOpenCard.requestRepaint();
        secondOpenCard.setOpen(false);
        secondOpenCard.requestRepaint();
    }

    public void cardOperation(GUI_Card card) {
        if (cardsOpen == 1) {
            firstOpenCard = card;
        }
        if (cardsOpen == 2) {
            secondOpenCard = card;
            if (compareCards() == true) {
                checkGameEnd();
            }
        }
    }

    public void showAboutDialog() {

        JOptionPane.showMessageDialog(null, Repository.TEXT_ABOUT, Repository.MENU_ABOUT, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ABOUT);
    }

    public static void openHelpGuide() {
        try {
            Desktop.getDesktop().browse(new URL(Repository.URL_GUIDE).toURI());
        } catch (IOException e) {
        } catch (URISyntaxException e) {
        }
    }

    public void highScore() {
        EntityManager em = Persistence.createEntityManagerFactory(Repository.TEXT_PERSISTENCE).createEntityManager();
        Query query = em.createNamedQuery(Repository.TEXT_QUERY);
        List<Player> player = (List<Player>) query.getResultList();

        List<String> playerlist = new ArrayList<String>();
        for (Player plist : player) {
            playerlist.add(Repository.TEXT_LIST01 + plist.getPlayerNickname() + Repository.TEXT_LIST02 + Repository.TEXT_LIST03 + plist.getPlayerNoclicks() + Repository.TEXT_LIST04);
        }
        em.close();
        String HighScoreFM = playerlist.toString().replace(",", "").replace("[", " ").replace("]", "");
        JOptionPane.showMessageDialog(null, Repository.TEXT_JOP03_01 + HighScoreFM, Repository.TEXT_JOP03_02, JOptionPane.PLAIN_MESSAGE, Repository.ICON_ENDJOP04);
    }
}
