package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 162: Midnoise
 * Mid-frequency noise pattern (audio reactive).
 */
@Component
public class Effect162_Midnoise extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double noise = Math.sin(ledIndex * 0.5 + adjustedTime / 80.0) *
                      Math.cos(adjustedTime / 100.0);

        double brightness = (noise + 1.0) / 2.0;
        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 162;
    }

    @Override
    public String getEffectName() {
        return "Midnoise";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
