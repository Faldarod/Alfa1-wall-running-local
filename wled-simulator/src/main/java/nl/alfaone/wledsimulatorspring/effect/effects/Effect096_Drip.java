package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 96: Drip
 * Water drip effect falling down.
 */
@Component
public class Effect096_Drip extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double dripPosition = (adjustedTime / 20.0) % segmentLength;
        double distance = Math.abs(ledIndex - dripPosition);

        if (distance < 1) {
            return getPrimaryColor(segment);
        } else if (distance < 3) {
            double fade = 1.0 - ((distance - 1) / 2.0);
            return scale(getPrimaryColor(segment), fade * 0.5);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 96;
    }

    @Override
    public String getEffectName() {
        return "Drip";
    }
}
