package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 70: Noise 1
 * Perlin-like noise pattern variant 1.
 */
@Component
public class Effect070_Noise1 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double noise = Math.sin(ledIndex * 0.5 + adjustedTime / 200.0) *
                      Math.cos(ledIndex * 0.3 - adjustedTime / 300.0);
        double hue = (noise * 0.5 + 0.5) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 70;
    }

    @Override
    public String getEffectName() {
        return "Noise 1";
    }
}
