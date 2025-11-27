package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 167: Juggles
 * Multiple juggling dots (audio reactive).
 */
@Component
public class Effect167_Juggles extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double maxBrightness = 0;
        double maxHue = 0;

        for (int i = 0; i < 5; i++) {
            double offset = (i * segmentLength / 5.0);
            double position = Math.abs(((adjustedTime / 25.0 + offset) % (segmentLength * 2)) - segmentLength);
            double distance = Math.abs(ledIndex - position);
            double brightness = distanceFade(distance, 3.0);

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxHue = (i * 0.2 + adjustedTime / 3000.0) % 1.0;
            }
        }

        return scale(hsvToRgb(maxHue, 1.0, 1.0), maxBrightness);
    }

    @Override
    public int getEffectId() {
        return 167;
    }

    @Override
    public String getEffectName() {
        return "Juggles";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
