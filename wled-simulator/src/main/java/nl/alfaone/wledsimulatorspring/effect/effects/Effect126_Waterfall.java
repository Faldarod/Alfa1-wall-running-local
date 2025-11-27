package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 126: Waterfall
 * Cascading waterfall effect.
 */
@Component
public class Effect126_Waterfall extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave = Math.sin((adjustedTime / 50.0) - ledIndex * 0.2);
        double brightness = (wave + 1.0) / 2.0;

        // Blue water colors
        int red = 0;
        int green = (int)(150 * brightness);
        int blue = (int)(255 * brightness);

        return new int[]{red, clamp(green), clamp(blue)};
    }

    @Override
    public int getEffectId() {
        return 126;
    }

    @Override
    public String getEffectName() {
        return "Waterfall";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
