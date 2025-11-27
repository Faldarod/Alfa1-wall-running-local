package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 121: Julia
 * Julia set fractal-inspired pattern.
 */
@Component
public class Effect121_Julia extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double x = (double)ledIndex / segmentLength * 4.0 - 2.0;
        double c = Math.sin(adjustedTime / 500.0) * 0.7;

        double zr = x;
        double zi = 0;
        int iterations = 0;
        int maxIterations = 20;

        while (iterations < maxIterations && zr * zr + zi * zi < 4.0) {
            double temp = zr * zr - zi * zi + c;
            zi = 2.0 * zr * zi;
            zr = temp;
            iterations++;
        }

        double brightness = (double)iterations / maxIterations;
        double hue = (brightness + adjustedTime / 3000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 121;
    }

    @Override
    public String getEffectName() {
        return "Julia";
    }
}
