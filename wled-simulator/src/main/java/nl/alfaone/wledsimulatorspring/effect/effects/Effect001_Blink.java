package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 1: Blink
 * Blinks the primary color on and off at a rate controlled by speed.
 */
@Component
public class Effect001_Blink extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed) / 1000.0;

        boolean isOn = (adjustedTime % 2) < 1;
        return isOn ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 1;
    }

    @Override
    public String getEffectName() {
        return "Blink";
    }
}
