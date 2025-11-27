package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 48: In Out
 * Lights sweep from center outward, then from edges inward (alternating).
 */
@Component
public class Effect048_InOut extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int center = segmentLength / 2;
        int cycle = (int)((adjustedTime / 100.0) % (center * 2));

        int distanceFromCenter = Math.abs(ledIndex - center);

        // First half: sweep out from center
        // Second half: sweep in from edges
        boolean isLit;
        if (cycle < center) {
            // Out phase: light up if within expanding radius from center
            isLit = distanceFromCenter <= cycle;
        } else {
            // In phase: light up if within contracting distance from edges
            int contracting = (center * 2) - cycle;
            int distanceFromEdge = Math.min(ledIndex, segmentLength - 1 - ledIndex);
            isLit = distanceFromEdge <= contracting;
        }

        return isLit ? getPrimaryColor(segment) : black();
    }

    @Override
    public int getEffectId() {
        return 48;
    }

    @Override
    public String getEffectName() {
        return "In Out";
    }
}
