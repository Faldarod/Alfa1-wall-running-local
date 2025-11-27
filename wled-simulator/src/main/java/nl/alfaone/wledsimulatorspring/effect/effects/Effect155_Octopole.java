package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 155: Octopole
 * Eight-armed pattern.
 */
@Component
public class Effect155_Octopole extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double angle = (double)ledIndex / segmentLength * 8 * Math.PI + adjustedTime / 100.0;
        double arms = Math.sin(angle) * Math.exp(-distance / 10.0);

        if (arms > 0.3) {
            double hue = (adjustedTime / 2000.0 + (double)ledIndex / segmentLength) % 1.0;
            return scale(hsvToRgb(hue, 1.0, 1.0), arms);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 155;
    }

    @Override
    public String getEffectName() {
        return "Octopole";
    }
}
