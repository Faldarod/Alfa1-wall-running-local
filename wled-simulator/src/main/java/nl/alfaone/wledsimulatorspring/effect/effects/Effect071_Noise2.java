package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 71: Noise 2
 * Perlin-like noise pattern variant 2.
 */
@Component
public class Effect071_Noise2 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double noise = Math.sin(ledIndex * 0.7 + adjustedTime / 250.0) +
                      Math.sin(ledIndex * 0.13 + adjustedTime / 100.0);
        double brightness = (noise + 2.0) / 4.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 71;
    }

    @Override
    public String getEffectName() {
        return "Noise 2";
    }
}
