package com.example.finalprojectpsi.ui.components.ImageUploadButton

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.ui.theme.Slate800
import com.example.finalprojectpsi.ui.theme.White
import android.os.Build
import androidx.compose.ui.text.TextStyle

@Composable
fun ImageUploadButton(
    imageUri: MutableState<Uri?>,
    bitmap: MutableState<Bitmap?>,
    context: Context,
    text: String
) {
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri.value = uri
    }

    imageUri.value?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        bitmap.value?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(20.dp)
            )
        }
    }

    Button(
        onClick = { launcher.launch("image/*") },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Slate800
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.image_icon),
            contentDescription = "Image Icon",
            modifier = Modifier
                .size(size = 30.dp)
                .padding(end = 5.dp),
            tint = White
        )
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}