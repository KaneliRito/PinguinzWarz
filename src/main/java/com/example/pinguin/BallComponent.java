package com.example.pinguin;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;

public class BallComponent extends Component {

    private Boolean isTouched = false;

    public Boolean getTouched() {
        return isTouched;
    }

    public void setTouched(Boolean touched) {
        isTouched = touched;
    }

    public void move() {
        if(entity.getX() >=700)  bounce();
        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                .mulLocal(2);
        entity.translate(dir);
    }

    public void bounce(){
        entity.setRotation(entity.getRotation() + 90);
        System.out.println("Bounced");
    }

    public void onUpdate(double tpf) {
        if(isTouched) this.move();
    }
}
