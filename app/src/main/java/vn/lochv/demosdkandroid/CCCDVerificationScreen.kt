package vn.lochv.demosdkandroid

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

//data class CCCDData(
//    val name: String,
//    val cccdNumber: String,
//    val otherDocument: String?,
//    val dob: String,
//    val gender: String,
//    val nationality: String,
//    val religion: String,
//    val ethnicity: String,
//    val hometown: String,
//    val residence: String,
//    val distinctiveSigns: String,
//    val issueDate: String,
//    val expiryDate: String,
//    val fatherName: String?,
//    val motherName: String?,
//    val faceMatchScore: Int,
//    val citizenImage: String,
//    val liveImage: String
//)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CCCDVerificationScreen(data: NfcModel, onContinue: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Xác thực CCCD", fontSize = 20.sp, fontWeight = FontWeight.Bold) },

                )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display Images
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(data.file),
                        contentDescription = "Citizen Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = rememberAsyncImagePainter(data.imgLiveNess),
                        contentDescription = "Live Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Face Match Result
                Text(
                    text = "Hình ảnh khuôn mặt khớp ảnh CCCD",
                    color = Color.Green,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Kết quả: ${data.faceMatching}%",
                    color = Color.Green,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()

                // Display Information
                InfoRow(label = "Họ và tên:", value = data.nameVNM ?: "")
                InfoRow(label = "CCCD Số:", value = data.number ?: "")
                InfoRow(label = "Giấy tờ khác:", value = data.otherPaper ?: "Không")
                InfoRow(label = "Ngày sinh:", value = data.dobVMN ?: "")
                InfoRow(label = "Giới tính:", value = data.nameDadVMN ?: "")
                InfoRow(label = "Quốc gia:", value = data.nationalityVMN ?: "")
                InfoRow(label = "Tôn giáo:", value = data.religionVMN ?: "")
                InfoRow(label = "Dân tộc:", value = data.nationVNM)
                InfoRow(label = "Quê quán:", value = data.homeTownVMN)
                InfoRow(label = "Nơi thường trú:", value = data.residentVMN ?: "")
                InfoRow(label = "Dấu hiệu nhận biết:", value = data.identificationSignsVNM)
                InfoRow(label = "Ngày cấp:", value = data.registrationDateVMN ?: "")
                InfoRow(label = "Ngày hết hạn:", value = data.doeVMN ?: "")
                InfoRow(label = "Tên bố:", value = data.nameDadVMN ?: "Không có")
                InfoRow(label = "Tên mẹ:", value = data.nameMomVMN ?: "Không có")

                Spacer(modifier = Modifier.height(24.dp))

                // Continue Button
                Button(
                    onClick = onContinue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text("Trở về", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    )
}

@Composable
fun InfoRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value ?: "",
            modifier = Modifier.weight(2f),
            fontSize = 14.sp
        )
    }
}