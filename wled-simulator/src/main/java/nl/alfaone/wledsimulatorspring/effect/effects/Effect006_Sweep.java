package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 6: Sweep
 * A sweeping motion across the segment with fade.
 */
@Component
public class Effect006_Sweep extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        double position = (adjustedTime / 100.0) % segmentLength;

        double distance = Math.abs(ledIndex - position);
        double fadeDistance = intensity / 10.0;
        double brightness = distanceFade(distance, fadeDistance);

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 6;
    }

    @Override
    public String getEffectName() {
        return "Sweep";
    }
}
