package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 19: Dissolve Rnd
 * Random dissolve with random colors.
 */
@Component
public class Effect019_DissolveRnd extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Use LED index and time to create pseudo-random pattern
        long seed = ledIndex + (long)(adjustedTime / 200.0);
        random.setSeed(seed);

        if (random.nextBoolean()) {
            double hue = random.nextDouble();
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 19;
    }

    @Override
    public String getEffectName() {
        return "Dissolve Rnd";
    }
}
