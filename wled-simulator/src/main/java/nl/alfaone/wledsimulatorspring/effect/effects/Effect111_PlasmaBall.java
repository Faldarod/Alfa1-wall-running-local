package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 111: Plasma Ball
 * Plasma ball energy effect.
 */
@Component
public class Effect111_PlasmaBall extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double pulse = Math.sin(adjustedTime / 80.0);
        double energy = Math.exp(-distance / (10 + pulse * 5));

        double hue = (adjustedTime / 500.0 + distance / segmentLength) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), energy);
    }

    @Override
    public int getEffectId() {
        return 111;
    }

    @Override
    public String getEffectName() {
        return "Plasma Ball";
    }
}
