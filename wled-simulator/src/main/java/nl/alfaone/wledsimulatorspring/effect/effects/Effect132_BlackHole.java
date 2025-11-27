package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 132: Black Hole
 * Gravitational pull toward center (2D adapted for 1D).
 */
@Component
public class Effect132_BlackHole extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);
        double pull = Math.sin(adjustedTime / 100.0 - distance * 0.2);

        double brightness = Math.exp(-distance / 10.0) * ((pull + 1.0) / 2.0);
        double hue = (distance / segmentLength + adjustedTime / 3000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 132;
    }

    @Override
    public String getEffectName() {
        return "Black Hole";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
