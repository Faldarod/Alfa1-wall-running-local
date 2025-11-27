package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 175: 2D Game of Life
 * Conway's Game of Life (simulated for 1D).
 */
@Component
public class Effect175_GameOfLife extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Simulate cellular automaton
        int generation = (int)(adjustedTime / 200.0) % 100;
        double cellState = Math.sin(ledIndex * 0.7 + generation * 0.5);

        if (cellState > 0) {
            return getPrimaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 175;
    }

    @Override
    public String getEffectName() {
        return "2D Game of Life";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
