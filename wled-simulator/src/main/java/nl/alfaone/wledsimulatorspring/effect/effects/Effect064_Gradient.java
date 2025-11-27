package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 64: Gradient
 * Smooth gradient between primary and secondary colors.
 */
@Component
public class Effect064_Gradient extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double position = (double)ledIndex / segmentLength;

        int[] primary = getPrimaryColor(segment);
        int[] secondary = getSecondaryColor(segment);

        return blend(primary, secondary, position);
    }

    @Override
    public int getEffectId() {
        return 64;
    }

    @Override
    public String getEffectName() {
        return "Gradient";
    }
}
