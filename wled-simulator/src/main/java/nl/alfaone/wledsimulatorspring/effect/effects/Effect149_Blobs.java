package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 149: Blobs
 * Organic blob patterns.
 */
@Component
public class Effect149_Blobs extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double blob1Pos = (segmentLength / 2.0) +
                         (segmentLength / 3.0) * Math.sin(adjustedTime / 150.0);
        double blob2Pos = (segmentLength / 2.0) +
                         (segmentLength / 3.0) * Math.sin(adjustedTime / 200.0 + Math.PI / 2);

        double dist1 = Math.abs(ledIndex - blob1Pos);
        double dist2 = Math.abs(ledIndex - blob2Pos);

        double brightness1 = distanceFade(dist1, 8.0);
        double brightness2 = distanceFade(dist2, 8.0);

        double maxBrightness = Math.max(brightness1, brightness2);

        if (maxBrightness > 0) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return scale(hsvToRgb(hue, 1.0, 1.0), maxBrightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 149;
    }

    @Override
    public String getEffectName() {
        return "Blobs";
    }
}
