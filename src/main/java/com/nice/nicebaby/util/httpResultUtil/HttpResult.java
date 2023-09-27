package com.nice.nicebaby.util.httpResultUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResult<T> {
    int result;
    T data;
}
