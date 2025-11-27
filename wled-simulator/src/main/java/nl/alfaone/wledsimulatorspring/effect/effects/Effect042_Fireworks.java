package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 42: Fireworks
 * Random firework bursts.
 */
@Component
public class Effect042_Fireworks extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int[] baseColor = getPrimaryColor(segment);
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double adjustedTime = getAdjustedTime(time, speed);
        double burst = Math.sin(adjustedTime / 200.0);

        if (burst > 0.95) {
            double burstChance = normalizeIntensity(intensity);
            return random.nextDouble() < burstChance ? white() : baseColor;
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 42;
    }

    @Override
    public String getEffectName() {
        return "Fireworks";
    }
}
