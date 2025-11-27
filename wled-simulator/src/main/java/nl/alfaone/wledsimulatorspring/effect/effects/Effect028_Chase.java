package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 28: Chase
 * Chase effect with moving block of color.
 */
@Component
public class Effect028_Chase extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int chaseSize = Math.max(1, intensity / 50);
        int position = (int)((adjustedTime / 100.0) % segmentLength);

        boolean inChase = Math.abs(ledIndex - position) < chaseSize;
        return inChase ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 28;
    }

    @Override
    public String getEffectName() {
        return "Chase";
    }
}
