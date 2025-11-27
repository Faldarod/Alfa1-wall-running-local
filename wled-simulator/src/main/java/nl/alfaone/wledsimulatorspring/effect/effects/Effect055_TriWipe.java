package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 55: Tri Wipe
 * Wipe effect with three colors.
 */
@Component
public class Effect055_TriWipe extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 50.0) % (segmentLength * 3));
        int section = position / segmentLength;

        int[] primary = getPrimaryColor(segment);
        int[] secondary = getSecondaryColor(segment);
        int[] tertiary = getTertiaryColor(segment);

        if (ledIndex <= (position % segmentLength)) {
            switch (section) {
                case 0: return primary;
                case 1: return secondary;
                case 2: return tertiary;
            }
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 55;
    }

    @Override
    public String getEffectName() {
        return "Tri Wipe";
    }
}
