package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 76: Glitter
 * Base color with random white sparkles.
 */
@Component
public class Effect076_Glitter extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double glitterChance = normalizeIntensity(intensity) * 0.03;
        if (random.nextDouble() < glitterChance) {
            return white();
        }

        return getPrimaryColor(segment);
    }

    @Override
    public int getEffectId() {
        return 76;
    }

    @Override
    public String getEffectName() {
        return "Glitter";
    }
}
