package com.infinite.ehrSystem.labResults.service;

import com.infinite.ehrSystem.exception.DuplicateLabResultException;
import com.infinite.ehrSystem.exception.InvalidLabOrderStateException;
import com.infinite.ehrSystem.exception.LabOrderNotFoundException;
import com.infinite.ehrSystem.exception.LabResultNotFoundException;
import com.infinite.ehrSystem.labResults.dto.LabResultDTO;
import com.infinite.ehrSystem.labResults.entity.LabOrder;
import com.infinite.ehrSystem.labResults.entity.LabOrderStatus;
import com.infinite.ehrSystem.labResults.entity.LabResult;
import com.infinite.ehrSystem.labResults.entity.ResultFlag;
import com.infinite.ehrSystem.labResults.repository.LabOrderRepository;
import com.infinite.ehrSystem.labResults.repository.LabResultRepository;
import com.infinite.ehrSystem.labResults.strategy.ResultFlagStrategy;
import com.infinite.ehrSystem.labResults.strategy.ResultFlagStrategyFactory;
import com.infinite.ehrSystem.labResults.strategy.ResultType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class LabResultServiceImpl implements LabResultService {

    private final LabResultRepository labResultRepository;
    private final LabOrderRepository labOrderRepository;
    private final ResultFlagStrategyFactory resultFlagStrategyFactory;

    public LabResultServiceImpl(
            LabResultRepository labResultRepository,
            LabOrderRepository labOrderRepository,
            ResultFlagStrategyFactory resultFlagStrategyFactory) {

        this.labResultRepository = labResultRepository;
        this.labOrderRepository = labOrderRepository;
        this.resultFlagStrategyFactory = resultFlagStrategyFactory;
    }
    @Override
    public LabResultDTO createLabResult(LabResultDTO dto)
    {
        // Ensure lab order exists
        LabOrder labOrder = getLabOrder(dto.getLabOrderId());

        if (labOrder.getStatus()
                != LabOrderStatus.ORDERED)
        {
            throw new InvalidLabOrderStateException(
                    "Lab result can only be entered for ORDERED lab orders"
            );
        }
        // Prevent duplicate lab result
        if (labResultRepository.existsByLabOrderId(dto.getLabOrderId()))
        {
            throw new DuplicateLabResultException("Lab result already exists for lab order: " + dto.getLabOrderId());
        }

        // Calculate NORMAL / ABNORMAL
        ResultFlag flag = calculateFlag(dto.getResultValue(), dto.getReferenceRange());

        LabResult labResult = LabResult.builder()
                        .labOrderId(dto.getLabOrderId())
                        .resultValue(dto.getResultValue())
                        .unit(dto.getUnit())
                        .referenceRange(dto.getReferenceRange())
                        .flag(flag)
                        .resultDate(dto.getResultDate())
                        .build();

        LabResult savedResult = labResultRepository.save(labResult);

        // Mark order completed
        labOrder.setStatus(LabOrderStatus.COMPLETED);

        labOrderRepository.save(labOrder);

        return mapToDTO(savedResult, labOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public LabResultDTO getLabResultById(UUID id)
    {
        LabResult labResult = getLabResult(id);
        LabOrder labOrder = getLabOrder(labResult.getLabOrderId());
        return mapToDTO(labResult, labOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public LabResultDTO getLabResultByLabOrderId(UUID labOrderId)
    {
        LabResult labResult = labResultRepository.findByLabOrderId(labOrderId).orElseThrow(() ->
                                new LabResultNotFoundException("Lab result not found for lab order: " + labOrderId));
        LabOrder labOrder = getLabOrder(labOrderId);
        return mapToDTO(labResult, labOrder);
    }

    @Override
    public LabResultDTO updateLabResult(UUID id, LabResultDTO dto)
    {
        LabResult labResult = getLabResult(id);
        LabOrder labOrder = getLabOrder(labResult.getLabOrderId());

        ResultFlag flag = calculateFlag(dto.getResultValue(),dto.getReferenceRange());
            labResult.setResultValue(dto.getResultValue());
            labResult.setUnit(dto.getUnit());
            labResult.setReferenceRange(dto.getReferenceRange());
            labResult.setFlag(flag);
            labResult.setResultDate(dto.getResultDate());

        LabResult updatedResult = labResultRepository.save(labResult);

        return mapToDTO(updatedResult, labOrder);
    }

    @Override
    public void deleteLabResult(UUID id)
    {
        if (!labResultRepository.existsById(id))
        {
            throw new LabResultNotFoundException("Lab result not found with id: " + id);
        }
        labResultRepository.deleteById(id);
    }

    private LabOrder getLabOrder(UUID id)
    {
        return labOrderRepository.findById(id).orElseThrow(() ->
                        new LabOrderNotFoundException("Lab order not found with id: " + id));
    }

    private LabResult getLabResult(UUID id)
    {
        return labResultRepository.findById(id).orElseThrow(() ->
                        new LabResultNotFoundException("Lab result not found with id: "+ id));
    }

    private ResultFlag calculateFlag(String resultValue, String referenceRange)
    {
        ResultType resultType = determineResultType(resultValue);
        ResultFlagStrategy strategy = resultFlagStrategyFactory.getStrategy(resultType);

        return strategy.calculateFlag(resultValue, referenceRange);
    }

    private ResultType determineResultType(String resultValue)
    {
        String value = resultValue.trim();
        if ("POSITIVE".equalsIgnoreCase(value) || "NEGATIVE".equalsIgnoreCase(value))
        {
            return ResultType.POSITIVE_NEGATIVE;
        }
        return ResultType.NUMERIC;
    }

    private LabResultDTO mapToDTO(LabResult labResult, LabOrder labOrder)
    {
        return LabResultDTO.builder()
                .id(labResult.getId())
                .labOrderId(labResult.getLabOrderId())
                .resultValue(labResult.getResultValue())
                .unit(labResult.getUnit())
                .referenceRange(labResult.getReferenceRange())
                .flag(labResult.getFlag())
                .resultDate(labResult.getResultDate())
                .testName(labOrder.getTestName())
                .build();
    }

}