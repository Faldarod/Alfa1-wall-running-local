package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 77: Meteor
 * Meteor shower with trailing tail.
 */
@Component
public class Effect077_Meteor extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 30.0) % segmentLength);
        int distance = Math.abs(ledIndex - position);

        if (distance == 0) {
            return white();
        } else if (distance < 10) {
            double fade = 1.0 - (distance / 10.0);
            return scale(getPrimaryColor(segment), fade);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 77;
    }

    @Override
    public String getEffectName() {
        return "Meteor";
    }
}
