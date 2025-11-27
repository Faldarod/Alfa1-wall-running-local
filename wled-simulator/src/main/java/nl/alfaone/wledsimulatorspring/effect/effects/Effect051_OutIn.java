package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 51: Out In
 * Lights move from center to edges, then back.
 */
@Component
public class Effect051_OutIn extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int center = segmentLength / 2;
        int maxDistance = center;
        int position = (int)((adjustedTime / 100.0) % (maxDistance * 2));

        int expansion = position < maxDistance ? position : (maxDistance * 2 - position);
        int distanceFromCenter = Math.abs(ledIndex - center);

        return distanceFromCenter <= expansion ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 51;
    }

    @Override
    public String getEffectName() {
        return "Out In";
    }
}
