package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 169: Waterfall 2
 * Enhanced waterfall effect (audio reactive).
 */
@Component
public class Effect169_Waterfall2 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave = Math.sin((adjustedTime / 40.0) - ledIndex * 0.25);
        double brightness = (wave + 1.0) / 2.0;

        // Blue-green water colors
        double hue = 0.5 + 0.1 * wave;
        return scale(hsvToRgb(hue, 0.8, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 169;
    }

    @Override
    public String getEffectName() {
        return "Waterfall 2";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
