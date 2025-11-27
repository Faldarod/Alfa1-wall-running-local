package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 74: Colortwinkles
 * Random colored twinkling stars.
 */
@Component
public class Effect074_Colortwinkles extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double twinkleChance = normalizeIntensity(intensity) * 0.05;
        if (random.nextDouble() < twinkleChance) {
            double hue = random.nextDouble();
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return scale(getPrimaryColor(segment), 0.1);
    }

    @Override
    public int getEffectId() {
        return 74;
    }

    @Override
    public String getEffectName() {
        return "Colortwinkles";
    }
}
