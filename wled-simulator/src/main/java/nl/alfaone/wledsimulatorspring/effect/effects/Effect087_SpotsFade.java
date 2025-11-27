package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 87: Spots Fade
 * Moving colored spots with fade trail.
 */
@Component
public class Effect087_SpotsFade extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (adjustedTime / 30.0) % segmentLength;
        double distance = Math.abs(ledIndex - position);
        double fadeSize = 3 + normalizeIntensity(intensity) * 7;
        double brightness = distanceFade(distance, fadeSize);

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 87;
    }

    @Override
    public String getEffectName() {
        return "Spots Fade";
    }
}
