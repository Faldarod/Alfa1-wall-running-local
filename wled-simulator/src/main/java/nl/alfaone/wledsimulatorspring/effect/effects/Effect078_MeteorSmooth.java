package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 78: Meteor Smooth
 * Smooth meteor effect with gradual tail fade.
 */
@Component
public class Effect078_MeteorSmooth extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (adjustedTime / 30.0) % segmentLength;
        double distance = Math.abs(ledIndex - position);

        double tailLength = 5 + normalizeIntensity(intensity) * 15;
        double brightness = distanceFade(distance, tailLength);

        if (distance < 0.5) {
            return white();
        } else if (brightness > 0) {
            return scale(getPrimaryColor(segment), brightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 78;
    }

    @Override
    public String getEffectName() {
        return "Meteor Smooth";
    }
}
