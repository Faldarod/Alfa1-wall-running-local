package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 81: Twinklefox
 * Twinkling effect with smooth fades.
 */
@Component
public class Effect081_Twinklefox extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double twinkle = Math.sin((ledIndex * 0.7 + adjustedTime / 100.0)) *
                        Math.cos((ledIndex * 0.3 - adjustedTime / 150.0));
        double brightness = (twinkle + 1.0) / 2.0;
        brightness = Math.pow(brightness, 2.0 - normalizeIntensity(intensity));

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 81;
    }

    @Override
    public String getEffectName() {
        return "Twinklefox";
    }
}
