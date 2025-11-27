package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 58: ICU
 * Intensive Care Unit / Police style effect.
 */
@Component
public class Effect058_ICU extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int halfLength = segmentLength / 2;
        boolean isFirstHalf = ledIndex < halfLength;

        double flashPhase = (adjustedTime % 400) / 400.0;
        boolean flash = flashPhase < 0.3;

        if (flash) {
            return isFirstHalf ? new int[]{255, 0, 0} : new int[]{0, 0, 255};
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 58;
    }

    @Override
    public String getEffectName() {
        return "ICU";
    }
}
