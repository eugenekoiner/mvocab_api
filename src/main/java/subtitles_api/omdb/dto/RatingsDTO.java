package subtitles_api.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RatingsDTO {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
}
