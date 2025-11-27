package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 160: Gravfreq
 * Gravity with frequency response (audio reactive).
 */
@Component
public class Effect160_Gravfreq extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double freq = Math.abs(Math.sin(adjustedTime / 100.0));
        int fillHeight = (int)(segmentLength * freq);

        if (ledIndex < fillHeight) {
            double hue = (double)ledIndex / fillHeight * 0.4;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 160;
    }

    @Override
    public String getEffectName() {
        return "Gravfreq";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
