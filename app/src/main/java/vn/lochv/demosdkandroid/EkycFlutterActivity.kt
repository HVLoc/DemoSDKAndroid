package vn.lochv.demosdkandroid

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class EkycFlutterActivity : FlutterActivity() {
    private val CHANNEL = "2id.ekyc"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "sendData") {
                val userInfoModel = call.argument<Map<String, Any>>("userInfoModel")
                val sendNfcModel = call.argument<Map<String, Any>>("sendNfcModel")
                // Xử lý dữ liệu nhận được từ Flutter
                println("Received from Flutter: ${userInfoModel.toString()}")

                // Đóng Activity (Flutter SDK)
                finish()

                // Trả về kết quả cho Flutter
                result.success("SDK Closed")
            } else {
                result.notImplemented()
            }
        }
    }

    override fun getInitialRoute(): String? {
        return intent.getStringExtra("initialRoute") ?: super.getInitialRoute()
    }
}
