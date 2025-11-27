package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 94: Sinelon Rainbow
 * Sine wave movement with rainbow colors.
 */
@Component
public class Effect094_SinelonRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (segmentLength / 2.0) +
                         (segmentLength / 2.0) * Math.sin(adjustedTime / 100.0);
        double distance = Math.abs(ledIndex - position);
        double brightness = distanceFade(distance, 5.0);

        double hue = (adjustedTime / 1000.0) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 94;
    }

    @Override
    public String getEffectName() {
        return "Sinelon Rainbow";
    }
}
