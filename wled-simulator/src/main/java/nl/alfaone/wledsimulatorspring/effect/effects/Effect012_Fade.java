package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 12: Fade
 * Fades smoothly between primary and secondary colors.
 */
@Component
public class Effect012_Fade extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double factor = sineWave(adjustedTime, 2000.0);

        int[] color1 = getPrimaryColor(segment);
        int[] color2 = getSecondaryColor(segment);

        return blend(color1, color2, factor);
    }

    @Override
    public int getEffectId() {
        return 12;
    }

    @Override
    public String getEffectName() {
        return "Fade";
    }
}
