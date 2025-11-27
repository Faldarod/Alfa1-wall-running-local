package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 98: Percent
 * Progress bar showing percentage.
 */
@Component
public class Effect098_Percent extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double percentage = normalizeIntensity(intensity);
        int fillPosition = (int)(segmentLength * percentage);

        if (ledIndex < fillPosition) {
            return getPrimaryColor(segment);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 98;
    }

    @Override
    public String getEffectName() {
        return "Percent";
    }
}
