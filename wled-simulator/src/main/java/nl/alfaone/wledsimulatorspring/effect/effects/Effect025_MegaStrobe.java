package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 25: Mega Strobe
 * Very fast, intense strobe.
 */
@Component
public class Effect025_MegaStrobe extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Very fast strobing
        double period = 200.0;
        double phase = (adjustedTime % period) / period;

        return phase < 0.05 ? white() : black();
    }

    @Override
    public int getEffectId() {
        return 25;
    }

    @Override
    public String getEffectName() {
        return "Mega Strobe";
    }
}
