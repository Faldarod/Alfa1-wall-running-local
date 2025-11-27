package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 36: Sweep Random
 * Sweep effect with random color changes.
 */
@Component
public class Effect036_SweepRandom extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        double position = (adjustedTime / 100.0) % segmentLength;

        double distance = Math.abs(ledIndex - position);
        double fadeDistance = intensity / 10.0;
        double brightness = distanceFade(distance, fadeDistance);

        if (brightness > 0) {
            random.setSeed((long)(adjustedTime / 1000.0));
            double hue = random.nextDouble();
            return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 36;
    }

    @Override
    public String getEffectName() {
        return "Sweep Random";
    }
}
