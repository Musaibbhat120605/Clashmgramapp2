package com.clashgram.app

import android.view.WindowManager
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

/**
 * Native plugin that toggles Android's FLAG_SECURE on the app window.
 *
 * When enabled, the OS blocks screenshots, screen recording, and the
 * "recent apps" thumbnail preview for this app - protecting the user's
 * own open chats from being captured on their device, independent of
 * any per-message "protected content" flag Telegram sets server-side.
 *
 * JS usage (after `import { registerPlugin } from '@capacitor/core'`):
 *   const SecureScreen = registerPlugin<{
 *     enable(): Promise<void>;
 *     disable(): Promise<void>;
 *     isEnabled(): Promise<{ enabled: boolean }>;
 *   }>('SecureScreen');
 *
 *   await SecureScreen.enable();
 */
@CapacitorPlugin(name = "SecureScreen")
class SecureScreenPlugin : Plugin() {

    private var enabled = false

    @PluginMethod
    fun enable(call: PluginCall) {
        activity.runOnUiThread {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
            enabled = true
            call.resolve()
        }
    }

    @PluginMethod
    fun disable(call: PluginCall) {
        activity.runOnUiThread {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            enabled = false
            call.resolve()
        }
    }

    @PluginMethod
    fun isEnabled(call: PluginCall) {
        val ret = com.getcapacitor.JSObject()
        ret.put("enabled", enabled)
        call.resolve(ret)
    }
}
