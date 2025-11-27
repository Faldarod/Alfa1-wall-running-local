package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 107: Solid Glitter
 * Solid color with sparkle overlay.
 */
@Component
public class Effect107_SolidGlitter extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double sparkleChance = normalizeIntensity(intensity) * 0.04;
        if (random.nextDouble() < sparkleChance) {
            return white();
        }

        return getPrimaryColor(segment);
    }

    @Override
    public int getEffectId() {
        return 107;
    }

    @Override
    public String getEffectName() {
        return "Solid Glitter";
    }
}
