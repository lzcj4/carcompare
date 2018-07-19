package com.carcompare.dto.car;

        import com.carcompare.dto.PagedResultInput;
import lombok.Data;
        import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetAllVehiclesInput extends PagedResultInput {
    private String paramMap;
}
