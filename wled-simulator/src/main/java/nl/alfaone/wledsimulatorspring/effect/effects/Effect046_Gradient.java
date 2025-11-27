package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 46: Gradient
 * Smooth gradient between primary and secondary colors.
 */
@Component
public class Effect046_Gradient extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double factor = ledIndex / (double)segmentLength;

        int[] color1 = getPrimaryColor(segment);
        int[] color2 = getSecondaryColor(segment);

        return blend(color1, color2, factor);
    }

    @Override
    public int getEffectId() {
        return 46;
    }

    @Override
    public String getEffectName() {
        return "Gradient";
    }
}
