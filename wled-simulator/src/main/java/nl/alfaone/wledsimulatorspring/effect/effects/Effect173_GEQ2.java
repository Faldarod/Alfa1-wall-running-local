package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 173: GEQ 2
 * Enhanced graphic equalizer (audio reactive).
 */
@Component
public class Effect173_GEQ2 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int numBands = 12;
        int barIndex = ledIndex / (segmentLength / numBands);
        double barHeight = Math.abs(Math.sin(adjustedTime / (70.0 + barIndex * 5))) * (segmentLength / numBands);

        if ((ledIndex % (segmentLength / numBands)) < barHeight) {
            double hue = (double)barIndex / numBands;
            double brightness = 1.0 - ((double)(ledIndex % (segmentLength / numBands)) / barHeight) * 0.3;
            return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 173;
    }

    @Override
    public String getEffectName() {
        return "GEQ 2";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
