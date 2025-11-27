package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 183: 2D Swirl
 * Swirling vortex pattern.
 */
@Component
public class Effect183_Swirl2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double angle = (double)ledIndex / segmentLength * 4 * Math.PI + adjustedTime / 80.0;
        double swirl = Math.sin(angle - distance * 0.3);

        double brightness = Math.exp(-distance / 15.0) * ((swirl + 1.0) / 2.0);
        double hue = (angle / (2 * Math.PI) + adjustedTime / 2000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 183;
    }

    @Override
    public String getEffectName() {
        return "2D Swirl";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
