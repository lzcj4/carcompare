package com.carcompare.dto.log;

import com.carcompare.dto.PagedResultInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetAllLogsInput extends PagedResultInput {
    public GetAllLogsInput(){super();}

    private String username;

    private String type;

    private String beginTime;

    private String endTime;
}
