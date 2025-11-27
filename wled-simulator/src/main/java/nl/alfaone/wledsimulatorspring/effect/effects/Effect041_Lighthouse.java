package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 41: Lighthouse
 * Rotating lighthouse beam effect.
 */
@Component
public class Effect041_Lighthouse extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        double position = (adjustedTime / 100.0) % segmentLength;

        double distance = Math.min(
            Math.abs(ledIndex - position),
            segmentLength - Math.abs(ledIndex - position)
        );

        double beamWidth = Math.max(2, intensity / 30.0);
        double brightness = distanceFade(distance, beamWidth);

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 41;
    }

    @Override
    public String getEffectName() {
        return "Lighthouse";
    }
}
