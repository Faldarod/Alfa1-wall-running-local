package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 186: 2D Warp
 * Enhanced 2D warp speed effect.
 */
@Component
public class Effect186_Warp2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double warp = Math.exp(-distance / 4.0);
        double streaks = Math.sin(adjustedTime / 15.0 - distance * 0.6);

        if (streaks > 0.6) {
            double hue = 0.55 + 0.15 * Math.sin(adjustedTime / 500.0); // Blue-cyan streaks
            return scale(hsvToRgb(hue, 0.8, 1.0), warp);
        }

        // Dark space background with stars
        double starChance = 0.01;
        if (random.nextDouble() < starChance) {
            return scale(white(), warp * 0.3);
        }

        return scale(new int[]{0, 0, 20}, warp * 0.2); // Very dark blue background
    }

    @Override
    public int getEffectId() {
        return 186;
    }

    @Override
    public String getEffectName() {
        return "2D Warp";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
