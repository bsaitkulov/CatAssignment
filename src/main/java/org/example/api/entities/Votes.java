package org.example.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Votes {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("image_id")
    String imageId;
    @JsonProperty("sub_id")
    String subId;
    @JsonProperty("value")
    String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    String message;

}
