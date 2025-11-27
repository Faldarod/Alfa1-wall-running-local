package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 65: Loading
 * Loading bar animation effect.
 */
@Component
public class Effect065_Loading extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 50.0) % segmentLength);

        if (ledIndex <= position) {
            return getPrimaryColor(segment);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 65;
    }

    @Override
    public String getEffectName() {
        return "Loading";
    }
}
