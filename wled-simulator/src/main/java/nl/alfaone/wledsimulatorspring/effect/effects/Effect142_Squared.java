package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 142: 2D Squared Swirl
 * Square wave spiral pattern.
 */
@Component
public class Effect142_Squared extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 30.0 + ledIndex) % segmentLength);
        int squareSize = 5;

        if ((position / squareSize) % 2 == 0) {
            double hue = (adjustedTime / 2000.0 + (double)ledIndex / segmentLength) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 142;
    }

    @Override
    public String getEffectName() {
        return "2D Squared Swirl";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
