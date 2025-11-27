package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 177: 2D Spaceships
 * Moving spaceship patterns (Game of Life gliders).
 */
@Component
public class Effect177_Spaceships extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 30.0) % segmentLength);

        // Simulate spaceship pattern (3 LEDs in L-shape)
        if (ledIndex == position || ledIndex == position + 1 || ledIndex == position + 2) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 177;
    }

    @Override
    public String getEffectName() {
        return "2D Spaceships";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
