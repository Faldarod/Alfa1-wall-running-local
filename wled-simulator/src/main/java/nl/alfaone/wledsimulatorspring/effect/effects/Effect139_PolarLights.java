package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 139: 2D Polar Lights
 * Aurora borealis effect.
 */
@Component
public class Effect139_PolarLights extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave1 = Math.sin((ledIndex * 0.1) + (adjustedTime / 200.0));
        double wave2 = Math.cos((ledIndex * 0.07) - (adjustedTime / 300.0));
        double wave3 = Math.sin((adjustedTime / 150.0));

        double brightness = ((wave1 + wave2) / 2.0 + 1.0) / 2.0;
        double hue = 0.4 + 0.2 * wave3; // Green-blue aurora colors

        return scale(hsvToRgb(hue, 0.8, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 139;
    }

    @Override
    public String getEffectName() {
        return "2D Polar Lights";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
