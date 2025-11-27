package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 34: Colorful
 * Multiple colors distributed across the segment with dynamic changes.
 */
@Component
public class Effect034_Colorful extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double baseHue = (ledIndex / (double)segmentLength * 0.3) + (adjustedTime / 3000.0);
        double hue = baseHue % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 34;
    }

    @Override
    public String getEffectName() {
        return "Colorful";
    }
}
