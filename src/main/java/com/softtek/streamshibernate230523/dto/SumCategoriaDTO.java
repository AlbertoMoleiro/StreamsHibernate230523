package com.softtek.streamshibernate230523.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumCategoriaDTO {
    private Short categoryId;
    private Long unitsInStock;
}
