package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 59: Multi Comet
 * Multiple comet effects moving across the segment.
 */
@Component
public class Effect059_MultiComet extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int numComets = Math.max(2, intensity / 50);

        double maxBrightness = 0;
        for (int i = 0; i < numComets; i++) {
            double offset = (segmentLength / (double)numComets) * i;
            double position = ((adjustedTime / 80.0) + offset) % segmentLength;
            double distance = Math.abs(ledIndex - position);
            double brightness = distanceFade(distance, 5.0);
            maxBrightness = Math.max(maxBrightness, brightness);
        }

        return scale(getPrimaryColor(segment), maxBrightness);
    }

    @Override
    public int getEffectId() {
        return 59;
    }

    @Override
    public String getEffectName() {
        return "Multi Comet";
    }
}
