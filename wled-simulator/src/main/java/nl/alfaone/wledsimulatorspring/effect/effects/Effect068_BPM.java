package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 68: BPM
 * Beats per minute - pulsing rhythm effect.
 */
@Component
public class Effect068_BPM extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double bpm = 60 + normalizeSpeed(speed) * 120; // 60-180 BPM
        double beatPhase = (adjustedTime * bpm / 60000.0) % 1.0;
        double brightness = Math.pow(Math.sin(beatPhase * Math.PI), 2);

        double hue = ((double)ledIndex / segmentLength + adjustedTime / 5000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 68;
    }

    @Override
    public String getEffectName() {
        return "BPM";
    }
}
