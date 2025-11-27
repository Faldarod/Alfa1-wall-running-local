package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 114: Android
 * Android loading animation style.
 */
@Component
public class Effect114_Android extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int dotSize = 5;
        double position = ((adjustedTime / 20.0) % (segmentLength + dotSize * 2)) - dotSize;

        if (ledIndex >= position && ledIndex < position + dotSize) {
            return getPrimaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 114;
    }

    @Override
    public String getEffectName() {
        return "Android";
    }
}
