package com.example.pinguin;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class BallComponent extends Component {

    private PhysicsComponent physics;
    private Boolean isTouched = false;
    Random r = new Random();
    int low = -300;
    int high = 200;


    public Boolean getTouched() {
        return isTouched;
    }

    public void setTouched(Boolean touched) {
        isTouched = touched;
        physics.setVelocityX(150);
        physics.setVelocityY(r.nextInt(high-low) + low);
    }

    private void limitVelocity() {
        // we don't want the ball to move too slow in X direction
        if (abs(physics.getVelocityX()) < 5 * 60) {
            physics.setVelocityX(signum(physics.getVelocityX()) * 5 * 60);
        }

        // we don't want the ball to move too fast in Y direction
        if (abs(physics.getVelocityY()) > 5 * 60 * 2) {
            physics.setVelocityY(signum(physics.getVelocityY()) * 5 * 60);
        }
    }
    private void checkOffscreen() {
        if (getEntity().getBoundingBoxComponent().isOutside(getGameScene().getViewport().getVisibleArea())) {
            physics.overwritePosition(new Point2D(
                    getAppWidth() / 2,
                    getAppHeight() / 2
            ));
        }
    }

    public void onUpdate(double tpf) {
        if(!isTouched) {
            physics.setVelocityX(0);
        }
        else{
            limitVelocity();
            checkOffscreen();
        }
    }

}
