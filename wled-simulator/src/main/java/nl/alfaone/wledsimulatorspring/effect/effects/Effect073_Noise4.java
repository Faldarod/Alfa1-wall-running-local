package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 73: Noise 4
 * Perlin-like noise pattern variant 4.
 */
@Component
public class Effect073_Noise4 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double noise1 = Math.sin(ledIndex * 0.4 + adjustedTime / 180.0);
        double noise2 = Math.cos(ledIndex * 0.23 - adjustedTime / 220.0);
        double noise3 = Math.sin(ledIndex * 0.15 + adjustedTime / 140.0);

        double combined = (noise1 + noise2 + noise3) / 3.0;
        double brightness = (combined + 1.0) / 2.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 73;
    }

    @Override
    public String getEffectName() {
        return "Noise 4";
    }
}
