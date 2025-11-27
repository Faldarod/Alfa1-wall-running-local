package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 57: Lightning
 * Random lightning flash effects.
 */
@Component
public class Effect057_Lightning extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double flashChance = normalizeIntensity(intensity) * 0.02;
        if (random.nextDouble() < flashChance) {
            return white();
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 57;
    }

    @Override
    public String getEffectName() {
        return "Lightning";
    }
}
