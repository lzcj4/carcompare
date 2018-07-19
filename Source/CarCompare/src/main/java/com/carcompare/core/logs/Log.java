package com.carcompare.core.logs;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Log {
    private long id;

    private String username;

    private Date time;

    private String type;

    private String description;
}
