package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 131: DNA Spiral
 * Rotating DNA spiral effect.
 */
@Component
public class Effect131_DNASpiral extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double angle = (ledIndex * 0.5) + (adjustedTime / 80.0);
        double helix1 = Math.sin(angle);
        double helix2 = Math.cos(angle);

        double brightness = (helix1 + helix2 + 2.0) / 4.0;
        double hue = (angle / (2 * Math.PI)) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 131;
    }

    @Override
    public String getEffectName() {
        return "DNA Spiral";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
