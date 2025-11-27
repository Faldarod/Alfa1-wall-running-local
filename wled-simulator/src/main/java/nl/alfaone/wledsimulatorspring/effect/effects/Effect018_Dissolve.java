package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 18: Dissolve
 * Random pixels dissolve and reappear.
 */
@Component
public class Effect018_Dissolve extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Use LED index and time to create pseudo-random but stable pattern
        long seed = ledIndex + (long)(adjustedTime / 200.0);
        random.setSeed(seed);

        return random.nextBoolean() ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 18;
    }

    @Override
    public String getEffectName() {
        return "Dissolve";
    }
}
