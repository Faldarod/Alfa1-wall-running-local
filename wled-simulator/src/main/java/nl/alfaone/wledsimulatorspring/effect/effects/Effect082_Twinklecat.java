package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 82: Twinklecat
 * Twinkling with multiple colors.
 */
@Component
public class Effect082_Twinklecat extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double phase = (ledIndex * 0.1 + adjustedTime / 120.0) % 1.0;
        double brightness = Math.pow(Math.sin(phase * Math.PI * 2), 4);

        double hue = (ledIndex * 0.05 + adjustedTime / 2000.0) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 82;
    }

    @Override
    public String getEffectName() {
        return "Twinklecat";
    }
}
