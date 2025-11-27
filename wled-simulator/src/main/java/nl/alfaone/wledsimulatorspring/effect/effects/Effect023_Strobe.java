package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 23: Strobe
 * Fast strobe effect.
 */
@Component
public class Effect023_Strobe extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Fast on/off with short on duration
        double period = 500.0;
        double phase = (adjustedTime % period) / period;

        return phase < 0.1 ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 23;
    }

    @Override
    public String getEffectName() {
        return "Strobe";
    }
}
