package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 140: 2D Pulser
 * Pulsing center point expanding outward.
 */
@Component
public class Effect140_Pulser extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);
        double pulseRadius = (adjustedTime / 50.0) % (segmentLength / 2.0);

        if (Math.abs(distance - pulseRadius) < 2) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 140;
    }

    @Override
    public String getEffectName() {
        return "2D Pulser";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
