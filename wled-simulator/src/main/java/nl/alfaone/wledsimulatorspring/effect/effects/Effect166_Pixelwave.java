package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 166: Pixelwave
 * Pixel wave pattern (audio reactive).
 */
@Component
public class Effect166_Pixelwave extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave = Math.sin((adjustedTime / 50.0) - ledIndex * 0.3);

        if (wave > 0.7) {
            double hue = (adjustedTime / 1500.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 166;
    }

    @Override
    public String getEffectName() {
        return "Pixelwave";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
