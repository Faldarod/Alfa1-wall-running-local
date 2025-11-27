package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 54: Tri Chase
 * Chase effect using three colors.
 */
@Component
public class Effect054_TriChase extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int pattern = (ledIndex + (int)(adjustedTime / 200.0)) % 3;

        int[] primary = getPrimaryColor(segment);
        int[] secondary = getSecondaryColor(segment);
        int[] tertiary = getTertiaryColor(segment);

        switch (pattern) {
            case 0: return primary;
            case 1: return secondary;
            case 2: return tertiary;
            default: return black();
        }
    }

    @Override
    public int getEffectId() {
        return 54;
    }

    @Override
    public String getEffectName() {
        return "Tri Chase";
    }
}
