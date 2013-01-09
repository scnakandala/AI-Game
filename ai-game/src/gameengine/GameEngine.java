package gameengine;

import communicators.MessageParser;
import java.util.Observable;
import java.util.Observer;
import models.MapObject;
import models.Map;
import models.Player;

public class GameEngine extends Observable implements Observer{

    public static final int NO_ROWS=10,NO_COLUMNS =10;

    public static GameEngine gameEngineAPI = null;
    private MessageParser messageParser;
    private MapController mapController;
    private Map map;

    private GameEngine() {
        this.messageParser = MessageParser.getInstance();
        this.mapController = new MapController();
        this.map = Map.getInstance();
        map.addObserver(this);
    }

    public static GameEngine getInstance() {
        if (GameEngine.gameEngineAPI == null) {
            GameEngine.gameEngineAPI = new GameEngine();
        }
        return GameEngine.gameEngineAPI;
    }

    public void join() {
        messageParser.join();
    }

    public void moveUp() {
        messageParser.up();
    }

    public void moveDown() {
        messageParser.down();
    }

    public void moveLeft() {
       messageParser.left();
    }

    public void moveRight() {
        messageParser.right();
    }

    public void shoot() {
        messageParser.shoot();
    }

    public MapObject[][] getMap(){
        return map.getMap();
    }

    public Player[] getPlayers(){
        return map.getPlayers();
    }

    public Player getThisPlayer(){
        return map.thisPlayer();
    }

    public void update(Observable o, Object arg) {
        if(o instanceof Map){
            setChanged();
            notifyObservers(arg);
        }
    }

}
