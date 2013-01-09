 package ui;

import gameengine.GameEngine;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import models.Brick;
import models.Coin;
import models.DeadPlayer;
import models.LifePack;
import models.MapObject;
import models.Player;
import models.Stone;
import models.Water;

public class GameMap extends JFrame implements Observer {
    private JPanel squaresPanel;
    private MapObject[][] map;
    private Player[] players;
    private JTable table;

    public GameMap() throws IOException {
        map = new MapObject[GameEngine.NO_ROWS][GameEngine.NO_COLUMNS];
        players = null;
        initComponents();
    }

    private void createSquares() throws IOException {
        LayoutManager layout = new GridLayout(GameEngine.NO_ROWS, GameEngine.NO_COLUMNS);
        squaresPanel = new JPanel();
        squaresPanel.setBorder(new EmptyBorder(8, 8, 4, 0));
        squaresPanel.setLayout(layout);
        for (int j = 0; j < GameEngine.NO_ROWS; j++) {
            for (int i = 0; i < GameEngine.NO_COLUMNS; i++) {
                JPanel squarePanel = getPanel(i, j);
                squaresPanel.add(squarePanel);
            }
        }
    }

    private void createTable() {
        String[] playerColours = {"Blue","Red","Green","Purple","Yello"};
        if (players == null) {
            String columnNames[] = {"ID", "Points $", "Coins $", "Life %"};
            Object rowData[][] = {{"Player ID", "Points $", "Coins $", "Life %"},
                {"P0", "0", "0", "0"},
                {"P1", "0", "0", "0"},
                {"P2", "0", "0", "0"},
                {"P3", "0", "0", "0"},
                {"P4", "0", "0", "0"}};
            table = new NonEditableTable(rowData, columnNames);
        }else{
            for(int i=0;i<5;i++){
                if(players[i]!=null){
                    Player p = players[i];
                    if(p.isOpponent()){
                        table.setValueAt("P"+i+" ["+playerColours[i]+"]", i+1, 0);
                    }else{
                        table.setValueAt("P"+i+" [User - "+playerColours[i]+"]", i+1, 0);
                    }
                    table.setValueAt(p.getPoints(), i+1, 1);
                    table.setValueAt(p.getCoins(), i+1, 2);
                    table.setValueAt(p.getHealth(), i+1, 3);
                }
            }
        }
    }

    private JPanel getPanel(int x, int y) {
        String freeSpace = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/floor.png";
        String water = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/water.png";
        String stone = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/stone.png";
        String brick = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/brick.png";
        String coinPile = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/coins.png";
        String lifePack = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/health.png";
        String p0_north = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankb1_n.png";
        String p0_east = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankb1_e.png";
        String p0_south = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankb1_s.png";
        String p0_west = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankb1_w.png";
        String p1_north = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankf1_n.png";
        String p1_east = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankf1_e.png";
        String p1_south = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankf1_s.png";
        String p1_west = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankf1_w.png";
        String p2_north = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankg1_n.png";
        String p2_east = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankg1_e.png";
        String p2_south = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankg1_s.png";
        String p2_west = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankg1_w.png";
        String p3_north = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankp1_n.png";
        String p3_east = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankp1_e.png";
        String p3_south = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankp1_s.png";
        String p3_west = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tankp1_w.png";
        String p4_north = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tanky1_n.png";
        String p4_east = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tanky1_e.png";
        String p4_south = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tanky1_s.png";
        String p4_west = "C:/Users/naka/Documents/NetBeansProjects/ai-game/src/resources/tanky1_w.png";


        ImagePanel panel;
        MapObject obj = map[x][y];
        if (obj == null) {
            panel = new ImagePanel(freeSpace);
        } else if (obj instanceof Stone) {
            panel = new ImagePanel(stone);
        } else if (obj instanceof Brick) {
            panel = new ImagePanel(brick);
        } else if (obj instanceof Water) {
            panel = new ImagePanel(water);
        } else if (obj instanceof Coin) {
            panel = new ImagePanel(coinPile);
        } else if (obj instanceof LifePack) {
            panel = new ImagePanel(lifePack);
        } else if (obj instanceof Player && !(obj instanceof DeadPlayer )) {
            Player player = (Player) obj;
            if (player.getName().equals("P0")) {
                if (player.getDirection() == 0) {
                    panel = new ImagePanel(p0_north);
                } else if (player.getDirection() == 1) {
                    panel = new ImagePanel(p0_east);
                } else if (player.getDirection() == 2) {
                    panel = new ImagePanel(p0_south);
                } else {
                    panel = new ImagePanel(p0_west);
                }
            } else if (player.getName().equals("P1")) {
                if (player.getDirection() == 0) {
                    panel = new ImagePanel(p1_north);
                } else if (player.getDirection() == 1) {
                    panel = new ImagePanel(p1_east);
                } else if (player.getDirection() == 2) {
                    panel = new ImagePanel(p1_south);
                } else {
                    panel = new ImagePanel(p1_west);
                }
            } else if (player.getName().equals("P2")) {
                if (((Player) obj).getDirection() == 0) {
                    panel = new ImagePanel(p2_north);
                } else if (player.getDirection() == 1) {
                    panel = new ImagePanel(p2_east);
                } else if (player.getDirection() == 2) {
                    panel = new ImagePanel(p2_south);
                } else {
                    panel = new ImagePanel(p2_west);
                }
            } else if (player.getName().equals("P3")) {
                if (((Player) obj).getDirection() == 0) {
                    panel = new ImagePanel(p3_north);
                } else if (player.getDirection() == 1) {
                    panel = new ImagePanel(p3_east);
                } else if (player.getDirection() == 2) {
                    panel = new ImagePanel(p3_south);
                } else {
                    panel = new ImagePanel(p3_west);
                }
            } else {
                if (player.getDirection() == 0) {
                    panel = new ImagePanel(p4_north);
                } else if (player.getDirection() == 1) {
                    panel = new ImagePanel(p4_east);
                } else if (player.getDirection() == 2) {
                    panel = new ImagePanel(p4_south);
                } else {
                    panel = new ImagePanel(p4_west);
                }
            }
        } else {
            panel = new ImagePanel(freeSpace);
        }

        return panel;
    }

    private void initComponents() throws IOException {

        /*the cell width is 50 pix*/
        int boardSize = (int) ((int) 50 * GameEngine.NO_ROWS * 1.1);

        createSquares();
        createTable();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GameMap");
        getContentPane().add(squaresPanel, BorderLayout.CENTER);
        getContentPane().add(table,BorderLayout.AFTER_LAST_LINE);
        setLocationByPlatform(true);
        setVisible(true);
        Dimension preferredSize = new Dimension();
        preferredSize.width = boardSize;
        /*hard coded the value to match the scenario*/
        preferredSize.height = boardSize+100;
        setPreferredSize(preferredSize);
        setResizable(false);
        pack();
    }

    public void update(Observable o, Object arg) {
        if (o instanceof GameEngine) {
            map = GameEngine.getInstance().getMap();
            players = GameEngine.getInstance().getPlayers();
            try {
                getContentPane().remove(squaresPanel);
                createSquares();
                createTable();
                getContentPane().add(squaresPanel, BorderLayout.CENTER);
                setVisible(true);
                repaint();
            } catch (IOException ex) {
                Logger.getLogger(GameMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    GameMap map = new GameMap();
                } catch (IOException ex) {
                    Logger.getLogger(GameMap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
