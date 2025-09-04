package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.shipment.*;
import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final CreateShipmentBean createShipmentBean;
    private final GetShipmentBean getShipmentBean;
    private final AttachConsignmentsBean attachConsignmentsBean;
    private final ListShipmentConsignmentsBean listShipmentConsignmentsBean;
    private final AddShipmentLegBean addShipmentLegBean;
    private final ListShipmentLegsBean listShipmentLegsBean;
    private final UpdateShipmentLegActualBean updateShipmentLegActualBean;

    @Override @Transactional
    public ShipmentDto create(CreateShipmentRequestDto req) {
        return createShipmentBean.create(req);
    }

    @Override @Transactional(readOnly = true)
    public ShipmentDto get(Long id) {
        return getShipmentBean.get(id);
    }

    @Override @Transactional
    public List<ShipmentConsignmentDto> attachConsignments(Long shipmentId, AttachConsignmentsRequestDto req) {
        return attachConsignmentsBean.attach(shipmentId, req);
    }

    @Override @Transactional(readOnly = true)
    public List<ShipmentConsignmentDto> listConsignments(Long shipmentId) {
        return listShipmentConsignmentsBean.list(shipmentId);
    }

    @Override @Transactional
    public ShipmentLegDto addLeg(Long shipmentId, AddShipmentLegRequestDto req) {
        return addShipmentLegBean.add(shipmentId, req);
    }

    @Override @Transactional(readOnly = true)
    public List<ShipmentLegDto> listLegs(Long shipmentId) {
        return listShipmentLegsBean.list(shipmentId);
    }

    @Override  @Transactional
    public ShipmentLegDto updateLegActual(Long legId, UpdateShipmentLegActualRequestDto req) {
        return updateShipmentLegActualBean.update(legId, req);
    }
}

