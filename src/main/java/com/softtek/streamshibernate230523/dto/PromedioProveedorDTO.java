package com.softtek.streamshibernate230523.dto;

import com.softtek.streamshibernate230523.utilities.MapperConfig;
import lombok.*;
import org.hibernate.annotations.SecondaryRow;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PromedioProveedorDTO  {
    private Short supplierId;
    private Double unitPrice;
}
