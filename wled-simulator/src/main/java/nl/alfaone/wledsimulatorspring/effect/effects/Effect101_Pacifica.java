package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 101: Pacifica
 * Gentle ocean wave effect.
 */
@Component
public class Effect101_Pacifica extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave1 = Math.sin((ledIndex * 0.02 + adjustedTime / 500.0));
        double wave2 = Math.sin((ledIndex * 0.03 - adjustedTime / 700.0));
        double wave3 = Math.sin((ledIndex * 0.015 + adjustedTime / 900.0));

        double combined = (wave1 + wave2 + wave3) / 3.0;
        double brightness = (combined + 1.0) / 2.0;

        // Ocean blue-green colors
        int red = (int)(0 * brightness * 255);
        int green = (int)(0.6 * brightness * 255);
        int blue = (int)(0.9 * brightness * 255);

        return new int[]{clamp(red), clamp(green), clamp(blue)};
    }

    @Override
    public int getEffectId() {
        return 101;
    }

    @Override
    public String getEffectName() {
        return "Pacifica";
    }
}
