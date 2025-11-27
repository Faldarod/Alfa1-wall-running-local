package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 40: Scanner (Knight Rider)
 * Scanner effect that moves back and forth with trailing fade.
 */
@Component
public class Effect040_Scanner extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int[] baseColor = getPrimaryColor(segment);
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        double position = sineWave(adjustedTime / 1000.0, 1.0) * segmentLength;

        double distance = Math.abs(ledIndex - position);
        double tailLength = intensity / 10.0;
        double brightness = distanceFade(distance, tailLength);

        return scale(baseColor, brightness);
    }

    @Override
    public int getEffectId() {
        return 40;
    }

    @Override
    public String getEffectName() {
        return "Scanner";
    }
}
