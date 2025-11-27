package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 143: 2D Sun Radiation
 * Radiating sun rays from center.
 */
@Component
public class Effect143_Sun extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double rays = Math.sin((adjustedTime / 50.0) + distance * 0.5);
        double brightness = Math.exp(-distance / 15.0) * ((rays + 1.0) / 2.0);

        // Sun colors (yellow-orange)
        return scale(hsvToRgb(0.12, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 143;
    }

    @Override
    public String getEffectName() {
        return "2D Sun Radiation";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
