package on.ssgdeal.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
public class NumberEvent {

    private Integer number;
}
