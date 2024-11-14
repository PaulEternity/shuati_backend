package com.paul.project.satoken;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.paul.project.common.ErrorCode;
import com.paul.project.exception.ThrowUtils;

import javax.servlet.http.HttpServletRequest;

public class DeviceUtils {
    /**
     * 获取用户设备
     * @param request
     * @return
     */
    public static String getRequestDevice(HttpServletRequest request) {
        String requestHeader = request.getHeader(Header.USER_AGENT.toString());
        //使用HuTool解析
        UserAgent userAgent = UserAgentUtil.parse(requestHeader);
        ThrowUtils.throwIf(userAgent == null, ErrorCode.OPERATION_ERROR);
        String defaultDevice = "pc";
        // 默认值是 PC
        String device = "pc";
        // 是否为小程序
        if (isMiniProgram(requestHeader)) {
            device = "miniProgram";
        } else if (isPad(requestHeader)) {
            // 是否为 Pad
            device = "pad";
        } else if (userAgent.isMobile()) {
            // 是否为手机
            device = "mobile";
        }
        return device;

    }

    /**
     * 是否为小程序
     * @param userAgentStr
     * @return
     */
    private static boolean isMiniProgram(String userAgentStr){
        return StrUtil.containsIgnoreCase(userAgentStr,"MicroMessenger")
                && StrUtil.containsIgnoreCase(userAgentStr,"MiniProgram");
    }

    private static boolean isPad(String userAgentStr){
        boolean isIpad = StrUtil.containsIgnoreCase(userAgentStr,"iPad");

        boolean isAndroidTablet = StrUtil.containsIgnoreCase(userAgentStr,"Android")
                && !StrUtil.containsIgnoreCase(userAgentStr, "Mobile");;
        // 如果是 iPad 或 Android 平板，则返回 true
        return isIpad || isAndroidTablet;
    }

}
