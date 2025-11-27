package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 63: Fire Flicker
 * Flickering fire effect with warm colors.
 */
@Component
public class Effect063_FireFlicker extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double flicker = 0.7 + 0.3 * random.nextDouble();
        double brightness = normalizeIntensity(intensity) * flicker;

        int red = (int)(255 * brightness);
        int green = (int)(100 * brightness);
        int blue = 0;

        return new int[]{clamp(red), clamp(green), clamp(blue)};
    }

    @Override
    public int getEffectId() {
        return 63;
    }

    @Override
    public String getEffectName() {
        return "Fire Flicker";
    }
}
