/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author james
 */
public class Bike extends GameElement implements Tick {

    private int speed;
    private String direction;
    private final int lineWidth = 3;
    Trail trail;
    private Image imageVertical;
    private Image imageHorizontal;
    private boolean dead;
    private String name;
    private int spawnX;
    private int spawnY;
    private String spawnDirection;

    public Bike() {
        super();
        setSpeed(2);
        setDirection("DOWN");
        trail = new Trail();
        dead = false;
    }

    public Bike(int x, int y, int w, int h, Color c, int s, String d, String n) {
        super(x, y, w, h, c);
        setSpeed(s);
        setDirection(d);
        trail = new Trail();
        dead = false;
        setName(n);
        spawnX = x;
        spawnY = y;
        spawnDirection = d;
    }

    public Bike(int x, int y, int w, int h, Color c, int s, String d, String n, Image iv, Image ih) {
        super(x, y, w, h, c);
        setSpeed(s);
        setDirection(d);
        trail = new Trail();
        imageVertical = iv;
        imageHorizontal = ih;
        setName(n);
        dead = false;
        spawnX = x;
        spawnY = y;
        spawnDirection = d;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public void setDirection(String d) {
        direction = d;
    }

    public void setName(String n) {
        name = n;
    }

    public void setImageVertical(Image i) {
        imageVertical = i;
    }

    public void setImageHorizontal(Image i) {
        imageHorizontal = i;
    }

    public void changeDead() {
        dead = !dead;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return imageVertical;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void render(Canvas canvas) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        for (int i = 0; i < trail.getSize(); i++) {
            trail.getLine(i).render(canvas);
        }
        if (imageHorizontal == null || imageVertical == null) {
            if (!dead) {
                graphics.setFill(Color.WHITE);
                graphics.fillRect(getxPos(), getyPos(), getWidth(), getHeight());
            }
        } else {
            if (getDirection().equals("UP") || getDirection().equals("DOWN")) {
                graphics.drawImage(imageVertical, super.getxPos(), super.getyPos() - imageVertical.getHeight() / 4);
            } else if (getDirection().equals("RIGHT") || getDirection().equals("LEFT")) {
                graphics.drawImage(imageHorizontal, super.getxPos() - imageHorizontal.getWidth() / 4, super.getyPos());
            }
        }
    }

    @Override
    public void tick() {
        if (!dead) {
            if (getDirection().equals("UP")) {
                setyPos(getyPos() - getSpeed());
            } else if (getDirection().equals("DOWN")) {
                setyPos(getyPos() + getSpeed());
            } else if (getDirection().equals("RIGHT")) {
                setxPos(getxPos() + getSpeed());
            } else if (getDirection().equals("LEFT")) {
                setxPos(getxPos() - getSpeed());
            }
            trail.createNextLine(new Line(getxPos() + (getWidth() / 2 - lineWidth / 2), getyPos() + (getHeight() / 2 - lineWidth / 2), lineWidth, lineWidth, getColor()));
        }
    }

    public boolean checkCollisionSelf(int x, int y) {
        for (int i = 0; i < trail.getSize() - 5; i++) {
            Line test = trail.getLine(i);
            if (checkCollision(x, y, test)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionOtherPlayer(int x, int y, Bike b) {
        for (int i = 0; i < b.trail.getSize(); i++) {
            Line test = b.trail.getLine(i);
            if (checkCollision(x, y, test)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(int x, int y, Line thing) {
        return thing.getxPos() >= getxPos() + x && thing.getxPos() <= getxPos() + x + getWidth()
                && thing.getxPos() + thing.getWidth() >= getxPos() + x && thing.getxPos() + thing.getWidth() <= getxPos() + x + getWidth()
                && thing.getyPos() >= getyPos() + y && thing.getyPos() <= getyPos() + y + getHeight()
                && thing.getyPos() + thing.getHeight() >= getyPos() + y && thing.getyPos() + thing.getHeight() <= getyPos() + y + getHeight();
    }

    public void resetToSpawn() {
        super.setxPos(spawnX);
        super.setyPos(spawnY);
        this.setDirection(spawnDirection);
        trail = new Trail();
    }
}
