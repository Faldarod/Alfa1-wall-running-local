package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 2: Breathe
 * Smoothly fades brightness in and out like breathing.
 */
@Component
public class Effect002_Breathe extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed) / 1000.0;

        double brightness = (Math.sin(adjustedTime) + 1.0) / 2.0; // 0-1
        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 2;
    }

    @Override
    public String getEffectName() {
        return "Breathe";
    }
}
