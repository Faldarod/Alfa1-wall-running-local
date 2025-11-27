package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 130: DNA
 * DNA double helix pattern (2D adapted for 1D).
 */
@Component
public class Effect130_DNA extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double helix1 = Math.sin((ledIndex * 0.4) + (adjustedTime / 100.0));
        double helix2 = Math.sin((ledIndex * 0.4) + (adjustedTime / 100.0) + Math.PI);

        double brightness = Math.max(Math.abs(helix1), Math.abs(helix2));

        if (helix1 > helix2) {
            return scale(getPrimaryColor(segment), brightness);
        } else {
            return scale(getSecondaryColor(segment), brightness);
        }
    }

    @Override
    public int getEffectId() {
        return 130;
    }

    @Override
    public String getEffectName() {
        return "DNA";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
