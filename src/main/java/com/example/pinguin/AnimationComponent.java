package com.example.pinguin;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.util.Duration;


public class AnimationComponent extends Component {
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;

    public AnimationComponent(){

//       Image image = new Image("player.png");

        animIdle = new AnimationChannel(FXGL.image("player.png"),4, 128/4,42, Duration.seconds(1), 1,1);
        animWalk = new AnimationChannel(FXGL.image("player.png"),4, 128/4,42, Duration.seconds(1), 0,3);

        texture = new AnimatedTexture(animIdle);

        texture.loopAnimationChannel(animWalk);

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }






}
