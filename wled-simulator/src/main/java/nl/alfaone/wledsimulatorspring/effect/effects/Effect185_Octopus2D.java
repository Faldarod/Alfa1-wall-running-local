package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 185: 2D Octopus
 * Enhanced 2D octopus tentacles.
 */
@Component
public class Effect185_Octopus2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double tentacle1 = Math.sin(distance * 0.4 + adjustedTime / 70.0);
        double tentacle2 = Math.cos(distance * 0.3 - adjustedTime / 90.0);
        double pulse = Math.sin(adjustedTime / 110.0);

        double brightness = ((tentacle1 + tentacle2) / 2.0 * pulse + 1.0) / 2.0 *
                           (1.0 - distance / (segmentLength / 2.0));
        double hue = (distance / segmentLength + adjustedTime / 1800.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 185;
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
