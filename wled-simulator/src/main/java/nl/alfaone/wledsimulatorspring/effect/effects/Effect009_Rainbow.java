package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 9: Rainbow
 * Classic rainbow effect with colors distributed across the segment.
 */
@Component
public class Effect009_Rainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);

        // Position-based hue (0-1)
        double positionHue = (double)ledIndex / segmentLength;

        // Time-based hue shift
        double timeHue = (adjustedTime / 5000.0) % 1.0;

        // Combined hue
        double hue = (positionHue + timeHue) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 9;
    }

    @Override
    public String getEffectName() {
        return "Rainbow";
    }
}
