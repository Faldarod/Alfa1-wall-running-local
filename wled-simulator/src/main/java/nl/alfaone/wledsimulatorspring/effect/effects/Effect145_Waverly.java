package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 145: 2D Waverly
 * Complex wave interference patterns.
 */
@Component
public class Effect145_Waverly extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave1 = Math.sin((ledIndex * 0.2) + (adjustedTime / 100.0));
        double wave2 = Math.sin((ledIndex * 0.15) - (adjustedTime / 150.0));
        double wave3 = Math.sin((ledIndex * 0.1) + (adjustedTime / 200.0));

        double combined = (wave1 + wave2 + wave3) / 3.0;
        double brightness = (combined + 1.0) / 2.0;
        double hue = (combined * 0.3 + 0.5 + adjustedTime / 3000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 145;
    }

    @Override
    public String getEffectName() {
        return "2D Waverly";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
