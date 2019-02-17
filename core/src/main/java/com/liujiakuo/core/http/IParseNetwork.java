package com.liujiakuo.core.http;

/**
 * Created by 佳阔 on 2019/2/17.
 * 解析网络数据，抽出一个接口，可以把不同解析数据的方式拆除来
 */

public interface IParseNetwork<T> {
    T parseNetworkResponse(String jsonStr);
}
