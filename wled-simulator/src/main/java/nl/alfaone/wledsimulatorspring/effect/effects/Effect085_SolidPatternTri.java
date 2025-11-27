package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 85: Solid Pattern Tri
 * Three-color solid pattern.
 */
@Component
public class Effect085_SolidPatternTri extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int patternSize = Math.max(1, (int)(normalizeIntensity(intensity) * 10) + 1);

        int colorIndex = (ledIndex / patternSize) % 3;
        switch (colorIndex) {
            case 0: return getPrimaryColor(segment);
            case 1: return getSecondaryColor(segment);
            case 2: return getTertiaryColor(segment);
            default: return black();
        }
    }

    @Override
    public int getEffectId() {
        return 85;
    }

    @Override
    public String getEffectName() {
        return "Solid Pattern Tri";
    }
}
