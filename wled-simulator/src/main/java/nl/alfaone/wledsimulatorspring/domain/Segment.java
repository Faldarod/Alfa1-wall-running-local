package nl.alfaone.wledsimulatorspring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * WLED Segment - represents a portion of the LED strip
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Segment {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("start")
    private Integer start;  // First LED in segment

    @JsonProperty("stop")
    private Integer stop;  // Last LED in segment (exclusive)

    @JsonProperty("len")
    private Integer length;  // Number of LEDs

    @JsonProperty("grp")
    private Integer grouping;  // LED grouping

    @JsonProperty("spc")
    private Integer spacing;  // LED spacing

    @JsonProperty("of")
    private Integer offset;  // Offset

    @JsonProperty("on")
    private Boolean on;  // Segment power

    @JsonProperty("frz")
    private Boolean freeze;  // Freeze effect

    @JsonProperty("bri")
    private Integer brightness;  // Segment brightness (0-255)

    @JsonProperty("cct")
    private Integer colorTemperature;  // Color temperature (0-255)

    @JsonProperty("col")
    private List<List<Integer>> colors;  // Colors [[R,G,B,W], [R,G,B,W], [R,G,B,W]]

    @JsonProperty("fx")
    private Integer effect;  // Effect ID

    @JsonProperty("sx")
    private Integer speed;  // Effect speed (0-255)

    @JsonProperty("ix")
    private Integer intensity;  // Effect intensity (0-255)

    @JsonProperty("pal")
    private Integer palette;  // Palette ID

    @JsonProperty("sel")
    private Boolean selected;  // Is segment selected

    @JsonProperty("rev")
    private Boolean reverse;  // Reverse direction

    @JsonProperty("mi")
    private Boolean mirror;  // Mirror effect
}
