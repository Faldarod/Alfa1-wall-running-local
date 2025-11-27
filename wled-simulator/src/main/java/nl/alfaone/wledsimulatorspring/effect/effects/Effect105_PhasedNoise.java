package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 105: Phased Noise
 * Phase-shifted noise patterns.
 */
@Component
public class Effect105_PhasedNoise extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double noise1 = Math.sin(ledIndex * 0.2 + adjustedTime / 150.0);
        double noise2 = Math.cos(ledIndex * 0.13 - adjustedTime / 200.0);
        double phase = Math.sin(adjustedTime / 100.0 + ledIndex * 0.1);

        double combined = (noise1 + noise2 + phase) / 3.0;
        double brightness = (combined + 1.0) / 2.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 105;
    }

    @Override
    public String getEffectName() {
        return "Phased Noise";
    }
}
