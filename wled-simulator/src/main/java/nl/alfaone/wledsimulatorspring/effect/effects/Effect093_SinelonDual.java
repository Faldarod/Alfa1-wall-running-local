package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 93: Sinelon Dual
 * Two sine wave movements.
 */
@Component
public class Effect093_SinelonDual extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position1 = (segmentLength / 2.0) +
                          (segmentLength / 2.0) * Math.sin(adjustedTime / 100.0);
        double position2 = (segmentLength / 2.0) +
                          (segmentLength / 2.0) * Math.sin(adjustedTime / 120.0 + Math.PI);

        double distance1 = Math.abs(ledIndex - position1);
        double distance2 = Math.abs(ledIndex - position2);

        double brightness1 = distanceFade(distance1, 5.0);
        double brightness2 = distanceFade(distance2, 5.0);

        if (brightness1 > brightness2) {
            return scale(getPrimaryColor(segment), brightness1);
        } else {
            return scale(getSecondaryColor(segment), brightness2);
        }
    }

    @Override
    public int getEffectId() {
        return 93;
    }

    @Override
    public String getEffectName() {
        return "Sinelon Dual";
    }
}
