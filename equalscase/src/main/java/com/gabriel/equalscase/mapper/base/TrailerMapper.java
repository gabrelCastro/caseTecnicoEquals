package com.gabriel.equalscase.mapper.base;

import com.gabriel.equalscase.model.base.Trailer;

public interface TrailerMapper<T extends Trailer> {
    void insert(T trailer);
}