package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 161: DJ Light
 * Dynamic DJ lighting effect (audio reactive).
 */
@Component
public class Effect161_Djlight extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double beat = Math.abs(Math.sin(adjustedTime / 90.0));
        double flash = beat > 0.8 ? 1.0 : 0.0;

        if (flash > 0) {
            double hue = (adjustedTime / 500.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 161;
    }

    @Override
    public String getEffectName() {
        return "DJ Light";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
