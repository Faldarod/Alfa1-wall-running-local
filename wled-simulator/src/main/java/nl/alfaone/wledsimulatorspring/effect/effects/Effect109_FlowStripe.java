package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 109: Flow Stripe
 * Flowing stripes with color transitions.
 */
@Component
public class Effect109_FlowStripe extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int stripeWidth = Math.max(1, (int)(normalizeIntensity(intensity) * 10) + 1);
        int position = (int)((adjustedTime / 30.0) + ledIndex);
        boolean inStripe = (position / stripeWidth) % 2 == 0;

        if (inStripe) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 109;
    }

    @Override
    public String getEffectName() {
        return "Flow Stripe";
    }
}
