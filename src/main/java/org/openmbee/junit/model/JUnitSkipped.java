package org.openmbee.junit.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@Accessors(chain = true)
public class JUnitSkipped {
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
