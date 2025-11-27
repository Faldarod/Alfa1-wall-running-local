package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 150: Jelly
 * Jelly-like wobbling effect.
 */
@Component
public class Effect150_Jelly extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wobble1 = Math.sin((ledIndex * 0.3) + (adjustedTime / 90.0));
        double wobble2 = Math.sin((ledIndex * 0.2) - (adjustedTime / 110.0));
        double wobble3 = Math.sin(adjustedTime / 70.0);

        double brightness = ((wobble1 + wobble2) / 2.0 * wobble3 + 1.0) / 2.0;
        double hue = 0.55 + 0.1 * wobble1; // Blue-green jelly colors

        return scale(hsvToRgb(hue, 0.7, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 150;
    }

    @Override
    public String getEffectName() {
        return "Jelly";
    }
}
