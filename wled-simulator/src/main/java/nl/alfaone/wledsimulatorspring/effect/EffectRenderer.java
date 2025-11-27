package nl.alfaone.wledsimulatorspring.effect;

import nl.alfaone.wledsimulatorspring.domain.Segment;

/**
 * Interface for all LED effect implementations.
 * Each effect renders colors for individual LEDs based on time and segment parameters.
 */
public interface EffectRenderer {

    /**
     * Render the effect for a single LED at a specific point in time.
     *
     * @param segment Contains effect parameters: color, speed, intensity, brightness, palette
     * @param ledIndex Position of the LED within the segment (0 to length-1)
     * @param time Current animation time in milliseconds (used for time-based animations)
     * @return RGB color array [R, G, B] with values 0-255
     */
    int[] render(Segment segment, int ledIndex, long time);

    /**
     * Get the unique effect ID (0-186).
     * This ID maps to the WLED effect list.
     *
     * @return Effect ID
     */
    int getEffectId();

    /**
     * Get the human-readable effect name.
     *
     * @return Effect name (e.g., "Solid", "Rainbow", "Fire 2012")
     */
    String getEffectName();

    /**
     * Check if this effect requires a 2D LED matrix.
     * 2D effects use width/height parameters instead of linear positioning.
     *
     * @return true if this is a 2D matrix effect
     */
    default boolean is2D() {
        return false;
    }

    /**
     * Check if this effect requires audio input for reactive animations.
     * Audio reactive effects use frequency/amplitude data from microphone or line-in.
     *
     * @return true if this effect requires audio input
     */
    default boolean isAudioReactive() {
        return false;
    }
}
