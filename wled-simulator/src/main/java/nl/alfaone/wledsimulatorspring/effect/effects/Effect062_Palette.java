package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 62: Palette
 * Smooth palette-based color transitions.
 */
@Component
public class Effect062_Palette extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double positionFactor = (double)ledIndex / segmentLength;
        double timeFactor = (adjustedTime / 100.0) % 1.0;
        double hue = (positionFactor * 0.3 + timeFactor) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 62;
    }

    @Override
    public String getEffectName() {
        return "Palette";
    }
}
