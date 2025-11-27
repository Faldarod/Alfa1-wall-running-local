package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 45: Fire Flicker
 * Flickering fire effect.
 */
@Component
public class Effect045_FireFlicker extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double flicker = random.nextDouble() * normalizeIntensity(intensity);
        double brightness = 0.5 + flicker * 0.5;

        int[] fireColor = new int[]{255, 100, 0}; // Orange fire
        return scale(fireColor, brightness);
    }

    @Override
    public int getEffectId() {
        return 45;
    }

    @Override
    public String getEffectName() {
        return "Fire Flicker";
    }
}
