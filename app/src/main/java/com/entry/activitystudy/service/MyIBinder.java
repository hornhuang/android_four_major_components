package com.entry.activitystudy.service;

import android.content.ServiceConnection;

/**定义所有 Binder 的接口
 * 实现所有 Service 中自定义 Binder 的基本功能
 */
public interface MyIBinder {
    // onCreate
    void invokeMethodInMyService();
    // stop
    void stopService(ServiceConnection serviceConnection);
}
