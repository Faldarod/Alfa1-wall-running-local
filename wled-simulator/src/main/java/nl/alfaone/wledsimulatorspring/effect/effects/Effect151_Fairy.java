package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 151: Fairy
 * Magical fairy sparkles.
 */
@Component
public class Effect151_Fairy extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double sparkleChance = normalizeSpeed(speed) * normalizeIntensity(intensity) * 0.02;
        if (random.nextDouble() < sparkleChance) {
            double hue = random.nextDouble() * 0.3 + 0.5; // Blue-purple fairy colors
            return hsvToRgb(hue, 0.8, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 151;
    }

    @Override
    public String getEffectName() {
        return "Fairy";
    }
}
