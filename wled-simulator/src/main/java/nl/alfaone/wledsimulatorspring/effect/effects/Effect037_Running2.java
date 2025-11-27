package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 37: Running 2
 * Alternative running lights with different wave pattern.
 */
@Component
public class Effect037_Running2 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave = Math.sin((ledIndex / (double)segmentLength * Math.PI * 4) - (adjustedTime / 200.0));
        double brightness = (wave + 1.0) / 2.0;

        int[] color1 = getPrimaryColor(segment);
        int[] color2 = getSecondaryColor(segment);

        return blend(color1, color2, brightness);
    }

    @Override
    public int getEffectId() {
        return 37;
    }

    @Override
    public String getEffectName() {
        return "Running 2";
    }
}
