package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 35: Traffic Light
 * Simulates traffic light sequence: red, yellow, green.
 */
@Component
public class Effect035_TrafficLight extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int state = (int)((adjustedTime / 2000.0) % 3);

        switch (state) {
            case 0: return new int[]{255, 0, 0};    // Red
            case 1: return new int[]{255, 255, 0};  // Yellow
            case 2: return new int[]{0, 255, 0};    // Green
            default: return black();
        }
    }

    @Override
    public int getEffectId() {
        return 35;
    }

    @Override
    public String getEffectName() {
        return "Traffic Light";
    }
}
