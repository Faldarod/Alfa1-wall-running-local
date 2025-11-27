package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 22: Sparkle+
 * Enhanced sparkle with more intensity.
 */
@Component
public class Effect022_SparkPlus extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double sparkleChance = normalizeIntensity(intensity) * 0.08; // Double normal sparkle
        if (random.nextDouble() < sparkleChance) {
            return white();
        }
        return scale(getPrimaryColor(segment), 0.5); // Dimmer base
    }

    @Override
    public int getEffectId() {
        return 22;
    }

    @Override
    public String getEffectName() {
        return "Sparkle+";
    }
}
