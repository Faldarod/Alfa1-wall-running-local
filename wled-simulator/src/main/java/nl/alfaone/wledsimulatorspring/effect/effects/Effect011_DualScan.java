package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 11: Dual Scan
 * Two scanning lights moving in opposite directions.
 */
@Component
public class Effect011_DualScan extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int[] primaryColor = getPrimaryColor(segment);
        int[] secondaryColor = getSecondaryColor(segment);
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);

        // Two scanners moving in opposite directions
        int position1 = (int)((adjustedTime / 50.0) % segmentLength); // Left to right
        int position2 = segmentLength - 1 - (int)((adjustedTime / 50.0) % segmentLength); // Right to left

        double distance1 = Math.abs(ledIndex - position1);
        double distance2 = Math.abs(ledIndex - position2);

        double brightness1 = distanceFade(distance1, 3.0);
        double brightness2 = distanceFade(distance2, 3.0);

        int[] color1 = scale(primaryColor, brightness1);
        int[] color2 = scale(secondaryColor, brightness2);

        return new int[]{
            clamp(color1[0] + color2[0]),
            clamp(color1[1] + color2[1]),
            clamp(color1[2] + color2[2])
        };
    }

    @Override
    public int getEffectId() {
        return 11;
    }

    @Override
    public String getEffectName() {
        return "Dual Scan";
    }
}
