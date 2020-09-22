/**
 * Created by alonzocontreras on 12/2/17.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import java.util.*;
import java.util.Timer;

public class GameWorld extends JPanel implements Observer {
    private BufferedImage background, lazImg, cardboardBox, woodBox, stoneBox, metalBox, wallImg, buttonImg, gameover, winImg;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Walls> gameWalls;
    private ArrayList<Boxes> gameBoxes;

    private BufferedImage[] deathAnimation, lazLeft, lazRight;

    private GameClock clock;
    private Controller controller;
    private Player player;
    private Timer movementTimer;
    private TimerTask move;
    private Boxes currentBox;
    private Music musicPlayer;
    private StackX stackX;
    private boolean movedLeft = false;
    private boolean movedRight = false;

    public GameWorld() {
        init();
    }

    private void init() {
        gameObjects = new ArrayList<>();
        gameWalls = new ArrayList<>();
        gameBoxes = new ArrayList<>();
        stackX = new StackX();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initBackground();
        initImages();
        buildMap();
        createPlayer();
        musicPlayer = new Music();
        musicPlayer.playGameExp();
        setMovementTimer();
        createController();
        makeClock();
        generateBox();
    }

    public void update(Observable o, Object obj) {
        //move character
        updatePlayer(player, controller);
        //check if last box has landed first
        if (!currentBox.getIsMoving() && player.getIsVisible() && !player.getWin() && player.getX() > 79 && player.getX() < 521) {
            gameObjects.add(currentBox);
            generateBox();
        }
        checkCollision();
        repaint();
    }

    private void initBackground() {
        loadBackground();
        int w = background.getWidth(this);
        int h = background.getHeight(this);
        setPreferredSize(new Dimension(w, h));

    }

    private void createPlayer() {
        player = new Player(7 * 40, 9 * 40, lazImg, 40, 40);
        getColumn().add(player);
//        gameObjects.add(player);
    }

    private void createController() {
        controller = new Controller(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        addKeyListener(controller);
        controller.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        for (Walls walls : gameWalls) {
            g.drawImage(wallImg, walls.getX(), walls.getY(), null);
        }
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject obj = gameObjects.get(i);
            if (obj.getIsVisible()) {
                g.drawImage(obj.getImage(), obj.getX(), obj.getY(), null);
            }
        }
        if (player.getWin()) {
            g.drawImage(winImg, 0, 0, null);

        }
        for(GameObject obj : column_11){
            g.drawImage(obj.getImage(), obj.getX(), obj.getY(), null);
        }
        for(GameObject obj : column_12){
            g.drawImage(obj.getImage(), obj.getX(), obj.getY(), null);
        }

        //checks if player was crushed by the box
        //if true: kill player and play end game sound/stop main game sound
        if (!player.getIsVisible()) {
            for (int i = 0; i < 11; i++) {
                g.drawImage(deathAnimation[i], player.getX(), player.getY(), null);
            }
            g.drawImage(gameover, 0, 0, null);
            musicPlayer.playEndGameExp();
            musicPlayer.stopMainGame();
        }
        if(movedLeft){
            for(int i = 0; i < 7; i++){
                //g.drawImage(lazLeft[i], player.getX(), player.getY(), null);
            }
            movedLeft = false;
        }
        if(movedRight){
            for(int i = 0; i < 7; i++){
                //g.drawImage(lazRight[i], player.getX(), player.getY(), null);
            }
            movedRight = false;
        }
        g.dispose();
    }

    private void loadBackground() {
        //uses image url to load the png into an Image data type
        try {
            background = ImageIO.read(new File("src/resources/Background.png"));
        } catch (Exception e) {
            System.out.println("Could not find background image file");
        }
    }

    public void initImages() {
        //reads image files from resources and adds them to an image array
        // lazarus, lazLeft, lazRight, lazDed, cardboardBox, woodBox, stoneBox, metalBox, wall
        try {
            winImg = ImageIO.read(new File("src/resources/victory.png"));
            gameover = ImageIO.read(new File("src/resources/gameover.png"));
            buttonImg = ImageIO.read(new File("src/resources/Button.png"));
            lazImg = ImageIO.read(new File("src/resources/idel.png"));
            wallImg = ImageIO.read(new File("src/resources/Wall.png"));
            metalBox = ImageIO.read(new File("src/resources/MetalBox.png"));
            stoneBox = ImageIO.read(new File("src/resources/StoneBox.png"));
            cardboardBox = ImageIO.read(new File("src/resources/CardBox.png"));
            woodBox = ImageIO.read(new File("src/resources/WoodBox.png"));
            lazLeft = new BufferedImage[7];
            for(int i = 0; i < 7; i++) {
                lazLeft[i] = ImageIO.read(new File("src/resources/moveLeft" + i + ".png"));
            }
            lazRight = new BufferedImage[7];
            for(int i = 0; i < 7; i++) {
                lazRight[i] = ImageIO.read(new File("src/resources/moveRight" + i + ".png"));
            }
            deathAnimation = new BufferedImage[11];
            for (int i = 0; i < 11; i++) {
                deathAnimation[i] = ImageIO.read(new File("src/resources/dead" + i + ".png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not find game resources.");
        }
    }

    private void buildMap() {
        Walls wall;
        Button btn;
        Map map = new Map();
        int[][] gameMap = map.getMap();
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 16; x++) {
                if (gameMap[y][x] == 1) {
                    wall = new Walls(x * 40, y * 40, wallImg, 40, 40);
                    gameWalls.add(wall);
                    gameObjects.add(wall);
                }
                if (gameMap[y][x] == 9) {
                    btn = new Button(x * 40, y * 40, buttonImg, 40, 40);
                    gameObjects.add(btn);
                }
            }
        }
    }

    public void updatePlayer(Player player, Controller controller) {
        Set<Integer> keyset = controller.getKeysPressed();
        if (player.getMoveCount() > 0) {
            if (keyset.contains(controller.getKeyLeft())) {
                //checks if player is not at edge of pit and if player is above a stack of boxes
                if((player.getX() > 80) && (player.getY() < stackX.getStackSizeX(player.getX()) - 40)){
                    player.mvLeft();
                    player.moveDown();  //moves player down 1 space
                }
                else {
                    player.mvLeft();
                }
                movedLeft = true;
                musicPlayer.playMoveExp();
            }
            if (keyset.contains(controller.getKeyRight())) {
                //checks if player is not at edge of pit and if player is above a stack of boxes
                if((player.getX() < 520) && (player.getY() < stackX.getStackSizeX(player.getX()))){
                    player.mvRight();
                    player.moveDown();  //moves player down 1 space
                }
                else {
                    player.mvRight();
                }
                movedRight = true;
                musicPlayer.playMoveExp();

            }
            player.setMoveCount(player.getMoveCount() - 1);
        }
    }

    public void checkCollision() {
        Rectangle playerHitBox = player.getBounds();
        Rectangle fallingBox = currentBox.getBounds();
        for(GameObject wallObj : gameObjects){
            Rectangle boxRect = wallObj.getBounds();
            if (fallingBox.intersects(boxRect) && (wallObj instanceof Walls)) {
                setColoumn();
                currentBox.collide((Walls) wallObj);
            }
        }
        for (GameObject obj : getColumn()){
            Rectangle hitbox = obj.getBounds();
            //this collision is for falling box vs wall
            if (fallingBox.intersects(hitbox) && (obj instanceof Walls)) {
                currentBox.collide((Walls) obj);
                stackX.increaseStackX(currentBox.getX());   //increases the count of boxes in that column
            }
            //checks if the box falls on the player, if so kill the player
            if (fallingBox.intersects(hitbox) && (obj instanceof Player)) {
                currentBox.collide((Player) obj);
                if (!currentBox.getIsMoving()) {
                    player.getDeathAnimation();
                    musicPlayer.playSquishedExp();
                    currentBox.setIsMoving(false);
                }
            }
            //checks if the player is running into a box
            if (playerHitBox.intersects(hitbox) && (obj.getIsSolid()) && (obj instanceof Boxes)) {
                player.collide(obj);
            }
            //checks if the player hits the button
            if (playerHitBox.intersects(hitbox) && (obj.getIsSolid()) && (obj instanceof Button)) {
                player.collide(obj);
                currentBox.setIsMoving(false);
                musicPlayer.playButtonExp();
                musicPlayer.stopMainGame();
            }
            //checks if the falling box falls on another box
            if (fallingBox.intersects(hitbox) && (obj.getIsSolid()) && (obj instanceof Boxes)) {
                currentBox.collide((Boxes) obj);
                stackX.increaseStackX(currentBox.getX());   //increases the count of boxes in that column
                ((Boxes) obj).setBoxOntop(true);    //obj has a box on top
                if (currentBox.getSoundFX()) {
                    musicPlayer.playCrushExp();
                    stackX.decreaseStackX(currentBox.getX()); //decreases the count of boxes in that column
                }
            }
        }
    }

    public void generateBox() {
        //randomBox is a number from 0 to 3
        int randomBox = (int) Math.floor(Math.random() * 4);
        switch (randomBox) {
            case 0:

                currentBox = new Boxes(player.getX(), 0, cardboardBox, 39, 39, 0);
                clock.addObserver(currentBox);
                break;
            case 1:
                currentBox = new Boxes(player.getX(), 0, woodBox, 39, 39, 1);
                clock.addObserver(currentBox);
                break;
            case 2:
                currentBox = new Boxes(player.getX(), 0, metalBox, 39, 39, 2);
                clock.addObserver(currentBox);
                break;
            case 3:
                currentBox = new Boxes(player.getX(), 0, stoneBox, 39, 39, 3);
                clock.addObserver(currentBox);
                break;
        }
    }

    private void makeClock() {
        clock = new GameClock();
        clock.addObserver(this);
        clock.addObserver(player);
    }

    public void setMovementTimer() {
        movementTimer = new Timer();
        move = new TimerTask() {
            @Override
            public void run() {
                if (player.getMoveCount() < 1) {
                    player.setMoveCount(player.getMoveCount() + 1);
                }
            }
        };
        movementTimer.schedule(move, 1, 100);
    }

}
