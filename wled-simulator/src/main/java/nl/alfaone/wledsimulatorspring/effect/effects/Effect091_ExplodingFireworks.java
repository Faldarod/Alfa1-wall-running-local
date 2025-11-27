package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 91: Exploding Fireworks
 * Multiple fireworks exploding at random positions.
 */
@Component
public class Effect091_ExplodingFireworks extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double maxBrightness = 0;
        double maxHue = 0;

        for (int i = 0; i < 3; i++) {
            double centerOffset = (i * segmentLength / 3.0);
            double burstRadius = ((adjustedTime / 25.0 + i * 50) % 100) % (segmentLength / 3.0);
            double distance = Math.abs(ledIndex - centerOffset - burstRadius);

            if (distance < 3) {
                double brightness = 1.0 - (distance / 3.0);
                if (brightness > maxBrightness) {
                    maxBrightness = brightness;
                    maxHue = (i * 0.33 + adjustedTime / 3000.0) % 1.0;
                }
            }
        }

        return scale(hsvToRgb(maxHue, 1.0, 1.0), maxBrightness);
    }

    @Override
    public int getEffectId() {
        return 91;
    }

    @Override
    public String getEffectName() {
        return "Exploding Fireworks";
    }
}
