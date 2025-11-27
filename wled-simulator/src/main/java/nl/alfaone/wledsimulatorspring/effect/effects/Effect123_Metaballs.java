package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 123: Metaballs
 * Organic blob-like patterns.
 */
@Component
public class Effect123_Metaballs extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double sum = 0;
        for (int i = 0; i < 3; i++) {
            double ballPos = (segmentLength / 2.0) + (segmentLength / 3.0) *
                            Math.sin(adjustedTime / 150.0 + i * Math.PI * 2 / 3);
            double distance = Math.abs(ledIndex - ballPos);
            sum += 10.0 / (distance + 1.0);
        }

        double brightness = Math.min(1.0, sum / 3.0);
        double hue = (adjustedTime / 2000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 123;
    }

    @Override
    public String getEffectName() {
        return "Metaballs";
    }
}
