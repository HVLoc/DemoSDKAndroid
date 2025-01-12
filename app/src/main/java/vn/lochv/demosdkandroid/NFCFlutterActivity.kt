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
    private val CHANNEL = "2id.ekyc"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                "setInitialNFC" -> {
                    // Trả về kết quả cho Flutter
                    result.success("Success NFC")
                }
                "dataNFC" -> {
                    val userInfoModel = call.argument<Map<String, Any>>("value")
                    // Chuyển đổi JSON sang NfcModel
                    val gson = Gson()
                    val json = gson.toJson(userInfoModel)
                    val nfcModel = gson.fromJson(json, NfcModel::class.java)

                    // Xử lý thông tin NFC
                    print( gson.toJson(nfcModel))

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
