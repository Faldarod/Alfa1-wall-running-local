package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 163: Noise Move
 * Moving noise patterns (audio reactive).
 */
@Component
public class Effect163_NoiseMove extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double noise = Math.sin(ledIndex * 0.3 + adjustedTime / 70.0) *
                      Math.cos(ledIndex * 0.2 - adjustedTime / 90.0);

        double brightness = (noise + 1.0) / 2.0;
        double hue = (noise * 0.3 + 0.5 + adjustedTime / 2000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 163;
    }

    @Override
    public String getEffectName() {
        return "Noise Move";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
