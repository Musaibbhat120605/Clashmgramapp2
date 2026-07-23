package com.clashgram.app

import android.os.Bundle
import com.getcapacitor.BridgeActivity

class MainActivity : BridgeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Register custom native plugins before the WebView bridge starts.
        registerPlugin(SecureScreenPlugin::class.java)
        super.onCreate(savedInstanceState)
    }
}
