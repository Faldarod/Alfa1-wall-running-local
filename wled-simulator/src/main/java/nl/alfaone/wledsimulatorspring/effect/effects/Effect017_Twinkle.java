package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 17: Twinkle
 * Random twinkling LEDs.
 */
@Component
public class Effect017_Twinkle extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double twinkleChance = normalizeIntensity(intensity) * 0.1;
        if (random.nextDouble() < twinkleChance) {
            return white();
        }
        return getPrimaryColor(segment);
    }

    @Override
    public int getEffectId() {
        return 17;
    }

    @Override
    public String getEffectName() {
        return "Twinkle";
    }
}
