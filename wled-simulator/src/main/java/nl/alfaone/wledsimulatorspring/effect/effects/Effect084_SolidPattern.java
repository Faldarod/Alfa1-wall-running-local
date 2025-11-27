package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 84: Solid Pattern
 * Alternating solid color pattern.
 */
@Component
public class Effect084_SolidPattern extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int patternSize = Math.max(1, (int)(normalizeIntensity(intensity) * 10) + 1);

        boolean showPrimary = (ledIndex / patternSize) % 2 == 0;
        return showPrimary ? getPrimaryColor(segment) : getSecondaryColor(segment);
    }

    @Override
    public int getEffectId() {
        return 84;
    }

    @Override
    public String getEffectName() {
        return "Solid Pattern";
    }
}
