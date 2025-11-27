package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 39: Stream
 * Streaming pixels effect with trailing fade.
 */
@Component
public class Effect039_Stream extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (adjustedTime / 50.0) % segmentLength;
        double distance = (ledIndex - position + segmentLength) % segmentLength;

        double brightness = distance < 10 ? Math.exp(-distance / 3.0) : 0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 39;
    }

    @Override
    public String getEffectName() {
        return "Stream";
    }
}
