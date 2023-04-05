package com.example.pinguin;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class WallComponent extends Component {
<<<<<<< HEAD
    private PhysicsComponent physics;
    private Boolean isChanged = false;
=======
    public PhysicsComponent physics;
    public Boolean isChanged = false;
>>>>>>> 05af9c2535e2210609b374db88a7bf60037e453c

    public Boolean getChanged() {
        return isChanged;
    }

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

<<<<<<< HEAD
    private void wallMovement(double speed){
=======
    public void wallMovement(double speed){
>>>>>>> 05af9c2535e2210609b374db88a7bf60037e453c
        if(isChanged) physics.setVelocityY(speed);
        else physics.setVelocityY(-speed);
    }

    public void onUpdate(double tpf){
<<<<<<< HEAD
        wallMovement(40);
=======
        wallMovement(10);
>>>>>>> 05af9c2535e2210609b374db88a7bf60037e453c
    }
}
