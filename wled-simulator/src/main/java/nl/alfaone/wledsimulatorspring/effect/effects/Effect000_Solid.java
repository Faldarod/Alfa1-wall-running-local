package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 0: Solid
 * Displays a static color across all LEDs in the segment.
 */
@Component
public class Effect000_Solid extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        return getPrimaryColor(segment);
    }

    @Override
    public int getEffectId() {
        return 0;
    }

    @Override
    public String getEffectName() {
        return "Solid";
    }
}
