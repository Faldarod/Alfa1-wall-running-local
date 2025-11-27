package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 137: 2D Octopus
 * Tentacle-like moving patterns.
 */
@Component
public class Effect137_Octopus extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double tentacle = Math.sin(distance * 0.5 + adjustedTime / 80.0) *
                         Math.cos(adjustedTime / 120.0);

        double brightness = ((tentacle + 1.0) / 2.0) * (1.0 - distance / (segmentLength / 2.0));
        double hue = (distance / segmentLength + adjustedTime / 2000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 137;
    }

    @Override
    public String getEffectName() {
        return "2D Octopus";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
