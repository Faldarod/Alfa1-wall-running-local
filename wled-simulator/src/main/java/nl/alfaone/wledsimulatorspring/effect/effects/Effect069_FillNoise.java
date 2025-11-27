package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 69: Fill Noise
 * Smooth noise-based color fill.
 */
@Component
public class Effect069_FillNoise extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Pseudo-noise using sine waves
        double noise = (Math.sin(ledIndex * 0.3 + adjustedTime / 100.0) +
                       Math.sin(ledIndex * 0.17 + adjustedTime / 150.0)) / 2.0;
        double hue = (noise * 0.5 + 0.5 + adjustedTime / 5000.0) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 69;
    }

    @Override
    public String getEffectName() {
        return "Fill Noise";
    }
}
