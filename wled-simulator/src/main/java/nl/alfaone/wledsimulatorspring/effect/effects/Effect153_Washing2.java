package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 153: Washing Machine 2
 * Enhanced washing machine effect.
 */
@Component
public class Effect153_Washing2 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (segmentLength / 2.0) +
                         (segmentLength / 2.5) * Math.sin(adjustedTime / 130.0) *
                         Math.cos(adjustedTime / 180.0);
        double distance = Math.abs(ledIndex - position);
        double brightness = distanceFade(distance, 10.0);

        double hue = (adjustedTime / 2500.0) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 153;
    }

    @Override
    public String getEffectName() {
        return "Washing Machine 2";
    }
}
