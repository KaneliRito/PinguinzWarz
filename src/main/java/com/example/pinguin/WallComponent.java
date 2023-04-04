package com.example.pinguin;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class WallComponent extends Component {
    private PhysicsComponent physics;
    private Boolean isChanged = false;

    public Boolean getChanged() {
        return isChanged;
    }

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

    private void wallMovement(double speed){
        if(isChanged) physics.setVelocityY(speed);
        else physics.setVelocityY(-speed);
    }

    public void onUpdate(double tpf){
        wallMovement(40);
    }
}
