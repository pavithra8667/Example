package com.infinite.ehrSystem.labResults.strategy;

import com.infinite.ehrSystem.exception.UnsupportedResultTypeException;
import org.springframework.stereotype.Component;

@Component
public class ResultFlagStrategyFactory {
    public static ResultFlagStrategy getStrategy(ResultType resultType) {
        if (resultType == ResultType.NUMERIC)
        {
            return new NumericResultStrategy();
        }
        if (resultType == ResultType.POSITIVE_NEGATIVE) {
            return new PositiveNegativeResultStrategy();
        }
        throw new UnsupportedResultTypeException("Unsupported result type: "+ resultType);
    }
}