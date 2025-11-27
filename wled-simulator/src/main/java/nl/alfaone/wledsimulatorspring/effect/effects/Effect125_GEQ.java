package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 125: GEQ
 * Graphic equalizer effect (simulated).
 */
@Component
public class Effect125_GEQ extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int barIndex = ledIndex / (segmentLength / 10);
        double barHeight = Math.abs(Math.sin(adjustedTime / 80.0 + barIndex * 0.5)) * segmentLength / 10;

        if ((ledIndex % (segmentLength / 10)) < barHeight) {
            double hue = (double)barIndex / 10.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 125;
    }

    @Override
    public String getEffectName() {
        return "GEQ";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
