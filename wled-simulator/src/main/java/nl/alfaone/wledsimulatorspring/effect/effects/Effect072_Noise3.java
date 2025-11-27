package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 72: Noise 3
 * Perlin-like noise pattern variant 3.
 */
@Component
public class Effect072_Noise3 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double scale = 0.1 + normalizeIntensity(intensity) * 0.5;
        double noise = Math.sin(ledIndex * scale + adjustedTime / 150.0) *
                      Math.cos(ledIndex * scale * 0.7 - adjustedTime / 200.0);

        double hue = (noise * 0.3 + 0.5 + adjustedTime / 3000.0) % 1.0;
        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 72;
    }

    @Override
    public String getEffectName() {
        return "Noise 3";
    }
}
