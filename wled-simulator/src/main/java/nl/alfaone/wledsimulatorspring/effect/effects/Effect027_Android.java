package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 27: Android
 * Android notification-style effect.
 */
@Component
public class Effect027_Android extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double expansion = Math.abs(Math.sin(adjustedTime / 800.0)) * center;

        double distance = Math.abs(ledIndex - center);
        return distance < expansion ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 27;
    }

    @Override
    public String getEffectName() {
        return "Android";
    }
}
