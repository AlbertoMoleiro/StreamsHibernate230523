package com.softtek.streamshibernate230523.service;

import com.softtek.streamshibernate230523.dto.PromedioProveedorDTO;
import com.softtek.streamshibernate230523.dto.PromedioCategoriaDTO;
import com.softtek.streamshibernate230523.dto.SumCategoriaDTO;
import com.softtek.streamshibernate230523.dto.SumaProveedorDTO;
import com.softtek.streamshibernate230523.model.Product;
import com.softtek.streamshibernate230523.repository.ICrud;

import java.util.List;

public interface IProductService extends ICrud<Product, Short> {
    List<Product> getAll();

    List<PromedioProveedorDTO> getPromedioPrecios();

    List<SumaProveedorDTO> getSumaStock();
    List<PromedioCategoriaDTO> getPromedioCategory();
    List<SumCategoriaDTO> getSumaCategory();
}
