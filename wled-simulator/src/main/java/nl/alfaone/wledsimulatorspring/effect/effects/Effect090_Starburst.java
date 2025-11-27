package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 90: Starburst
 * Exploding starburst from center.
 */
@Component
public class Effect090_Starburst extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);
        double burstRadius = (adjustedTime / 20.0) % segmentLength;

        if (Math.abs(distance - burstRadius) < 2) {
            return getPrimaryColor(segment);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 90;
    }

    @Override
    public String getEffectName() {
        return "Starburst";
    }
}
