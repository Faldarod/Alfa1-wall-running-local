package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 113: Washing Machine
 * Back and forth washing machine motion.
 */
@Component
public class Effect113_Washing extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (segmentLength / 2.0) +
                         (segmentLength / 2.0) * Math.sin(adjustedTime / 150.0);
        double distance = Math.abs(ledIndex - position);
        double brightness = distanceFade(distance, 8.0);

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 113;
    }

    @Override
    public String getEffectName() {
        return "Washing Machine";
    }
}
