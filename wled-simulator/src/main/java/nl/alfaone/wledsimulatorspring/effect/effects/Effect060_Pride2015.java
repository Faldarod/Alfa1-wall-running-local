package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 60: Pride2015
 * Rainbow pride effect with moving colors.
 */
@Component
public class Effect060_Pride2015 extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double hue = ((ledIndex * 360.0 / segmentLength) + (adjustedTime / 20.0)) % 360.0;
        double saturation = 0.9 + 0.1 * Math.sin(adjustedTime / 100.0 + ledIndex);
        double brightness = 0.8 + 0.2 * Math.sin(adjustedTime / 150.0 - ledIndex);

        return hsvToRgb(hue / 360.0, saturation, brightness);
    }

    @Override
    public int getEffectId() {
        return 60;
    }

    @Override
    public String getEffectName() {
        return "Pride2015";
    }
}
