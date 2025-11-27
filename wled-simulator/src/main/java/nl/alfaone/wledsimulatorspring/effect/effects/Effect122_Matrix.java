package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 122: Matrix
 * Matrix-style falling code effect adapted for 1D.
 */
@Component
public class Effect122_Matrix extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double dropChance = 0.02;
        if (random.nextDouble() < dropChance) {
            return new int[]{0, 255, 0}; // Green matrix color
        }

        double fadePhase = Math.sin(adjustedTime / 50.0 + ledIndex * 0.5);
        if (fadePhase > 0.95) {
            return new int[]{0, 100, 0}; // Dim green
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 122;
    }

    @Override
    public String getEffectName() {
        return "Matrix";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
