package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 49: In In
 * Lights move from both edges toward center simultaneously.
 */
@Component
public class Effect049_InIn extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int center = segmentLength / 2;
        int position = (int)((adjustedTime / 100.0) % center);

        boolean fromLeft = ledIndex <= position;
        boolean fromRight = ledIndex >= (segmentLength - 1 - position);

        return (fromLeft || fromRight) ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 49;
    }

    @Override
    public String getEffectName() {
        return "In In";
    }
}
