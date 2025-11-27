package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 102: Sunrise
 * Gradual sunrise simulation.
 */
@Component
public class Effect102_Sunrise extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double sunProgress = (adjustedTime / 500.0) % 1.0;
        double positionFactor = (double)ledIndex / segmentLength;

        if (positionFactor < sunProgress) {
            double hue = 0.1 - (sunProgress * 0.1); // Orange to yellow
            return hsvToRgb(hue, 1.0, sunProgress);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 102;
    }

    @Override
    public String getEffectName() {
        return "Sunrise";
    }
}
