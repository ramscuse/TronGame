
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author james
 */
public class Game extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static Canvas canvas;
    RedrawTimer timer = new RedrawTimer();
    private Bike P1;
    private Bike P2;
    private Bike P3;
    private Bike P4;
    private ArrayList<Bike> players;
    private ArrayList<Integer> scores;
    private ArrayList<Boolean> AI;
    private boolean[] playing = {true, true, false, false};
    private boolean[] ai = {false, true, true, true};
    private Image[] bikeImages = {new Image("RedBike.png", 100, 200, false, true), new Image("BlueBike.png", 100, 200, false, true),
        new Image("GreenBike.png", 100, 200, false, true), new Image("PurpleBike.png", 100, 200, false, true)};
    private Image[] bikeImages2 = {new Image("RedBike.png", 10, 20, false, true), new Image("BlueBike.png", 10, 20, false, true),
        new Image("GreenBike.png", 10, 20, false, true), new Image("PurpleBike.png", 10, 20, false, true)};
    private Image[] bikeImages3 = {new Image("RedBike2.png", 20, 10, false, true), new Image("BlueBike2.png", 20, 10, false, true),
        new Image("GreenBike2.png", 20, 10, false, true), new Image("PurpleBike2.png", 20, 10, false, true)};
    private Image background = new Image("grid.jpg", WIDTH, HEIGHT, false, true);
    private Image logo = new Image("logo.png", WIDTH, 300, false, true);
    private boolean paused = false;

    /**
     * @param args the command line arguments
     */
    private enum STATE {
        MAINMENU,
        GAMEMODESELECT,
        GAME,
    }

    public static STATE gameState = STATE.MAINMENU;

    public void start(Stage stage) {
        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        if (gameState == STATE.MAINMENU) {
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double mx = event.getSceneX();
                    double my = event.getSceneY();
                    if (mx > WIDTH / 2 - 200 && mx < WIDTH / 2 - 200 + 400 && my > 350 && my < 400) {
                        gameState = STATE.GAMEMODESELECT;
                        start(stage);
                    }
                    if (mx > WIDTH / 2 - 200 && mx < WIDTH / 2 - 200 + 400 && my > 410 && my < 460) {
                        stage.close();;
                    }
                }
            });
        } else if (gameState == STATE.GAMEMODESELECT) {
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double mx = event.getSceneX();
                    double my = event.getSceneY();
                    for (int i = 0; i < 4; i++) {
                        if (mx > 25 + ((675 / 4) * i) + (25 * i) + 15 && mx < (25 + ((675 / 4) * i) + (25 * i) + 15) + (675 / 4 - 30)
                                && my > 40 && my < 90) {
                            playing[i] = true;
                        }
                        if (mx > 25 + ((675 / 4) * i) + (25 * i) + 15 && mx < (25 + ((675 / 4) * i) + (25 * i) + 15) + (675 / 4 - 30)
                                && my > 95 && my < 145) {
                            playing[i] = false;
                        }
                        if (mx > 25 + ((675 / 4) * i) + (25 * i) + 15 && mx < (25 + ((675 / 4) * i) + (25 * i) + 15) + (675 / 4 - 30)
                                && my > 400 && my < 450 && i < 2) {
                            ai[i] = !ai[i];
                        }
                        if (mx > 590 && mx < 790 && my > 520 && my < 590) {
                            gameState = STATE.GAME;
                            start(stage);
                        }
                        if (mx > 590 && mx < 790 && my > 520 && my < 590) {
                            gameState = STATE.GAME;
                            start(stage);
                        }
                        if (mx > 10 && mx < 210 && my > 520 && my < 590) {
                            gameState = STATE.MAINMENU;
                            start(stage);
                        }
                    }
                }
            });
        } else if (gameState == STATE.GAME) {
            players = new ArrayList<Bike>();
            scores = new ArrayList<Integer>();
            AI = new ArrayList<Boolean>();

            P1 = new Bike(10000, 50, 10, 10, Color.RED, 0, "RIGHT", "P1");
            P2 = new Bike(10000, 50, 10, 10, Color.RED, 0, "RIGHT", "P2");
            P3 = new Bike(10000, 50, 10, 10, Color.RED, 0, "RIGHT", "P3");
            P4 = new Bike(10000, 50, 10, 10, Color.RED, 0, "RIGHT", "P4");

            if (playing[0]) {
                if (ai[0]) {
                    P1 = new AIBike(50, 50, 10, 10, Color.RED, 2, "RIGHT", "P1", bikeImages2[0], bikeImages3[0]);
                    AI.add(true);
                } else {
                    P1 = new Bike(50, 50, 10, 10, Color.RED, 2, "RIGHT", "P1", bikeImages2[0], bikeImages3[0]);
                    AI.add(false);
                }
                players.add(P1);
                scores.add(0);
            }
            if (playing[1]) {
                if (ai[1]) {
                    P2 = new AIBike(WIDTH - 50, HEIGHT - 50, 10, 10, Color.BLUE, 2, "LEFT", "P2", bikeImages2[1], bikeImages3[1]);
                    AI.add(true);
                } else {
                    P2 = new Bike(WIDTH - 50, HEIGHT - 50, 10, 10, Color.BLUE, 2, "LEFT", "P2", bikeImages2[1], bikeImages3[1]);
                    AI.add(false);
                }
                players.add(P2);
                scores.add(0);
            }
            if (playing[2]) {
                if (ai[2]) {
                    P3 = new AIBike(WIDTH - 50, 50, 10, 10, Color.GREEN, 2, "DOWN", "P3", bikeImages2[2], bikeImages3[2]);
                    AI.add(true);
                } else {
                    P3 = new Bike(WIDTH - 50, 50, 10, 10, Color.GREEN, 2, "DOWN", "P3", bikeImages2[2], bikeImages3[2]);
                    AI.add(false);
                }
                players.add(P3);
                scores.add(0);
            }
            if (playing[3]) {
                if (ai[3]) {
                    P4 = new AIBike(50, HEIGHT - 50, 10, 10, Color.PURPLE, 2, "UP", "P4", bikeImages2[3], bikeImages3[3]);
                    AI.add(true);
                } else {
                    P4 = new Bike(50, HEIGHT - 50, 10, 10, Color.PURPLE, 2, "UP", "P4", bikeImages2[3], bikeImages3[3]);
                    AI.add(false);
                }
                players.add(P4);
                scores.add(0);
            }

            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double mx = event.getSceneX();
                    double my = event.getSceneY();
                    if (paused) {
                        if (mx > WIDTH / 2 - ((675 / 4 - 30) / 2) && mx < WIDTH / 2 - ((675 / 4 - 30) / 2) + (675 / 4 - 30)
                                && my > 200 + (60 * 0) && my < 200 + (60 * 0) + 50) {
                            paused = !paused;
                        }
                        if (mx > WIDTH / 2 - ((675 / 4 - 30) / 2) && mx < WIDTH / 2 - ((675 / 4 - 30) / 2) + (675 / 4 - 30)
                                && my > 200 + (60 * 1) && my < 200 + (60 * 1) + 50) {
                            gameState = STATE.MAINMENU;
                            start(stage);
                        }
                    }
                }
            });

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        paused = !paused;
                    }
                    if (!ai[1]) {
                        if (event.getCode() == KeyCode.UP && !(P2.getDirection().equals("DOWN"))) {
                            P2.setDirection("UP");
                        }
                        if (event.getCode() == KeyCode.DOWN && !(P2.getDirection().equals("UP"))) {
                            P2.setDirection("DOWN");
                        }
                        if (event.getCode() == KeyCode.RIGHT && !(P2.getDirection().equals("LEFT"))) {
                            P2.setDirection("RIGHT");
                        }
                        if (event.getCode() == KeyCode.LEFT && !(P2.getDirection().equals("RIGHT"))) {
                            P2.setDirection("LEFT");
                        }
                    }
                    if (!ai[0]) {
                        if (event.getCode() == KeyCode.W && !(P1.getDirection().equals("DOWN"))) {
                            P1.setDirection("UP");
                        }
                        if (event.getCode() == KeyCode.S && !(P1.getDirection().equals("UP"))) {
                            P1.setDirection("DOWN");
                        }
                        if (event.getCode() == KeyCode.D && !(P1.getDirection().equals("LEFT"))) {
                            P1.setDirection("RIGHT");
                        }
                        if (event.getCode() == KeyCode.A && !(P1.getDirection().equals("RIGHT"))) {
                            P1.setDirection("LEFT");
                        }
                    }
                }
            });
        }
        stage.setTitle("TRON");
        stage.setScene(scene);
        stage.show();
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class RedrawTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            gc.setFont(new Font("Verdana", 14));
            gc.setStroke(Color.WHITE);

            if (gameState == STATE.MAINMENU) {
                gc.drawImage(logo, 0, 0);
                gc.setFill(Color.LIGHTBLUE);
                gc.setFont(new Font("Verdana", 30));
                gc.setStroke(Color.BLACK);
                gc.fillRect(WIDTH / 2 - 200, 350, 400, 50);
                gc.fillRect(WIDTH / 2 - 200, 410, 400, 50);
                gc.strokeText("START", 355, 386);
                gc.strokeText("QUIT", 365, 446);
            } else if (gameState == STATE.GAMEMODESELECT) {
                gc.setFont(new Font("Verdana", 40));
                gc.setFill(Color.GREEN);
                gc.fillRect(590, 520, 200, 70);
                gc.setFill(Color.RED);
                gc.fillRect(10, 520, 200, 70);
                gc.setFill(Color.BLACK);
                gc.fillText("START", 625, 567);
                gc.fillText("BACK", 55, 567);
                for (int i = 0; i < 4; i++) {
                    gc.setFont(new Font("Verdana", 30));
                    gc.setStroke(Color.WHITE);
                    gc.setFill(Color.DARKGRAY);
                    gc.fillRect(25 + ((675 / 4) * i) + (25 * i), 25, 675 / 4, 440);
                    gc.setFill(Color.GREY);
                    gc.fillRect(25 + ((675 / 4) * i) + (25 * i) + 15, 40, 675 / 4 - 30, 50);
                    gc.strokeText("JOIN", 25 + ((675 / 4) * i) + (25 * i) + 50, 77);
                    if (playing[i]) {
                        gc.fillRect(25 + ((675 / 4) * i) + (25 * i) + 15, 95, 675 / 4 - 30, 50);
                        gc.strokeText("REMOVE", 25 + ((675 / 4) * i) + (25 * i) + 20, 132);
                        gc.drawImage(bikeImages[i], 25 + ((675 / 4) * i) + (25 * i) + 35, 170);
                        gc.fillRect(25 + ((675 / 4) * i) + (25 * i) + 15, 400, 675 / 4 - 30, 50);
                        if (ai[i]) {
                            gc.strokeText("CPU", 25 + ((675 / 4) * i) + (25 * i) + 55, 437);
                        } else {
                            gc.strokeText("USER", 25 + ((675 / 4) * i) + (25 * i) + 45, 437);
                        }
                    }
                }
            } else if (gameState == STATE.GAME) {
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                gc.drawImage(background, 0, 0);
                gc.setFont(new Font("Verdana", 14));
                gc.setStroke(Color.WHITE);
                if (paused) {
                    for (int i = 0; i < 2; i++) {
                        gc.setFill(Color.DARKGRAY);
                        gc.fillRect(WIDTH / 2 - ((675 / 4 - 30) / 2), 200 + (60 * i), 675 / 4 - 30, 50);
                    }
                    gc.setFill(Color.BLACK);
                    gc.setFont(new Font("Verdana", 30));
                    gc.fillText("RESUME", WIDTH / 2 - ((675 / 4 - 30) / 2) + 5, 235);
                    gc.fillText("QUIT", WIDTH / 2 - ((675 / 4 - 30) / 2) + 30, 235 + 60);
                }
                for (int i = 0; i < players.size(); i++) {
                    if (!players.get(i).isDead()) {
                        if (!paused) {
                            players.get(i).tick();
                        }
                        players.get(i).render(canvas);
                        if (AI.get(i)) {
                            for (int k = 0; k < players.size(); k++) {
                                if (k != i) {
                                    ((AIBike) players.get(i)).Survive(WIDTH, HEIGHT, players.get(k));
                                }
                            }
                            ((AIBike) players.get(i)).RandomChanges();
                        }
                        if (players.get(i).checkCollisionSelf(0, 0) || players.get(i).getxPos() > WIDTH
                                || players.get(i).getxPos() < 0 || players.get(i).getyPos() > HEIGHT || players.get(i).getyPos() < 0) {
                            players.get(i).changeDead();
                        }
                        for (int k = 0; k < players.size(); k++) {
                            if (k != i && players.get(i).checkCollisionOtherPlayer(0, 0, players.get(k))) {
                                players.get(i).changeDead();
                            }
                        }
                    }
                    gc.strokeText(players.get(i).getName() + " Score:" + scores.get(i), 50, 510 + (i * 20));
                }
                for (int i = 0; i < players.size(); i++) {
                    int cnt = 0;
                    for (int k = 0; k < players.size(); k++) {
                        if (k != i && !players.get(k).isDead()) {
                            cnt++;
                        }
                    }
                    if (cnt == 0) {
                        scores.set(i, scores.get(i) + 1);
                        for (int k = 0; k < players.size(); k++) {
                            players.get(k).resetToSpawn();
                            if (players.get(k).isDead()) {
                                players.get(k).changeDead();
                            }
                        }
                    }
                }
            }
        }
    }
}
