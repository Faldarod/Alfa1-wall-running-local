package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 103: Flow
 * Smooth flowing color transition.
 */
@Component
public class Effect103_Flow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double positionHue = (double)ledIndex / segmentLength * 0.3;
        double timeHue = (adjustedTime / 200.0) % 1.0;
        double hue = (positionHue + timeHue) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 103;
    }

    @Override
    public String getEffectName() {
        return "Flow";
    }
}
