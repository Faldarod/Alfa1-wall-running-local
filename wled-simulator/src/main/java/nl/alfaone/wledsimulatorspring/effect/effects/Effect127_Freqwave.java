package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 127: Freqwave
 * Frequency-based wave pattern (simulated).
 */
@Component
public class Effect127_Freqwave extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave1 = Math.sin((adjustedTime + ledIndex * 10) / 100.0);
        double wave2 = Math.sin((adjustedTime + ledIndex * 15) / 150.0);
        double brightness = ((wave1 + wave2) / 2.0 + 1.0) / 2.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 127;
    }

    @Override
    public String getEffectName() {
        return "Freqwave";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
