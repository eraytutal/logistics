package com.vbt.logistics.dto;

import com.vbt.logistics.enums.RoleType;

public record OrderPartyDto(
        Long id,
        Long orderId,
        RoleType role,
        String partyName
) {}
