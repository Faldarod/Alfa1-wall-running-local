package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 124: Freqmap
 * Frequency visualization (simulated without audio).
 */
@Component
public class Effect124_Freqmap extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double freq = (double)ledIndex / segmentLength;
        double amplitude = Math.abs(Math.sin(adjustedTime / 100.0 + freq * 10));

        double brightness = amplitude;
        double hue = freq;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 124;
    }

    @Override
    public String getEffectName() {
        return "Freqmap";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
