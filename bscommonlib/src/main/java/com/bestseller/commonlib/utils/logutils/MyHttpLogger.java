package com.bestseller.commonlib.utils.logutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.logging.HttpLoggingInterceptor;
/**
 * @author:Joshua
 * @data:2019/2/18
 * @des:
 */
public class MyHttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
//        if ((message.startsWith("{") && message.endsWith("}"))
//                || (message.startsWith("[") && message.endsWith("]"))) {
////            message = JsonUtil.formatJson(message);
//            message = KLog.json(message);
////            Logger.d(message);
//        }

        try {
            if (message.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(message);
                message = jsonObject.toString(4);
            } else if (message.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(message);
                message = jsonArray.toString(4);
            } else {
            }
        } catch (JSONException e) {
        }

        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtil.d(mMessage.toString());
        }
    }
}
