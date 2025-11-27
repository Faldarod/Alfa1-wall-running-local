package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 56: Tri Fade
 * Fades between three colors.
 */
@Component
public class Effect056_TriFade extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double cycle = (adjustedTime / 2000.0) % 3.0;

        int[] primary = getPrimaryColor(segment);
        int[] secondary = getSecondaryColor(segment);
        int[] tertiary = getTertiaryColor(segment);

        if (cycle < 1.0) {
            return blend(primary, secondary, cycle);
        } else if (cycle < 2.0) {
            return blend(secondary, tertiary, cycle - 1.0);
        } else {
            return blend(tertiary, primary, cycle - 2.0);
        }
    }

    @Override
    public int getEffectId() {
        return 56;
    }

    @Override
    public String getEffectName() {
        return "Tri Fade";
    }
}
