package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 21: Dark Sparkle
 * Random dark spots on lit background.
 */
@Component
public class Effect021_DarkSparkle extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double sparkleChance = normalizeIntensity(intensity) * 0.04;
        if (random.nextDouble() < sparkleChance) {
            return black();
        }
        return getPrimaryColor(segment);
    }

    @Override
    public int getEffectId() {
        return 21;
    }

    @Override
    public String getEffectName() {
        return "Dark Sparkle";
    }
}
