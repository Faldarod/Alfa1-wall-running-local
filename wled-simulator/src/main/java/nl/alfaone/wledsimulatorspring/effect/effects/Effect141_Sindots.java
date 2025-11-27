package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 141: 2D Sindots
 * Sine wave modulated dots pattern.
 */
@Component
public class Effect141_Sindots extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double dot1 = Math.sin((ledIndex * 0.3) + (adjustedTime / 80.0));
        double dot2 = Math.sin((ledIndex * 0.5) - (adjustedTime / 120.0));

        if (dot1 > 0.8 || dot2 > 0.8) {
            double hue = (adjustedTime / 1500.0) % 1.0;
            return hsvToRgb(hue, 1.0, Math.max(dot1, dot2));
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 141;
    }

    @Override
    public String getEffectName() {
        return "2D Sindots";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
