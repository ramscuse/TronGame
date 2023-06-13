/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author james
 */
public class AIBike extends Bike {
    
    public AIBike() {
        super();
    }

    public AIBike(int x, int y, int w, int h, Color c, int s, String d, String n) {
        super(x, y, w, h, c, s, d, n);
    }

    public AIBike(int x, int y, int w, int h, Color c, int s, String d, String n, Image iv, Image ih) {
        super(x, y, w, h, c, s, d, n, iv, ih);
    }

    public void Survive(int W, int H, Bike b) {
        for (int i = 10; i > 0; i--) {
            if (getDirection().equals("UP")) {
                if (checkCollisionSelf(0, getSpeed() * -i) || checkCollisionOtherPlayer(0, getSpeed() * -i, b)
                        || getyPos() - getSpeed() * i <= 0) {
                    if (Math.random() > .5) {
                        setDirection("LEFT");
                    } else {
                        setDirection("RIGHT");
                    }
                }
            } else if (getDirection().equals("DOWN")) {
                if (checkCollisionSelf(0, getSpeed() * i) || checkCollisionOtherPlayer(0, getSpeed() * i, b)
                        || getyPos() + getHeight() + getSpeed() * i >= H) {
                    if (Math.random() > .5) {
                        setDirection("LEFT");
                    } else {
                        setDirection("RIGHT");
                    }
                }
            } else if (getDirection().equals("RIGHT")) {
                if (checkCollisionSelf(getSpeed() * i, 0) || checkCollisionOtherPlayer(getSpeed() * i, 0, b)
                        || getxPos() + getWidth() + getSpeed() * i >= W) {
                    if (Math.random() > .5) {
                        setDirection("DOWN");
                    } else {
                        setDirection("UP");
                    }
                }
            } else if (getDirection().equals("LEFT")) {
                if (checkCollisionSelf(getSpeed() * -i, 0) || checkCollisionOtherPlayer(getSpeed() * -i, 0, b)
                        || getxPos() - getSpeed() * i <= 0) {
                    if (Math.random() > .5) {
                        setDirection("DOWN");
                    } else {
                        setDirection("UP");
                    }
                }
            }
        }
    }

    public void RandomChanges() {
        if (Math.random() < .005) {
            if (getDirection().equals("UP")) {
                if (Math.random() > .5) {
                    setDirection("LEFT");
                } else {
                    setDirection("RIGHT");
                }
            } else if (getDirection().equals("DOWN")) {
                if (Math.random() > .5) {
                    setDirection("LEFT");
                } else {
                    setDirection("RIGHT");
                }

            } else if (getDirection().equals("RIGHT")) {
                if (Math.random() > .5) {
                    setDirection("DOWN");
                } else {
                    setDirection("UP");
                }

            } else if (getDirection().equals("LEFT")) {
                if (Math.random() > .5) {
                    setDirection("DOWN");
                } else {
                    setDirection("UP");
                }

            }
        }
    }
}
