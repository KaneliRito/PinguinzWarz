package com.example.pinguin;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class WallComponent extends Component {
    private PhysicsComponent physics;
    private Boolean isChanged = false;

    private static final double WALL_SPEED_UP = -50;
    private static final double WALL_SPEED_DOWN = 50;

    public Boolean getChanged() {
        return isChanged;
    }

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

    public void swapMovement(Entity wall, Entity wall2){
        //System.out.println("Wall" + getEntity().getY());

        if(getEntity().getY() >= (wall.getY() - 100) && !isChanged ){
            isChanged = true;
        }
        else if(getEntity().getY() <= (wall2.getY() + 100) && isChanged){
            isChanged = false;
        }
    }

    public void onUpdate(double tpf){
        if(isChanged) physics.setVelocityY(WALL_SPEED_UP);
        else physics.setVelocityY(WALL_SPEED_DOWN);

    }
}
