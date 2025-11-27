package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 38: Red & Blue
 * Alternating red and blue lights (police/emergency style).
 */
@Component
public class Effect038_RedAndBlue extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int halfLength = segmentLength / 2;
        boolean isFirstHalf = ledIndex < halfLength;

        int state = (int)((adjustedTime / 300.0) % 2);

        if ((isFirstHalf && state == 0) || (!isFirstHalf && state == 1)) {
            return new int[]{255, 0, 0}; // Red
        } else {
            return new int[]{0, 0, 255}; // Blue
        }
    }

    @Override
    public int getEffectId() {
        return 38;
    }

    @Override
    public String getEffectName() {
        return "Red & Blue";
    }
}
