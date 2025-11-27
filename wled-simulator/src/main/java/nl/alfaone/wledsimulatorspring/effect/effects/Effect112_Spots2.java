package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 112: Spots 2
 * Advanced spots with multiple colors.
 */
@Component
public class Effect112_Spots2 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int numSpots = Math.max(2, (int)(normalizeIntensity(intensity) * 5));
        double maxBrightness = 0;
        double maxHue = 0;

        for (int i = 0; i < numSpots; i++) {
            double position = ((adjustedTime / 30.0 + i * segmentLength / (double)numSpots) % segmentLength);
            double distance = Math.abs(ledIndex - position);
            double brightness = distanceFade(distance, 3.0);

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxHue = (i * 1.0 / numSpots);
            }
        }

        return scale(hsvToRgb(maxHue, 1.0, 1.0), maxBrightness);
    }

    @Override
    public int getEffectId() {
        return 112;
    }

    @Override
    public String getEffectName() {
        return "Spots 2";
    }
}
