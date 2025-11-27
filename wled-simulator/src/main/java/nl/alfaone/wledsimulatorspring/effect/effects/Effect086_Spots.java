package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 86: Spots
 * Moving colored spots.
 */
@Component
public class Effect086_Spots extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int spotSize = Math.max(1, (int)(normalizeIntensity(intensity) * 5) + 1);
        int position = (int)((adjustedTime / 30.0) % segmentLength);

        if (Math.abs(ledIndex - position) < spotSize) {
            return getPrimaryColor(segment);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 86;
    }

    @Override
    public String getEffectName() {
        return "Spots";
    }
}
