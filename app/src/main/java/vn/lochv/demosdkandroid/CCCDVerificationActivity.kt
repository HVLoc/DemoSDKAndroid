package vn.lochv.demosdkandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.gson.Gson

class CCCDVerificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lấy JSON của NfcModel từ Intent
        val userDataJson = intent.getStringExtra("userData")
        val nfcModel: NfcModel? = Gson().fromJson(userDataJson, NfcModel::class.java)

        setContent {
            nfcModel?.let {
                CCCDVerificationScreen(
                    data = nfcModel,
                    onContinue = {
                        // Xử lý sự kiện nút "Tiếp tục"
                        finish() // Đóng màn hình sau khi xử lý
                    }
                )
            }
        }
    }
}