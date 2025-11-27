package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 138: 2D Plasma Ball
 * Enhanced plasma ball for 2D matrices.
 */
@Component
public class Effect138_PlasmaBall2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double pulse = Math.sin(adjustedTime / 70.0);
        double energy = Math.exp(-distance / (8 + pulse * 4));

        double hue = (adjustedTime / 400.0 + distance / segmentLength) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), energy);
    }

    @Override
    public int getEffectId() {
        return 138;
    }

    @Override
    public String getEffectName() {
        return "2D Plasma Ball";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
