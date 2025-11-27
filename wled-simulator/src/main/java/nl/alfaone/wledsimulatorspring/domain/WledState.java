package nl.alfaone.wledsimulatorspring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * WLED /json/state response
 * Contains the current state of the WLED device
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WledState {
    @JsonProperty("on")
    private Boolean on;  // Master power

    @JsonProperty("bri")
    private Integer brightness;  // Master brightness (0-255)

    @JsonProperty("transition")
    private Integer transition;  // Transition time in 100ms units

    @JsonProperty("ps")
    private Integer preset;  // Preset ID (-1 = none)

    @JsonProperty("pl")
    private Integer playlist;  // Playlist ID (-1 = none)

    @JsonProperty("nl")
    private Nightlight nightlight;

    @JsonProperty("udpn")
    private UdpSync udpSync;

    @JsonProperty("lor")
    private Integer ledOutputRange;  // LED output range

    @JsonProperty("mainseg")
    private Integer mainSegment;  // Main segment ID

    @JsonProperty("seg")
    private List<Segment> segments;  // LED segments

    @JsonProperty("v")
    @Builder.Default
    private Long updateId = 0L;  // Update ID for tracking changes (keep default for initialization)

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Nightlight {
        @JsonProperty("on")
        private Boolean on;

        @JsonProperty("dur")
        private Integer duration;  // Duration in minutes

        @JsonProperty("mode")
        private Integer mode;  // Nightlight mode

        @JsonProperty("tbri")
        private Integer targetBrightness;  // Target brightness

        @JsonProperty("rem")
        private Integer remaining;  // Remaining time in minutes
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UdpSync {
        @JsonProperty("send")
        private Boolean send;  // Send UDP sync

        @JsonProperty("recv")
        private Boolean receive;  // Receive UDP sync
    }
}
