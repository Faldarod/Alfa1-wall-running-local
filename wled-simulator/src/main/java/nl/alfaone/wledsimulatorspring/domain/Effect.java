package nl.alfaone.wledsimulatorspring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WLED Effect metadata
 * Represents information about a WLED effect including its parameters and capabilities
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Effect {
    private Integer id;
    private String name;
    private boolean is2D;           // Supports 2D matrix
    private boolean isAudioReactive; // Requires audio input
    private boolean supportsOverlay; // Can be used as overlay
    private int parameterCount;      // Number of adjustable parameters

    /**
     * Effect categories for organization
     */
    public enum Category {
        SOLID,
        BLINK,
        CHASE,
        RAINBOW,
        FIRE,
        SPARKLE,
        THEATER,
        SCANNER,
        TWINKLE,
        NOISE,
        MUSIC,
        MATRIX,
        OTHER
    }

    private Category category;
}
