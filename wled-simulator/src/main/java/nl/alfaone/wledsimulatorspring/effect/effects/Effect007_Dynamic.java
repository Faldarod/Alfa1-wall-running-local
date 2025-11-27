package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 7: Dynamic
 * Dynamic color changes with smooth transitions.
 */
@Component
public class Effect007_Dynamic extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double hue = (adjustedTime / 2000.0) % 1.0;
        double saturation = 0.5 + 0.5 * Math.sin(adjustedTime / 1000.0);

        return hsvToRgb(hue, saturation, 1.0);
    }

    @Override
    public int getEffectId() {
        return 7;
    }

    @Override
    public String getEffectName() {
        return "Dynamic";
    }
}
