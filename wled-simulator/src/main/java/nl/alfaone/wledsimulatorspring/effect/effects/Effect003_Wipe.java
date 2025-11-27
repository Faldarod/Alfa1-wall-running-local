package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 3: Wipe
 * Wipes primary color across the segment progressively.
 */
@Component
public class Effect003_Wipe extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int position = (int)((adjustedTime / 50.0) % segmentLength);

        return ledIndex <= position ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 3;
    }

    @Override
    public String getEffectName() {
        return "Wipe";
    }
}
