package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 10: Scan
 * A scanning light that moves back and forth across the segment.
 */
@Component
public class Effect010_Scan extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int[] baseColor = getPrimaryColor(segment);
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int position = (int)((adjustedTime / 50.0) % segmentLength);

        double distance = Math.abs(ledIndex - position);
        double brightness = distanceFade(distance, 5.0);

        return scale(baseColor, brightness);
    }

    @Override
    public int getEffectId() {
        return 10;
    }

    @Override
    public String getEffectName() {
        return "Scan";
    }
}
