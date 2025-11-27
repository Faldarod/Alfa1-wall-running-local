package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 75: Lake
 * Calm water ripple effect.
 */
@Component
public class Effect075_Lake extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double ripple1 = Math.sin((ledIndex + adjustedTime / 100.0) * 0.5);
        double ripple2 = Math.cos((ledIndex - adjustedTime / 150.0) * 0.3);
        double brightness = (ripple1 + ripple2 + 2.0) / 4.0;

        int[] baseColor = new int[]{0, 100, 200}; // Blue water
        return scale(baseColor, brightness);
    }

    @Override
    public int getEffectId() {
        return 75;
    }

    @Override
    public String getEffectName() {
        return "Lake";
    }
}
