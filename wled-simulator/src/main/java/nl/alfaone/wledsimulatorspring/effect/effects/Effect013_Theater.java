package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 13: Theater
 * Theater chase effect with groups of 3 LEDs.
 */
@Component
public class Effect013_Theater extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int step = (int)(adjustedTime / 100.0) % 3;
        boolean isOn = (ledIndex % 3) == step;

        return isOn ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 13;
    }

    @Override
    public String getEffectName() {
        return "Theater";
    }
}
