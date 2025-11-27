package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 20: Sparkle
 * Random sparkles of white light on the primary color.
 */
@Component
public class Effect020_Sparkle extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double sparkleChance = normalizeIntensity(intensity) * 0.04; // 0-0.04
        if (random.nextDouble() < sparkleChance) {
            return white();
        }
        return black(); // Dark background so only sparkles are visible
    }

    @Override
    public int getEffectId() {
        return 20;
    }

    @Override
    public String getEffectName() {
        return "Sparkle";
    }
}
