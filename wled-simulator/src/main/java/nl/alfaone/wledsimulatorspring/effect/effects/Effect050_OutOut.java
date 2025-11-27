package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 50: Out Out
 * Lights move from center outward to edges.
 */
@Component
public class Effect050_OutOut extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int center = segmentLength / 2;
        int expansion = (int)((adjustedTime / 100.0) % center);

        int distanceFromCenter = Math.abs(ledIndex - center);

        return distanceFromCenter <= expansion ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 50;
    }

    @Override
    public String getEffectName() {
        return "Out Out";
    }
}
