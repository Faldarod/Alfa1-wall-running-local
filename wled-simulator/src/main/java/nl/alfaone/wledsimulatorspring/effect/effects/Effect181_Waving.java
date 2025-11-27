package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 181: 2D Waving Cell
 * Cellular waving patterns.
 */
@Component
public class Effect181_Waving extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave1 = Math.sin((ledIndex * 0.25) + (adjustedTime / 100.0));
        double wave2 = Math.sin((ledIndex * 0.18) - (adjustedTime / 140.0));
        double wave3 = Math.sin(adjustedTime / 90.0);

        double brightness = ((wave1 + wave2) / 2.0 * wave3 + 1.0) / 2.0;
        double hue = (wave1 * 0.2 + 0.5 + adjustedTime / 2500.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 181;
    }

    @Override
    public String getEffectName() {
        return "2D Waving Cell";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
