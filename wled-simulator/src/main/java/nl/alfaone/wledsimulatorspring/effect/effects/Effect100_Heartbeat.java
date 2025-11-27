package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 100: Heartbeat
 * Pulsing heartbeat rhythm effect.
 */
@Component
public class Effect100_Heartbeat extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double beatCycle = (adjustedTime / 1000.0) % 1.0;
        double brightness;

        if (beatCycle < 0.15) {
            brightness = beatCycle / 0.15;
        } else if (beatCycle < 0.3) {
            brightness = 1.0 - (beatCycle - 0.15) / 0.15;
        } else if (beatCycle < 0.4) {
            brightness = (beatCycle - 0.3) / 0.1 * 0.7;
        } else if (beatCycle < 0.5) {
            brightness = 0.7 - (beatCycle - 0.4) / 0.1 * 0.7;
        } else {
            brightness = 0;
        }

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 100;
    }

    @Override
    public String getEffectName() {
        return "Heartbeat";
    }
}
