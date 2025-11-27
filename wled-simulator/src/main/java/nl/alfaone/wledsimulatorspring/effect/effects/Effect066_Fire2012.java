package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 66: Fire 2012
 * Realistic fire simulation effect.
 */
@Component
public class Effect066_Fire2012 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        // Fire rises from bottom, cooler at top
        double heightFactor = 1.0 - ((double)ledIndex / segmentLength);
        double heat = heightFactor * normalizeIntensity(intensity);

        // Add random flickering
        heat *= (0.7 + 0.3 * random.nextDouble());

        // Convert heat to fire colors
        if (heat < 0.3) {
            int red = (int)(heat * 255 / 0.3);
            return new int[]{clamp(red), 0, 0};
        } else if (heat < 0.7) {
            int green = (int)((heat - 0.3) * 255 / 0.4);
            return new int[]{255, clamp(green), 0};
        } else {
            int blue = (int)((heat - 0.7) * 255 / 0.3);
            return new int[]{255, 255, clamp(blue)};
        }
    }

    @Override
    public int getEffectId() {
        return 66;
    }

    @Override
    public String getEffectName() {
        return "Fire 2012";
    }
}
