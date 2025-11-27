package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 108: Twinkle
 * Random twinkling lights.
 */
@Component
public class Effect108_Twinkle extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double twinkleChance = normalizeSpeed(speed) * normalizeIntensity(intensity) * 0.03;
        if (random.nextDouble() < twinkleChance) {
            return getPrimaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 108;
    }

    @Override
    public String getEffectName() {
        return "Twinkle";
    }
}
