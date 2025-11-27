package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 180: 2D Drift
 * Enhanced 2D drifting clouds.
 */
@Component
public class Effect180_Drift2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double drift1 = Math.sin((ledIndex * 0.04) + (adjustedTime / 250.0));
        double drift2 = Math.cos((ledIndex * 0.025) - (adjustedTime / 350.0));
        double drift3 = Math.sin((ledIndex * 0.018) + (adjustedTime / 450.0));

        double brightness = ((drift1 + drift2 + drift3) / 3.0 + 1.0) / 2.0;
        double hue = ((drift1 + drift2) / 4.0 + 0.5 + adjustedTime / 4000.0) % 1.0;

        return scale(hsvToRgb(hue, 0.7, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 180;
    }

    @Override
    public String getEffectName() {
        return "2D Drift";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
