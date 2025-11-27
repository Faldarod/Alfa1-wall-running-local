package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 119: Rainbow Runner
 * Moving rainbow with trail.
 */
@Component
public class Effect119_RainbowRunner extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (adjustedTime / 25.0) % segmentLength;
        double distance = Math.abs(ledIndex - position);

        if (distance < 5) {
            double hue = (adjustedTime / 1000.0 + (1.0 - distance / 5.0) * 0.2) % 1.0;
            double brightness = 1.0 - (distance / 5.0);
            return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 119;
    }

    @Override
    public String getEffectName() {
        return "Rainbow Runner";
    }
}
