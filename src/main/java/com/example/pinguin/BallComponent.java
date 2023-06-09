package com.example.pinguin;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.geometry.Point2D;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class BallComponent extends Component {

    public PhysicsComponent physics;
    public Boolean isTouched = false;
    Random r = new Random();
    int low = -300;
    int high = 200;

    private Point2D startPos;

    public void setStartPos(double x, double y) {
        this.startPos = new Point2D(x, y);
    }

    public Boolean getTouched() {
        return isTouched;
    }

    public void setTouched(Boolean touched, int playernum) {
        isTouched = touched;
        switch(playernum){
            case(1):{
                physics.setVelocityX(150);
                physics.setVelocityY(r.nextInt(high-low) + low);
                break;
            }
            case(2):{
                physics.setVelocityX(-150);
                physics.setVelocityY(-(r.nextInt(high-low) + low));
                break;
            }
            default:break;
        }

    }

    public void resetPos(){
        physics.overwritePosition(startPos);
        isTouched=false;
        physics.setVelocityX(0);
        physics.setVelocityY(0);
        physics.setAngularVelocity(0);
    }

    public void limitVelocity() {
        // we don't want the ball to move too slow in X direction
        if (abs(physics.getVelocityX()) < 5 * 60) {
            physics.setVelocityX(signum(physics.getVelocityX()) * 5 * 60);
        }

        // we don't want the ball to move too fast in Y direction
        if (abs(physics.getVelocityY()) > 5 * 60 * 2) {
            physics.setVelocityY(signum(physics.getVelocityY()) * 5 * 60);
        }
    }
    public void checkOffscreen() {
        if (entity.getY() >= 650 || entity.getY() <= 0){
            resetPos();
        }
    }

    public void onUpdate(double tpf) {
        if(!isTouched) {
            physics.setVelocityX(0);
            physics.setVelocityY(0);
        }
        else{
            limitVelocity();
            checkOffscreen();
        }
    }

}
