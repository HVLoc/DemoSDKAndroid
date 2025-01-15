package vn.lochv.demosdkandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject
import com.google.gson.Gson

class NFCFlutterActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        // Tạo MethodChannel
        val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)

        // Gửi dữ liệu qua MethodChannel
        methodChannel.invokeMethod(
            SET_INITIAL_NFC, mapOf("key" to "Hello from Android")
        )
        methodChannel.setMethodCallHandler { call, result ->
                when (call.method) {
                    DATA_NFC -> {
                        val userInfoModel = call.argument<Map<String, Any>>("value")
                        // Chuyển đổi JSON sang NfcModel
                        val gson = Gson()
                        val json = gson.toJson(userInfoModel)
                        val nfcModel = gson.fromJson(json, NfcModel::class.java)

                        // Xử lý thông tin NFC
                        print(gson.toJson(nfcModel))

                        // Đóng Activity (Flutter SDK)
                        finish()

                        // Trả về kết quả cho Flutter
                        result.success("SDK Closed")
                    }

                    else -> {
                        result.notImplemented()
                    }
                }
            }
    }

}
