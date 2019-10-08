package com.ecpss.tkmybatismapper.Base;

import org.springframework.stereotype.Repository;

@Repository
public interface CrudDAO<T> extends InsertDAO<T>, DeleteDAO<T>, UpdateDAO<T>, SelectDAO<T> {
}
