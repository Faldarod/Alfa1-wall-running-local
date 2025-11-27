package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 178: 2D Crazy Bees
 * Erratic bee-like movements.
 */
@Component
public class Effect178_CrazyBees extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double maxBrightness = 0;
        double maxHue = 0;

        for (int i = 0; i < 3; i++) {
            double beePos = (segmentLength / 2.0) +
                           (segmentLength / 3.0) * Math.sin(adjustedTime / (80.0 + i * 20)) *
                           Math.cos(adjustedTime / (100.0 + i * 15));
            double distance = Math.abs(ledIndex - beePos);
            double brightness = distanceFade(distance, 4.0);

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxHue = (0.15 + i * 0.1) % 1.0; // Yellow-ish bee colors
            }
        }

        return scale(hsvToRgb(maxHue, 1.0, 1.0), maxBrightness);
    }

    @Override
    public int getEffectId() {
        return 178;
    }

    @Override
    public String getEffectName() {
        return "2D Crazy Bees";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
