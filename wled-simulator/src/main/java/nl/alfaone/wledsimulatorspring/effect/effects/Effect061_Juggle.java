package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 61: Juggle
 * Multiple colored dots bouncing back and forth.
 */
@Component
public class Effect061_Juggle extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double maxBrightness = 0;
        double maxHue = 0;

        for (int i = 0; i < 4; i++) {
            double offset = (i * segmentLength / 4.0);
            double position = Math.abs(((adjustedTime / 30.0 + offset) % (segmentLength * 2)) - segmentLength);
            double distance = Math.abs(ledIndex - position);
            double brightness = distanceFade(distance, 4.0);

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxHue = (i * 0.25) + (adjustedTime / 5000.0);
            }
        }

        return scale(hsvToRgb(maxHue % 1.0, 1.0, 1.0), maxBrightness);
    }

    @Override
    public int getEffectId() {
        return 61;
    }

    @Override
    public String getEffectName() {
        return "Juggle";
    }
}
